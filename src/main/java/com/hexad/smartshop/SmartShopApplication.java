package com.hexad.smartshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.hexad.smartshop.customer.CustomerRegistration;

@SpringBootApplication
public class SmartShopApplication {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(SmartShopApplication.class, args);
		CustomerRegistration registeredCustomer = context.getBean(CustomerRegistration.class);
		registeredCustomer.getCustomerById(7);
	}
}
