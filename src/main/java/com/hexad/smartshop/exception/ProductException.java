package com.hexad.smartshop.exception;

public class ProductException extends SmartshopBaseException {

	private static final long serialVersionUID = -2931988598003321160L;
	
	public ProductException() {
		
	}
	
	public ProductException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}
	
	public ProductException(String argErrorCode, String errorMessage, Throwable argThrowable, Object[] args) {
		super(argErrorCode, errorMessage, argThrowable, args);
	}

}
