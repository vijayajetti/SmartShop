package com.hexad.smartshop;

import java.util.List;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.hexad.smartshop.customer.CustomerRegistration;
import com.hexad.smartshop.data.CustomerInputConstants;
import com.hexad.smartshop.data.CustomerInputHelper;
import com.hexad.smartshop.data.OrderInputConstants;
import com.hexad.smartshop.data.OrderInputHelper;
import com.hexad.smartshop.model.Cart;
import com.hexad.smartshop.model.Customer;
import com.hexad.smartshop.model.Order;
import com.hexad.smartshop.model.Product;
import com.hexad.smartshop.mq.Producer;

@SpringBootApplication
public class SmartShopApplication {
	
	private final static Logger logger = LoggerFactory.getLogger(CustomerRegistration.class);
	
	@Bean
	public Queue queue() {
		return new ActiveMQQueue("wearhouse.queue");
	}
	
	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(SmartShopApplication.class, args);
		CustomerRegistration customerInit = context.getBean(CustomerRegistration.class);
		/*** Uncomment below for new Customer Registration*/
		customerInit.registerCustomer(CustomerInputHelper.getCustomer());
		/*** Uncomment below to update the existing Customer details*/
		customerInit.updateCustomer(CustomerInputHelper.getUpdateCustomer());
		/*** Uncomment below to get existing Customer details by Id*/
		Customer customer=customerInit.getCustomerById(CustomerInputConstants.CUSTOMER_GET_ID);
		
//		Customer customer = CustomerInputHelper.getCustomerForInput();
		List<Product> productList = OrderInputHelper.getProductList(OrderInputConstants.productPriceMap);
		Cart cart = OrderInputHelper.buildCart(customer, productList);
		customer.setCart(cart);
		Order orderMade = OrderInputHelper.buildOrder(customer);
		Producer producer = context.getBean(Producer.class);
		logger.info("Order has been placed with : "+orderMade.getOrderId());
		logger.info("Sending order details to warehouse");
		
		String[] orderData= {"Order Details of : "+orderMade.getCustomer().getCustomerName(),
							 "OrderId: "+orderMade.getOrderId(),
							 "OrderDate: "+orderMade.getDateOfOrder(),
							 "Current Status: "+orderMade.getCurrentStatus(),
							 "Expected Delivery Date: "+orderMade.getDeliveryDate(),
							 "Order Price: "+orderMade.getTotalPrice()};
		producer.run(orderData);
//		SpringApplication.exit(context, () -> 0);
	}
	
	
}
