package com.hexad.smartshop.init;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.hexad.smartshop.data.CustomerInputConstants;
import com.hexad.smartshop.data.CustomerInputHelper;
import com.hexad.smartshop.model.Customer;
import com.hexad.smartshop.service.ICustomerDetailsService;


public class CustomerRegistrationTest {

	@InjectMocks
	private CustomerRegistration controller;

	@Mock
	private ICustomerDetailsService customerDetailsService;

	private static Customer customer, updateCustomer;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customer = CustomerInputHelper.getCustomerWithId();
		updateCustomer = CustomerInputHelper.getUpdateCustomer();
	}

	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void testGetCustomerById() throws Exception{
		assertNotNull(customer);
		when(customerDetailsService.getCustomerById(customer.getCustomerId())).thenReturn(customer);
		Customer result = controller.getCustomerById(customer.getCustomerId());
		assertEquals("Verify the result ", customer, result);
		assertEquals(3, result.getAddresses().size());
		verify(customerDetailsService, times(1)).getCustomerById(customer.getCustomerId());
	}

	@Test
	public void testRegisterCustomer() throws Exception{
		assertNotNull(customer);
		when(customerDetailsService.registerCustomer(customer)).thenReturn(customer);
		Customer result=controller.registerCustomer(customer);
		assertEquals("Verify the result ", customer, result);
		verify(customerDetailsService, times(1)).registerCustomer(customer);
	}

	@Test
	public void testUpdateCustomer() throws Exception{
		assertNotNull(updateCustomer);
		when(customerDetailsService.updateCustomer(updateCustomer)).thenReturn(updateCustomer);
		Customer result = controller.updateCustomer(updateCustomer);
		assertEquals("Verify the data ", CustomerInputConstants.CUSTOMER_UDATE_NAME, result.getCustomerName());
		assertEquals("Verify the result ", updateCustomer, result);
		verify(customerDetailsService, times(1)).updateCustomer(updateCustomer);
	}

}
