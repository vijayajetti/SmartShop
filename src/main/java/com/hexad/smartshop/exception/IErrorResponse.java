package com.hexad.smartshop.exception;

public interface IErrorResponse {
	
	public String getErrorCode();

	public void setErrorCode(String errorCode);

	public void setErrorMessage(String msg);

	public String getErrorMessage();
	
	public Object[] getArgs(); 

	public void setArgs(Object[] args); 

}
