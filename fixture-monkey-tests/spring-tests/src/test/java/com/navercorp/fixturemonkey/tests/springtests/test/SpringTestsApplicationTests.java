package com.navercorp.fixturemonkey.tests.springtests.test;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.navercorp.fixturemonkey.spring.Bananas;
import com.navercorp.fixturemonkey.tests.springtests.SpringTestApplication;
import com.navercorp.fixturemonkey.tests.springtests.config.TestApiClient;

@SpringBootTest(classes = SpringTestApplication.class, webEnvironment = WebEnvironment.NONE)
class SpringTestsApplicationTests {

	@Autowired
	private TestApiClient testApiClient;

	@AfterEach
	void tearDown() {
		Bananas.clear();
	}

	@Test
	void getMonoString() {
		String expected = "test";

		String actual = testApiClient.getMonoString()
			.blockOptional().orElseThrow();

		then(actual).isEqualTo(expected);
	}

	@Test
	void getRawString() {
		String expected = "test";

		String actual = testApiClient.getRawString();

		then(actual).isEqualTo(expected);
	}

	@Test
	void getStaticString() {
		String actual = TestApiClient.getStaticString();

		then(actual).isEqualTo("test");
	}

	@Test
	void setRawString() {
		String expected = "hahaho";
		Bananas.type(TestApiClient.class, String.class)
			.set("$", expected);

		String actual = testApiClient.getRawString();

		then(actual).isEqualTo(expected);
	}

	@Test
	void setByType() {
		String expected = "hahaho";
		Bananas.type(TestApiClient.class, String.class)
			.set("$", expected);

		String actual = testApiClient.getMonoString()
			.blockOptional().orElseThrow();

		then(actual).isEqualTo(expected);
	}

	@Test
	void setByName() {
		String expected = "hahaho";
		Bananas.name(TestApiClient.class, "getMonoString")
			.set("$", expected);

		String actual = testApiClient.getMonoString()
			.blockOptional().orElseThrow();

		then(actual).isEqualTo(expected);
	}

	@Test
	void setWhenEmpty() {
		String expected = "hahaho";
		Bananas.type(TestApiClient.class, String.class)
			.set("$", expected);

		String actual = testApiClient.getMonoEmpty()
			.blockOptional().orElseThrow();

		then(actual).isEqualTo(expected);
	}

	@Test
	void setWhenNull() {
		String expected = "hahaho";
		Bananas.type(TestApiClient.class, String.class)
			.set("$", expected);

		String actual = testApiClient.getNull();

		then(actual).isEqualTo(expected);
	}

	@Test
	void fetchError() {
		String actual = testApiClient.fetch()
			.blockOptional().orElseThrow();

		then(actual).isNotNull();
	}

	@Test
	void getComplexType() {
		String actual = testApiClient.getComplexType().value();

		then(actual).isEqualTo("test");
	}

	@Test
	void setComplexType() {
		String expected = "hahaho";
		Bananas.type(TestApiClient.class, ComplexType.class)
			.set("value", expected);
		String actual = testApiClient.getComplexType().value();

		then(actual).isEqualTo(expected);
	}
}
