package com.navercorp.fixturemonkey.spring;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;

import reactor.core.publisher.Mono;

import com.navercorp.fixturemonkey.ArbitraryBuilder;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.type.Types;

public class BananaInterceptor implements MethodInterceptor {
	private final FixtureMonkey fixtureMonkey;

	public BananaInterceptor(FixtureMonkey fixtureMonkey) {
		this.fixtureMonkey = fixtureMonkey;
	}

	@SuppressWarnings({"rawtypes", "unchecked", "ReactiveStreamsUnusedPublisher"})
	@Nullable
	@Override
	public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {
		Object proceed = invocation.proceed();
		if (Modifier.isStatic(invocation.getMethod().getModifiers())) {
			return proceed;
		}

		Class<?> callerType = requireNonNull(invocation.getThis()).getClass();
		AnnotatedType annotatedReturnType = invocation.getMethod().getAnnotatedReturnType();
		Class<?> returnType;
		List<AnnotatedType> genericsTypes = Types.getGenericsTypes(annotatedReturnType);
		if (genericsTypes.isEmpty()) {
			returnType = invocation.getMethod().getReturnType();
		} else {
			returnType = Types.getActualType(genericsTypes.get(0));
		}
		if (AopUtils.isAopProxy(invocation.getThis()) && invocation.getThis() instanceof Advised advised) {
			callerType = advised.getProxiedInterfaces()[0];
		}

		Map<String, Object> manipulators = new HashMap<>();
		manipulators.putAll(Bananas.get(callerType, returnType));
		manipulators.putAll(Bananas.get(callerType, invocation.getMethod().getName()));

		if (proceed instanceof Mono mono) {
			ArbitraryBuilder<?> fallbackBuilder = fixtureMonkey.giveMeBuilder(returnType);
			manipulators.forEach(fallbackBuilder::set);
			return mono.defaultIfEmpty(fallbackBuilder.sample())
				.onErrorResume(throwable -> Mono.just(fallbackBuilder.sample()))
				.map(value -> {
					ArbitraryBuilder<?> builder = fixtureMonkey.giveMeBuilder(value);
					manipulators.forEach(builder::set);
					return builder.sample();
				})
				.onErrorResume(throwable -> Mono.just(fixtureMonkey.giveMeOne(returnType)));
		} else if (proceed instanceof Optional optional) {
			return optional.map(value -> {
				ArbitraryBuilder<?> builder = fixtureMonkey.giveMeBuilder(value);
				manipulators.forEach(builder::set);
				return builder.sample();
			});
		}

		if (manipulators.isEmpty()) {
			return proceed;
		}

		ArbitraryBuilder<?> builder;
		if (proceed == null) {
			builder = fixtureMonkey.giveMeBuilder(returnType);
		} else {
			builder = fixtureMonkey.giveMeBuilder(proceed);
		}
		manipulators.forEach(builder::set);
		return builder.sample();
	}
}
