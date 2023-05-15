package com.navercorp.fixturemonkey.spring;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nullable;

import com.navercorp.fixturemonkey.spring.Bananas.RequestTarget.RequestMethod;
import com.navercorp.fixturemonkey.spring.Bananas.RequestTarget.FixtureMonkeyManipulation;

public final class Bananas {
	private static final Map<Class<?>, RequestTarget> targetsByType = new ConcurrentHashMap<>();

	public static FixtureMonkeyManipulation type(Class<?> apiClientType, Class<?> returnType) {
		return method(apiClientType, new RequestMethod<>(returnType, null));
	}

	public static FixtureMonkeyManipulation name(Class<?> apiClientType, String methodName) {
		return method(apiClientType, new RequestMethod<>(null, methodName));
	}

	private static FixtureMonkeyManipulation method(Class<?> type, RequestMethod<?> requestMethod) {
		RequestTarget requestTarget = targetsByType.computeIfAbsent(type, key ->
			new RequestTarget(key, new HashMap<>())
		);

		return requestTarget.manipulationsByRequestMethod.computeIfAbsent(
			requestMethod,
			key -> new FixtureMonkeyManipulation(new HashMap<>())
		);
	}

	static Map<String, Object> get(Class<?> apiClientType, Class<?> returnType) {
		return get(apiClientType, new RequestMethod<>(returnType, null));
	}

	static Map<String, Object> get(Class<?> apiClientType, String methodName) {
		return get(apiClientType, new RequestMethod<>(null, methodName));
	}

	private static Map<String, Object> get(Class<?> apiClientType, RequestMethod<?> requestMethod) {
		RequestTarget requestTarget = targetsByType.computeIfAbsent(apiClientType, type ->
			new RequestTarget(apiClientType, new HashMap<>())
		);

		FixtureMonkeyManipulation fixtureMonkeyManipulation =
			requestTarget.manipulationsByRequestMethod.get(requestMethod);
		if (fixtureMonkeyManipulation == null) {
			return Map.of();
		}

		return fixtureMonkeyManipulation.valuesByExpression;
	}

	public static void clear() {
		targetsByType.clear();
	}

	public record RequestTarget(
		Class<?> type,

		Map<RequestMethod<?>, FixtureMonkeyManipulation> manipulationsByRequestMethod
	) {
		public record RequestMethod<T>(
			@Nullable
			Class<T> returnType,

			@Nullable
			String methodName
		) {
			@Override
			public boolean equals(Object obj) {
				if (this == obj) {
					return true;
				}
				if (obj == null || getClass() != obj.getClass()) {
					return false;
				}
				RequestMethod<?> that = (RequestMethod<?>)obj;
				return Objects.equals(returnType, that.returnType) && Objects.equals(methodName, that.methodName);
			}

			@Override
			public int hashCode() {
				return Objects.hash(returnType, methodName);
			}
		}

		public record FixtureMonkeyManipulation(
			Map<String, Object> valuesByExpression
		) {
			public void set(String expression, Object value) {
				valuesByExpression.put(expression, value);
			}

			@Override
			public Map<String, Object> valuesByExpression() {
				return Collections.unmodifiableMap(valuesByExpression);
			}
		}
	}
}
