package com.hexad.smartshop.exception;

import java.util.List;

public class CustomerException extends Exception {

	private static final long serialVersionUID = -1458783884772948561L;
	public static final int ERROR_VALIDATION_SAVE = 100;

	private String errorMessage;

	private List<String> errorFields;

	private int errorCode = 0;

	public String getErrorMessage() {
		return errorMessage;
	}

	public CustomerException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public CustomerException() {
		super();
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public CustomerException(Exception e) {
		super(e);
	}

	public CustomerException(String message, Exception e) {
		super(message, e);
	}

	public CustomerException(int code, String message) {
		super(message);
		this.errorCode = code;
	}

	public CustomerException(int code) {
		this.errorCode = code;
	}

	public void setErrorFields(List<String> errorFields) {
		this.errorFields = errorFields;
	}

	public List<String> getErrorFields() {
		return errorFields;
	}
}
