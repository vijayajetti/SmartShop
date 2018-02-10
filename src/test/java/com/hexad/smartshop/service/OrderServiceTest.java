package com.hexad.smartshop.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.hexad.smartshop.constants.ErrorMessageConstants;
import com.hexad.smartshop.data.CustomerInputHelper;
import com.hexad.smartshop.exception.OrderException;
import com.hexad.smartshop.model.Customer;
import com.hexad.smartshop.model.Order;
import com.hexad.smartshop.mq.Producer;
import com.hexad.smartshop.repository.OrderRepository;
import com.hexad.smartshop.utils.TestUtils;

public class OrderServiceTest {
	
//	@Spy
	@InjectMocks
	private OrderService orderService;
	
	@Mock
	private OrderRepository orderRepository;
	
	private static Customer customer; 
	
	private static Order order;
	
	@Mock
	private Producer producer;
	
	@Captor
	private ArgumentCaptor<Order> orderCaptor;
	
	@Captor
	private ArgumentCaptor<String[]> orderDataCaptor;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customer = CustomerInputHelper.getCustomerWithId();
		
	}

	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void testMakeAnOrder() throws Exception{
		customer.setCart(TestUtils.getCartWithProductList());
		order = TestUtils.buildOrder(customer);
		when(orderRepository.save(orderCaptor.capture())).thenReturn(order);
		doNothing().when(producer).run(orderDataCaptor.capture());
		Order targetOrder = orderService.makeAnOrder(customer);
		assertSame("Verifying the result", order, targetOrder);
		
	}
	@Test
	public void testMakeAnOrderThrowExceptionIfCartDoesNotHaveAnyProducts() {
		try {
			orderService.makeAnOrder(customer);
			fail();
		} catch (IllegalArgumentException exception) {
			assertEquals("Verify the result ", ErrorMessageConstants.ORDER_AMOUNT_ZERO, exception.getMessage());
		} catch (Exception exception) {
			fail("Should not reach here for IllegalArgumentException " + exception.getMessage());
		}
	}
	
	@Test
	public void testMakeAnOrderThrowExceptionWhenUnableToSaveOrder() {
		customer.setCart(TestUtils.getCartWithProductList());
		try {
			when(orderRepository.save(orderCaptor.capture())).thenThrow(
					new OrderException(ErrorMessageConstants.ORDER_CREATING_ERROR_ID, ErrorMessageConstants.ORDER_CREATING_ERROR_VALUE));
			orderService.makeAnOrder(customer);
		} catch (OrderException exception) {
			assertEquals("verify the Order Exception error code", ErrorMessageConstants.ORDER_CREATING_ERROR_ID,	exception.getErrorCode());
			assertEquals("verify the Order Exception error message", ErrorMessageConstants.ORDER_CREATING_ERROR_VALUE, exception.getErrorMessage());
		} catch (Exception exception) {
			fail("Should not reach here for CustomerException " + exception.getMessage());
		}
	}
	
	@Test
	public void testMakeAnOrderThrowExceptionWhenUnableToSendOrderToWareHouse() {
		customer.setCart(TestUtils.getCartWithProductList());
		order = TestUtils.buildOrder(customer);
		try {
			when(orderRepository.save(orderCaptor.capture())).thenReturn(order);
			doThrow(new OrderException(ErrorMessageConstants.ORDER_SENDING_ERROR_ID, ErrorMessageConstants.ORDER_SENDING_ERROR_VALUE))
					.when(producer).run(orderDataCaptor.capture());
			orderService.makeAnOrder(customer);
		}catch(OrderException exception){
			assertEquals("verify the Order Exception error code", ErrorMessageConstants.ORDER_SENDING_ERROR_ID, exception.getErrorCode());
			assertEquals("verify the Order Exception error message", ErrorMessageConstants.ORDER_SENDING_ERROR_VALUE, exception.getErrorMessage());
		}catch(Exception exception) {
			fail("Should not reach here for Order Exception "+exception.getMessage());
		}
	} 
	
	/*@Test
	public void testSample() {
		doReturn("Success").when(orderService).test();
		String str = orderService.sample();
		System.out.println("RESULT : "+str);
	}*/

}
