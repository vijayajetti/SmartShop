package com.hexad.smartshop.init;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import com.hexad.smartshop.constants.ErrorMessageConstants;
import com.hexad.smartshop.data.CustomerInputConstants;
import com.hexad.smartshop.exception.OrderException;
import com.hexad.smartshop.model.Customer;
import com.hexad.smartshop.model.Order;
import com.hexad.smartshop.model.Product;
import com.hexad.smartshop.service.IOrderService;
import com.hexad.smartshop.utils.AppUtils;

public class OrderCreationTest {

	@InjectMocks
	private OrderCreation creation;

	@Mock
	private IOrderService orderService;

	private static Customer customer;

	private static Order order;

	private static Date orderDate, deliveryDate;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		orderDate = AppUtils.getCurrentDate();
		deliveryDate = AppUtils.getPlustHrsDateWithTime(1l);
		customer = new Customer(CustomerInputConstants.CUSTOMER_CREATION_NAME, CustomerInputConstants.CUSTOMER_CREATION_EMAIL_ID,
				CustomerInputConstants.CUSTOMER_CREATION_PHONE_NUMBER);
		order = new Order("1234", customer, orderDate, "NEW", deliveryDate, new BigDecimal(10));

	}

	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void testCreateCart() throws Exception {
		when(orderService.makeAnOrder(customer)).thenReturn(order);
		Order result = creation.createCart(customer);
		assertEquals("Verify the result ", order, result);
		verify(orderService, times(1)).makeAnOrder(customer);
	}

	@Test
	public void testCreateCartThrowException() {
		try {
			when(orderService.makeAnOrder(customer)).thenThrow(
					new OrderException(ErrorMessageConstants.ORDER_CREATING_ERROR_ID, ErrorMessageConstants.ORDER_CREATING_ERROR_VALUE));
			creation.createCart(customer);
		} catch (OrderException exception) {
			assertEquals("verify the Order Exception error message",
					"com.hexad.smartshop.exception.OrderException: " + ErrorMessageConstants.ORDER_CREATING_ERROR_VALUE,
					exception.getMessage());
		} catch (Exception exception) {
			fail("Should not reach here for OrderException " + exception.getMessage());
		}

	}
	
	@Test
	public void testAddProductToCart() {
		OrderCreation.addProductToCart(10001L, new BigDecimal(100));
	}

	@Test
	public void testAddProductToCartIfProductMapContainsSameProduct() {
		Map<Long, Product> productMap = new HashMap<Long, Product>();
		productMap.put(100L, new Product());
		ReflectionTestUtils.setField(creation, "productMap", productMap);
		OrderCreation.addProductToCart(100L, new BigDecimal(100));
	}
	
	@Test
	public void testRemoveProductFromCart() {
		OrderCreation.removeProductFromCart(10001L);
	}
	@Test
	public void testRemoveProductFromCartIfProductMapContainsSameProduct() {
		Map<Long, Product> productMap = new HashMap<Long, Product>();
		productMap.put(100L, new Product());
		ReflectionTestUtils.setField(creation, "productMap", productMap);
		OrderCreation.removeProductFromCart(100L);
	}
	
}
