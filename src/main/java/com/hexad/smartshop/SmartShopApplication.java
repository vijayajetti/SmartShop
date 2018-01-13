package com.hexad.smartshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.hexad.smartshop.customer.CustomerRegistration;

@SpringBootApplication
public class SmartShopApplication {

	public static void main(String[] args) throws Exception {
		
		ApplicationContext context = SpringApplication.run(SmartShopApplication.class, args);
		CustomerRegistration customerInit = context.getBean(CustomerRegistration.class);
		/*** Uncomment below for new Customer Registration*/
//		customerInit.registerCustomer(CustTestData.getCustomer());
		/*** Uncomment below to update the existing Customer details*/
//		customerInit.updateCustomer(CustTestData.getUpdateCustomer());
		customerInit.getCustomerById(CustTestData.CUSTOMER_GET_ID);
		SpringApplication.exit(context, () -> 0);
	}
}
