package com.hexad.smartshop.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.hexad.smartshop.constants.ErrorMessageConstants;
import com.hexad.smartshop.data.CustomerInputConstants;
import com.hexad.smartshop.data.CustomerInputHelper;
import com.hexad.smartshop.exception.CustomerException;
import com.hexad.smartshop.model.Customer;
import com.hexad.smartshop.repository.CustomerRepository;

public class CustomerDetailsServiceTest {

	@InjectMocks
	private CustomerDetailsService customerDetailsService;

	@Mock
	private CustomerRepository customerRepository;

	private static Customer customer, updateCustomer;

	@BeforeClass
	public static void setUpClass() throws Exception{
		customer = CustomerInputHelper.getCustomerWithId();
		updateCustomer = CustomerInputHelper.getUpdateCustomer();
	}

	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void testRegisterCustomer() throws Exception {
		assertNotNull(customer);
		when(customerRepository.save(customer)).thenReturn(customer);
		Customer result = customerDetailsService.registerCustomer(customer);
		assertEquals("Verify the data ", CustomerInputConstants.CUSTOMER_CREATION_ID, result.getCustomerId());
		assertEquals("Verify result ", customer, result);
		verify(customerRepository, times(1)).save(customer);
	}
	
	@Test
	public void testRegisterCustomerThrowExceptionWhenUnableToRegisterCustomer() {
		try {
			when(customerRepository.save(customer)).thenThrow(new CustomerException(ErrorMessageConstants.CUSTOMER_CREATING_ERROR_ID,
					ErrorMessageConstants.CUSTOMER_CREATING_ERROR_VALUE));
			customerDetailsService.registerCustomer(customer);
		} catch (CustomerException exception) {
			assertEquals("Verify the CustomerException error code ", ErrorMessageConstants.CUSTOMER_CREATING_ERROR_ID, exception.getErrorCode());
			assertEquals("Verify the CustomerException error message ", ErrorMessageConstants.CUSTOMER_CREATING_ERROR_VALUE, exception.getErrorMessage());
		} catch (Exception exception) {
			fail("Should not reach here for CustomerException " + exception.getMessage());
		}
	}

	@Test
	public void testUpdateCustomer() throws Exception {
		assertNotNull(updateCustomer);
		when(customerRepository.save(updateCustomer)).thenReturn(updateCustomer);
		Customer result = customerDetailsService.updateCustomer(updateCustomer);
		assertEquals("Verify data ", CustomerInputConstants.CUSTOMER_UPDATE_EMAIL_ID, result.getEmailId());
		assertEquals("Verify result ", updateCustomer, result);
		verify(customerRepository, times(1)).save(updateCustomer);

	}
	
	@Test
	public void testUpdateCustomerThrowExceptionWhenUnableToUpdateCustomer() {
		try {
			when(customerRepository.save(updateCustomer)).thenThrow(new CustomerException(ErrorMessageConstants.CUSTOMER_UPDATING_ERROR_ID,
					ErrorMessageConstants.CUSTOMER_UPDATING_ERROR_VALUE));
			customerDetailsService.updateCustomer(updateCustomer);
		} catch (CustomerException exception) {
			assertEquals("Verify the CustomerException error code ", ErrorMessageConstants.CUSTOMER_UPDATING_ERROR_ID, exception.getErrorCode());
			assertEquals("Verify the CustomerException error message ", ErrorMessageConstants.CUSTOMER_UPDATING_ERROR_VALUE, exception.getErrorMessage());
		} catch (Exception exception) {
			fail("Should not reach here for CustomerException " + exception.getMessage());
		}
	}

	@Test
	public void testGetCustomerById() throws Exception {
		assertNotNull(customer);
		when(customerRepository.findOne(customer.getCustomerId())).thenReturn(customer);
		Customer result = customerDetailsService.getCustomerById(customer.getCustomerId());
		assertEquals("Verify result ", customer, result);
		verify(customerRepository, times(1)).findOne(customer.getCustomerId());
	}
	
	@Test
	public void testGetCustomerByIdThrowExceptionWhenCustomerNotFound() {
		try {
			when(customerRepository.findOne(customer.getCustomerId())).thenThrow(
					new CustomerException(ErrorMessageConstants.CUSTOMER_NOT_FOUND_ID, ErrorMessageConstants.CUSTOMER_NOT_FOUND_VALUE));
			customerDetailsService.getCustomerById(customer.getCustomerId());
		} catch (CustomerException exception) {
			assertEquals("Verify the CustomerException error code ", ErrorMessageConstants.CUSTOMER_NOT_FOUND_ID, exception.getErrorCode());
			assertEquals("Verify the CustomerException error message ", ErrorMessageConstants.CUSTOMER_NOT_FOUND_VALUE, exception.getErrorMessage());
		} catch (Exception exception) {
			fail("Should not reach here for CustomerException " + exception.getMessage());
		}
	}

}
