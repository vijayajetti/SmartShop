package com.hexad.smartshop.exception;

public class SmartshopBaseException extends RuntimeException {

	private static final long serialVersionUID = -3929312467360542082L;

	/**
	 * SmartshopBaseException and its subclasses are a form of
	 * RuntimeException SmartshopBaseException class acts as a base class for other
	 * Business Exception
	 */

	protected String errorCode;

	protected String errorMessage;


	public SmartshopBaseException() {
		super();
	}

	public SmartshopBaseException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public SmartshopBaseException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public SmartshopBaseException(Throwable cause) {
		super(cause);
	}

	public SmartshopBaseException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
		this.errorMessage = errorMessage;
	}

	public SmartshopBaseException(String errorCode, String errorMessage, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}