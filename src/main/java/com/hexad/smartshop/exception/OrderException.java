package com.hexad.smartshop.exception;

public class OrderException extends SmartshopBaseException {

	private static final long serialVersionUID = -8964233399097173914L;


	public OrderException() {
		super();
	}

	public OrderException(String argErrorCode) {
		super(argErrorCode);
	}

	public OrderException(String argErrorCode, Throwable argThrowable) {
		super(argErrorCode, argThrowable);
	}

	public OrderException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}
	public OrderException(String argErrorCode, String errorMessage, Throwable argThrowable, Object[] args) {
		super(argErrorCode, errorMessage, argThrowable, args);
	}
}
