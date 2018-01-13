package com.hexad.smartshop.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexad.smartshop.TestUtils;
import com.hexad.smartshop.model.Customer;
import com.hexad.smartshop.repository.ICustomerDetailsRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CustomerDetailsServiceTest {

	@InjectMocks
	private CustomerDetailsService customerDetailsService;

	@Mock
	private ICustomerDetailsRepository customerRepository;

//	private static Address address1, address2, address3;

	private static Customer customer, updateCustomer;

//	private static List<Address> addressList;

	@BeforeClass
	public static void setUpClass() {
		customer = TestUtils.getCustomer();
		updateCustomer = TestUtils.getUpdateCustomer();
	}

	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void testRegisterCustomer() throws Exception {
		assertNotNull(customer);
		when(customerRepository.registerCustomer(customer)).thenReturn(1000);
		Integer customerId = customerDetailsService.registerCustomer(customer);
		assertEquals("Verify result ", new Integer(1000), customerId);
		verify(customerRepository, times(1)).registerCustomer(customer);
	}

	/*@Test
	public void testRegisterCustomerVerifyCustId() throws Exception {
		assertNotNull(customer);
		when(customerRepository.registerCustomer(customer)).thenReturn(1000);
		Integer result = customerDetailsService.registerCustomer(customer);
		assertEquals(new Integer(1000), result);
	}*/

	@Test
	public void testUpdateCustomer() throws Exception {
		assertNotNull(updateCustomer);
		when(customerRepository.updateCustomer(updateCustomer)).thenReturn(updateCustomer);
		Customer result = customerDetailsService.updateCustomer(updateCustomer);
		assertEquals("Verify data ", TestUtils.CUSTOMER_UPDATE_EMAIL_ID, result.getEmailId());
		assertEquals("Verify result ", updateCustomer, result);
		verify(customerRepository, times(1)).updateCustomer(updateCustomer);

	}

	@Test
	public void testGetCustomerById() throws Exception {
		assertNotNull(customer);
		when(customerRepository.getCustomerById(customer.getCustomerId())).thenReturn(customer);
		Customer result = customerDetailsService.getCustomerById(customer.getCustomerId());
		assertEquals("Verify result ", customer, result);
		verify(customerRepository, times(1)).getCustomerById(customer.getCustomerId());
	}

}