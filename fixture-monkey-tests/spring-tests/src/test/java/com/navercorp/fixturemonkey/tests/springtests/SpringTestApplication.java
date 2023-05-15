package com.navercorp.fixturemonkey.tests.springtests;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.navercorp.fixturemonkey.spring.FixtureMonkeyAspectConfiguration;

@SpringBootApplication
@Import(FixtureMonkeyAspectConfiguration.class)
public class SpringTestApplication {
}
