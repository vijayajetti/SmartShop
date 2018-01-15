package com.hexad.smartshop.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.hexad.smartshop.constants.ErrorMessageConstants;
import com.hexad.smartshop.data.CustomerInputConstants;
import com.hexad.smartshop.data.CustomerInputHelper;
import com.hexad.smartshop.exception.CustomerException;
import com.hexad.smartshop.model.Address;
import com.hexad.smartshop.model.Customer;

public class CustomerDetailsRepositoryTest {

	@InjectMocks
	private CustomerDetailsRepository customerRepository;

	@Mock
	private EntityManager entityManager;

	@Mock
	private Query query;
	
	@Captor
	private ArgumentCaptor<String> sql;

	private static Customer customer, updateCustomer;

	@BeforeClass
	public static void setUpClass() throws Exception {
		customer = CustomerInputHelper.getCustomerWithId();
		updateCustomer = CustomerInputHelper.getUpdateCustomer();
	}

	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void testRegisterCustomer() throws Exception {
		when(entityManager.createNativeQuery(sql.capture())).thenReturn(query);
		when(query.getSingleResult()).thenReturn(BigInteger.ZERO);
		doNothing().when(entityManager).persist(customer);
		Integer response = customerRepository.registerCustomer(customer);
		assertEquals("Verify the Customer Id ", customer.getCustomerId(), response);
		verify(entityManager, times(1)).createNativeQuery(sql.capture());
		verify(query, times(1)).getSingleResult();
	}
	
	@Test
	public void testRegisterCustomerThrowsCustomerExceptionIfPhoneNumberAlreadyExist() throws Exception {
		try {
		customer.setPhoneNumber(CustomerInputConstants.CUSTOMER_CREATION_PHONE_NUMBER);
		when(entityManager.createNativeQuery(sql.capture())).thenReturn(query);
		when(query.getSingleResult()).thenReturn(BigInteger.ONE);
		customerRepository.registerCustomer(customer);
		fail();
		}catch(CustomerException cx) {
			assertEquals("Verify the Error Code", ErrorMessageConstants.CUSTOMER_ALREADY_REGESTERED_ID, cx.getErrorCode());
			assertEquals("Verify the Error Message",ErrorMessageConstants.CUSTOMER_ALREADY_REGESTERED_VALUE,cx.getErrorMessage());
		}catch(Exception e) {
			fail();
		}
	}

	@Test
	public void testGetCustomerById() throws Exception {
		
		Address addr = new Address();
		addr.setCustomer(customer);
		List<Address> addressList = Arrays.asList(addr);
		when(entityManager.createQuery(sql.capture())).thenReturn(query);
		when(query.getResultList()).thenReturn(addressList);
		Customer cust = customerRepository.getCustomerById(1);
        assertEquals("Verify the Customer Id", customer.getCustomerId(), cust.getCustomerId());
        assertEquals("Verify the Customer Name", customer.getCustomerName(), cust.getCustomerName());
        assertEquals("Verify the Email Id", customer.getEmailId(), cust.getEmailId());
		verify(entityManager, times(1)).createQuery(sql.capture());
		verify(query, times(1)).getResultList();
	}

	@Test
	public void testUpdateCustomer() throws Exception {
		
		Address addr = new Address();
		addr.setCustomer(updateCustomer);
		List<Address> addressList = Arrays.asList(addr);
		when(entityManager.createQuery(sql.capture())).thenReturn(query);
		when(query.getResultList()).thenReturn(addressList);
		Customer cust = customerRepository.updateCustomer(updateCustomer);
        assertEquals("Verify the Customer Id", updateCustomer.getCustomerId(), cust.getCustomerId());
        assertEquals("Verify the Customer Name", updateCustomer.getCustomerName(), cust.getCustomerName());
        assertEquals("Verify the Email Id", updateCustomer.getEmailId(), cust.getEmailId());
		verify(entityManager, times(2)).createQuery(sql.capture());
		verify(query, times(2)).getResultList();
	}
	
	@Test
	public void testUpdateCustomerThrowsCustomerExceptionIfCustomerDetailsAreNotFound() throws Exception {
	 try {	
		Address addr = new Address();
		List<Address> addressList = Arrays.asList(addr);
		when(entityManager.createQuery(sql.capture())).thenReturn(query);
		when(query.getResultList()).thenReturn(addressList);
		customerRepository.updateCustomer(updateCustomer);
		fail();
		}catch(CustomerException cx) {
			assertEquals("Verify the Error Code", ErrorMessageConstants.CUSTOMER_NOT_FOUND_ID, cx.getErrorCode());
			assertEquals("Verify the Error Message",ErrorMessageConstants.CUSTOMER_NOT_FOUND_VALUE,cx.getErrorMessage());
		}catch(Exception e) {
			fail();
		}
	}

}
