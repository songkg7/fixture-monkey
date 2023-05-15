package com.navercorp.fixturemonkey.spring;

import java.util.Optional;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.navercorp.fixturemonkey.FixtureMonkey;

@SuppressWarnings("SpringFacetCodeInspection")
@Configuration
@EnableAspectJAutoProxy
public class FixtureMonkeyAspectConfiguration {
	private final FixtureMonkey fixtureMonkey;

	@Value("${fixture.pointcut:execution(reactor.core.publisher.Mono+ *(..))}")
	private String pointcutExpression;

	@Autowired(required = false)
	public FixtureMonkeyAspectConfiguration(Optional<FixtureMonkey> fixtureMonkey) {
		this.fixtureMonkey = fixtureMonkey.orElse(FixtureMonkey.create());
	}

	@Bean
	public BananaInterceptor bananaInterceptor() {
		return new BananaInterceptor(fixtureMonkey);
	}

	@Bean
	public Advisor bananaAdvisor(BananaInterceptor bananaInterceptor) {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(pointcutExpression);
		return new DefaultPointcutAdvisor(pointcut, bananaInterceptor);
	}
}
