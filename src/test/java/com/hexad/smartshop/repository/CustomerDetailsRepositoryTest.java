package com.hexad.smartshop.repository;

import static org.mockito.MockitoAnnotations.initMocks;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CustomerDetailsRepositoryTest {

	@InjectMocks
	private CustomerDetailsRepository customerRepository;

	@Mock
	private EntityManager entityManager;

	@Mock
	private Query query;

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
	}

	@Test
	public void testGetCustomerById() throws Exception {
		
	}

	@Test
	public void testUpdateCustomer() throws Exception {
	}

}
