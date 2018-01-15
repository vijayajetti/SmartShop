package com.hexad.smartshop.exception;

public class CustomerException extends SmartshopBaseException {

	private static final long serialVersionUID = -1458783884772948561L;

	public CustomerException() {
		super();
	}

	public CustomerException(String argErrorCode) {
		super(argErrorCode);
	}

	public CustomerException(String argErrorCode, Throwable argThrowable) {
		super(argErrorCode, argThrowable);
	}

	public CustomerException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}
}
