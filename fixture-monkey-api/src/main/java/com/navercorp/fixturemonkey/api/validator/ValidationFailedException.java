package com.navercorp.fixturemonkey.api.validator;

import java.util.Set;

import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;

@API(since = "0.6.0", status = Status.EXPERIMENTAL)
public class ValidationFailedException extends RuntimeException {
	private final Set<?> constraintViolations;

	public ValidationFailedException(String message, Set<?> constraintViolations) {
		super(message);
		this.constraintViolations = constraintViolations;
	}
}
