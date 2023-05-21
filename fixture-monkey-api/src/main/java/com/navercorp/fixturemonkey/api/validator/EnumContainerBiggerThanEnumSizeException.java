package com.navercorp.fixturemonkey.api.validator;

import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;

@API(since = "0.6.0", status = Status.EXPERIMENTAL)
public class EnumContainerBiggerThanEnumSizeException extends RuntimeException {
	public EnumContainerBiggerThanEnumSizeException(String message) {
		super(message);
	}
}
