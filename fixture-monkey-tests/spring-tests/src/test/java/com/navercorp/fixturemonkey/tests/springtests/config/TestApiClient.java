package com.navercorp.fixturemonkey.tests.springtests.config;

import java.net.URI;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import com.navercorp.fixturemonkey.tests.springtests.test.ComplexType;

@Component
@RequiredArgsConstructor
public class TestApiClient {
	private final WebClient webClient;

	public Mono<String> getMonoString() {
		return Mono.just("test");
	}

	public Mono<String> getMonoEmpty() {
		return Mono.empty();
	}

	public Mono<String> fetch() {
		return webClient.get()
			.uri(URI.create("localhost:8080"))
			.retrieve()
			.bodyToMono(String.class);
	}

	public String getRawString() {
		return "test";
	}

	public String getNull() {
		return null;
	}

	public static String getStaticString() {
		return "test";
	}

	public ComplexType getComplexType() {
		return new ComplexType("test");
	}
}
