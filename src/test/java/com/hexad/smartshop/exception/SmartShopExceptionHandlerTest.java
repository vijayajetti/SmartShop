package com.hexad.smartshop.exception;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SmartShopExceptionHandlerTest {
	
	@InjectMocks
	private SmartShopExceptionHandler SmartShopExceptionHandler;
		
	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}
	
	@Test
	public void testExceptionUpdateCustomerHandler() {
		ResponseEntity<ErrorResponse> errorResponse = SmartShopExceptionHandler.exceptionUpdateCustomerHandler(new Exception());
		assertEquals("Verify the Status Code",HttpStatus.NOT_FOUND, errorResponse.getStatusCode());
		assertEquals("Verify the Error Code", HttpStatus.NOT_FOUND.value(), errorResponse.getBody().getErrorCode());

	}
	
	@Test
	public void testExceptionHandler() {
		ResponseEntity<ErrorResponse> errorResponse = SmartShopExceptionHandler.exceptionHandler(new Exception());
		assertEquals("Verify the Status Code", HttpStatus.BAD_REQUEST, errorResponse.getStatusCode());
		assertEquals("Verify the Error Code", HttpStatus.BAD_REQUEST.value(), errorResponse.getBody().getErrorCode());

	}
	
	@Test
	public void testExceptionCustomerHandler() {
		ResponseEntity<ErrorResponse> errorResponse = SmartShopExceptionHandler.exceptionCustomerHandler(new Exception());
		assertEquals("Verify the Status Code", HttpStatus.CONFLICT, errorResponse.getStatusCode());
		assertEquals("Verify the Error Code", HttpStatus.CONFLICT.value(), errorResponse.getBody().getErrorCode());

	}

}
