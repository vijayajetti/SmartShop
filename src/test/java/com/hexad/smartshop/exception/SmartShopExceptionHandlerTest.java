package com.hexad.smartshop.exception;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import com.hexad.smartshop.constants.ErrorMessageConstants;

public class SmartShopExceptionHandlerTest {
	
	@InjectMocks
	private SmartShopExceptionHandler SmartShopExceptionHandler;
		
	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}
	
	@Test
	public void testExceptionUpdateCustomerHandler() {
		IErrorResponse errorResponse = SmartShopExceptionHandler.exceptionUpdateCustomerHandler(new SmartshopBaseException(ErrorMessageConstants.CUSTOMER_NOT_FOUND_ID, ErrorMessageConstants.CUSTOMER_NOT_FOUND_VALUE));
		assertEquals("Verify the Error Code",ErrorMessageConstants.CUSTOMER_NOT_FOUND_ID, errorResponse.getErrorCode());
		assertEquals("Verify the Error Message", ErrorMessageConstants.CUSTOMER_NOT_FOUND_VALUE, errorResponse.getErrorMessage());

	}
	
	@Test()
	public void testExceptionHandler() {
		IErrorResponse errorResponse = SmartShopExceptionHandler.exceptionHandler(new Exception(ErrorMessageConstants.APP_GENERIC_ERROR_ID));
		assertEquals("Verify the Error Code", ErrorMessageConstants.APP_GENERIC_ERROR_ID, errorResponse.getErrorCode());
		assertEquals("Verify the Error Message", ErrorMessageConstants.APP_GENERIC_ERROR_VALUE, errorResponse.getErrorMessage());

	}
	
	@Test
	public void testExceptionCustomerHandler() {
		IErrorResponse errorResponse = SmartShopExceptionHandler.exceptionCustomerHandler(new CustomerException(ErrorMessageConstants.CUSTOMER_ALREADY_REGESTERED_ID, ErrorMessageConstants.CUSTOMER_ALREADY_REGESTERED_VALUE));
		assertEquals("Verify the Error Code", ErrorMessageConstants.CUSTOMER_ALREADY_REGESTERED_ID, errorResponse.getErrorCode());
		assertEquals("Verify the Error Message", ErrorMessageConstants.CUSTOMER_ALREADY_REGESTERED_VALUE, errorResponse.getErrorMessage());

	}

}
