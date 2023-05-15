package com.navercorp.fixturemonkey.tests.springtests.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;

@Configuration
public class TestConfig {
	@Bean
	public WebClient webClient() {
		return WebClient.builder().build();
	}

	@Bean
	public FixtureMonkey fixtureMonkey() {
		return FixtureMonkey.builder()
			.objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
			.build();
	}
}
