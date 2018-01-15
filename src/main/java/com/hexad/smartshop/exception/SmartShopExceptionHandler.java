package com.hexad.smartshop.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hexad.smartshop.constants.ErrorMessageConstants;

@ControllerAdvice
public class SmartShopExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public IErrorResponse exceptionUpdateCustomerHandler(SmartshopBaseException ex) {
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode(ex.getErrorCode());
		response.setErrorMessage(ex.getErrorMessage());
		return response;
	}
	
	@ExceptionHandler(Exception.class)
	public IErrorResponse exceptionHandler(Exception ex) {
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode(ErrorMessageConstants.APP_GENERIC_ERROR_ID);
		response.setErrorMessage(ErrorMessageConstants.APP_GENERIC_ERROR_VALUE);
		return response;
	}
	
	@ExceptionHandler(CustomerException.class)
	public IErrorResponse exceptionCustomerHandler(CustomerException ex) {
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode(ex.getErrorCode());
		response.setErrorMessage(ex.getErrorMessage());
		return response;
	}

}
