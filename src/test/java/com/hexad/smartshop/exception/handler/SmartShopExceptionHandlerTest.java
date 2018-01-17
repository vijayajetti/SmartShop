package com.hexad.smartshop.exception.handler;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import com.hexad.smartshop.constants.ErrorMessageConstants;
import com.hexad.smartshop.exception.CustomerException;
import com.hexad.smartshop.exception.IErrorResponse;
import com.hexad.smartshop.exception.SmartshopBaseException;

public class SmartShopExceptionHandlerTest {
	
	@InjectMocks
	private SmartShopExceptionHandler smartShopExceptionHandler;
		
	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}
	
	@Test
	public void testExceptionUpdateCustomerHandler() {
		IErrorResponse errorResponse = smartShopExceptionHandler.exceptionUpdateCustomerHandler(new SmartshopBaseException(ErrorMessageConstants.CUSTOMER_NOT_FOUND_ID, ErrorMessageConstants.CUSTOMER_NOT_FOUND_VALUE));
		assertEquals("Verify the Error Code",ErrorMessageConstants.CUSTOMER_NOT_FOUND_ID, errorResponse.getErrorCode());
		assertEquals("Verify the Error Message", ErrorMessageConstants.CUSTOMER_NOT_FOUND_VALUE, errorResponse.getErrorMessage());

	}
	
	@Test()
	public void testExceptionHandler() {
		IErrorResponse errorResponse = smartShopExceptionHandler.exceptionHandler(new Exception(ErrorMessageConstants.APP_GENERIC_ERROR_ID));
		assertEquals("Verify the Error Code", ErrorMessageConstants.APP_GENERIC_ERROR_ID, errorResponse.getErrorCode());
		assertEquals("Verify the Error Message", ErrorMessageConstants.APP_GENERIC_ERROR_VALUE, errorResponse.getErrorMessage());

	}
	
	@Test
	public void testExceptionCustomerHandler() {
		IErrorResponse errorResponse = smartShopExceptionHandler.exceptionCustomerHandler(new CustomerException(ErrorMessageConstants.CUSTOMER_ALREADY_REGESTERED_ID, ErrorMessageConstants.CUSTOMER_ALREADY_REGESTERED_VALUE));
		assertEquals("Verify the Error Code", ErrorMessageConstants.CUSTOMER_ALREADY_REGESTERED_ID, errorResponse.getErrorCode());
		assertEquals("Verify the Error Message", ErrorMessageConstants.CUSTOMER_ALREADY_REGESTERED_VALUE, errorResponse.getErrorMessage());

	}

}
