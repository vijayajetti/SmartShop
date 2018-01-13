package com.hexad.smartshop.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.hexad.smartshop.exception.CustomerException;
import com.hexad.smartshop.model.Customer;
import com.hexad.smartshop.service.ICustomerDetailsService;

@Configuration
public class CustomerRegistration {

	private final Logger logger = LoggerFactory.getLogger(CustomerRegistration.class);

	private static String CLASS_NAME = CustomerRegistration.class.getName();

	@Autowired
	private ICustomerDetailsService customerService;

	public Customer getCustomerById(Integer customerId) throws CustomerException {
		String methodName = CLASS_NAME + ".getCustomerById()";
		logger.info(methodName);
		Customer customer = customerService.getCustomerById(customerId);
		logger.info("Customer Id:" + customer.getCustomerId());
		logger.info("Customer Name:" + customer.getCustomerName());
		logger.info("Customer Addresses:" + customer.getAddresses().size());
		return customer;
	}

	public Integer registerCustomer(Customer customer) throws CustomerException {
		String methodName = CLASS_NAME + ".registerCustomer()";
		logger.info(methodName);
		logger.info("Registering Customer:" + customer.getCustomerId());
		return customerService.registerCustomer(customer);
	}

	public Customer updateCustomer(Customer customer) throws CustomerException {
		String methodName = CLASS_NAME + ".updateCustomer()";
		logger.info(methodName);
		logger.info("Update Customer:" + customer.getCustomerId());
		return customerService.updateCustomer(customer);
	}

}
