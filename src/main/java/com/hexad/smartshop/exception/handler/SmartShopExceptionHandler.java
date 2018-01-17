package com.hexad.smartshop.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hexad.smartshop.constants.ErrorMessageConstants;
import com.hexad.smartshop.exception.CustomerException;
import com.hexad.smartshop.exception.ErrorResponse;
import com.hexad.smartshop.exception.IErrorResponse;
import com.hexad.smartshop.exception.OrderException;
import com.hexad.smartshop.exception.SmartshopBaseException;

@ControllerAdvice
public class SmartShopExceptionHandler {

	@ExceptionHandler(Exception.class)
	public IErrorResponse exceptionHandler(Exception ex) {
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode(ErrorMessageConstants.APP_GENERIC_ERROR_ID);
		response.setErrorMessage(ErrorMessageConstants.APP_GENERIC_ERROR_VALUE);
		return response;
	}

	@ExceptionHandler(RuntimeException.class)
	public IErrorResponse exceptionUpdateCustomerHandler(SmartshopBaseException ex) {
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode(ex.getErrorCode());
		response.setErrorMessage(ex.getErrorMessage());
		response.setArgs(ex.getArguments());
		return response;
	}

	@ExceptionHandler({ CustomerException.class, OrderException.class})
	public IErrorResponse exceptionCustomerHandler(SmartshopBaseException ex) {
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode(ex.getErrorCode());
		response.setErrorMessage(ex.getErrorMessage());
		response.setArgs(ex.getArguments());
		return response;
	}

}
