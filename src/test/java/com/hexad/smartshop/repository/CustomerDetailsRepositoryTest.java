package com.hexad.smartshop.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexad.smartshop.TestUtils;
import com.hexad.smartshop.constants.ErrorMessageConstants;
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
		customer.setPhoneNumber(TestUtils.CUSTOMER_CREATION_PHONE_NUMBER);
		when(entityManager.createNativeQuery(sql.capture())).thenReturn(query);
		when(query.getSingleResult()).thenReturn(BigInteger.ONE);
		customerRepository.registerCustomer(customer);
		fail();
		}catch(CustomerException cx) {
			assertEquals("Verify the Error Code", 0, cx.getErrorCode());
			assertEquals("Verify the Error Message",ErrorMessageConstants.CUSTOMER_CONFLICT,cx.getErrorMessage());
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
		addr.setCustomer(customer);
		List<Address> addressList = Arrays.asList(addr);
		when(entityManager.createQuery(sql.capture())).thenReturn(query);
		when(query.getResultList()).thenReturn(addressList);
		Customer cust = customerRepository.updateCustomer(customer);
        assertEquals("Verify the Customer Id", customer.getCustomerId(), cust.getCustomerId());
        assertEquals("Verify the Customer Name", customer.getCustomerName(), cust.getCustomerName());
        assertEquals("Verify the Email Id", customer.getEmailId(), cust.getEmailId());
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
		customerRepository.updateCustomer(customer);
		fail();
		}catch(CustomerException cx) {
			assertEquals("Verify the Error Code", 0, cx.getErrorCode());
			assertEquals("Verify the Error Message",ErrorMessageConstants.CUSTOMER_NOT_FOUND,cx.getErrorMessage());
		}catch(Exception e) {
			fail();
		}
	}

}
