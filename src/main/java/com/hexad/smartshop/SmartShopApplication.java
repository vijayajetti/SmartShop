package com.hexad.smartshop;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.hexad.smartshop.data.CustomerInputHelper;
import com.hexad.smartshop.data.OrderInputConstants;
import com.hexad.smartshop.init.CustomerRegistration;
import com.hexad.smartshop.init.OrderCreation;
import com.hexad.smartshop.model.Customer;
import com.hexad.smartshop.model.Order;

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
		Customer customer = customerInit.registerCustomer(CustomerInputHelper.getCustomer());
		/*** Uncomment below to update the existing Customer details*/
//		customerInit.updateCustomer(CustomerInputHelper.getUpdateCustomer());
		/*** Uncomment below to get existing Customer details by Id*/
//		Customer customer=customerInit.getCustomerById(CustomerInputConstants.CUSTOMER_GET_ID);
		OrderCreation cartInit=context.getBean(OrderCreation.class);
		OrderCreation.addProductToCart(OrderInputConstants.PRODUCT_ID_ALMOND, OrderInputConstants.PRODUCT_PRICE_ALMOND);
		OrderCreation.addProductToCart(OrderInputConstants.PRODUCT_ID_RICE, OrderInputConstants.PRODUCT_PRICE_RICE);
		OrderCreation.addProductToCart(OrderInputConstants.PRODUCT_ID_OIL, OrderInputConstants.PRODUCT_PRICE_OIL);
		OrderCreation.addProductToCart(OrderInputConstants.PRODUCT_ID_SUGAR, OrderInputConstants.PRODUCT_PRICE_SUGAR);
		OrderCreation.addProductToCart(OrderInputConstants.PRODUCT_ID_WHEAT, OrderInputConstants.PRODUCT_PRICE_WHEAT);
		OrderCreation.addProductToCart(OrderInputConstants.PRODUCT_ID_ALMOND, OrderInputConstants.PRODUCT_PRICE_ALMOND);
		Order order=cartInit.createCart(customer);
		logger.info("Order has been placed with : "+order.getOrderId());
	}
	
	
}
