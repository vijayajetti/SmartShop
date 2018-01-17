package com.hexad.smartshop.exception;

public class SmartshopBaseException extends RuntimeException {

	private static final long serialVersionUID = -3929312467360542082L;

	/**
	 * SmartshopBaseException and its subclasses are a form of RuntimeException.
	 * SmartshopBaseException class acts as a base class for other Business
	 * Exception
	 */

	protected String errorCode;

	protected String errorMessage;

	protected Throwable throwable;

	protected Object[] arguments;

	public SmartshopBaseException() {
		super();
	}

	public SmartshopBaseException(String errorCode) {
		this(errorCode, null, new Object[] {});
		this.errorCode = errorCode;
	}

	public SmartshopBaseException(String errorCode, Object[] arguments) {
		this(errorCode, null, arguments);
	}

	public SmartshopBaseException(Throwable cause) {
		super(cause);
	}

	public SmartshopBaseException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public SmartshopBaseException(String errorMessage, Throwable throwable) {
		this(errorMessage, throwable, new Object[] {});
		this.errorMessage = errorMessage;
		this.throwable=throwable;

	}

	public SmartshopBaseException(String errorCode, Throwable throwable, Object[] arguments) {
		this.errorCode = errorCode;
		this.throwable = throwable;
		this.arguments = arguments == null ? new Object[] {} : arguments;
	}

	public SmartshopBaseException(String errorCode, String errorMessage, Throwable throwable, Object[] arguments) {
		super(throwable);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.throwable=throwable;
		this.arguments=arguments;
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

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	public Object[] getArguments() {
		return arguments;
	}

	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}
	

}