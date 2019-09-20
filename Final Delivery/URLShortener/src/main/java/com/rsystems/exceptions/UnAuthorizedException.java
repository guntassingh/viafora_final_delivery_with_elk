package com.rsystems.exceptions;

public class UnAuthorizedException  extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnAuthorizedException(String message) {
		super(message);
	}
	
	public UnAuthorizedException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
