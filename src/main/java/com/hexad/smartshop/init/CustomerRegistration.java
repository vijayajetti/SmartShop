package com.hexad.smartshop.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.hexad.smartshop.constants.ErrorMessageConstants;
import com.hexad.smartshop.exception.CustomerException;
import com.hexad.smartshop.logging.SmartShopLoggingHelper;
import com.hexad.smartshop.model.Customer;
import com.hexad.smartshop.service.ICustomerDetailsService;

@Configuration
public class CustomerRegistration {

	private static String CLASS_NAME = CustomerRegistration.class.getName();

	@Autowired
	private ICustomerDetailsService customerService;

	public Customer getCustomerById(Integer customerId) {
		String methodName = CLASS_NAME + ".getCustomerById()";
		SmartShopLoggingHelper.debug(methodName, ErrorMessageConstants.START_METHOD);
		Customer customer = null;
		if (customerId == null) {
			SmartShopLoggingHelper.severe(methodName, ErrorMessageConstants.INPUT_IS_NULL, new Object[] {});
			throw new IllegalArgumentException(ErrorMessageConstants.INPUT_IS_NULL);
		}
		try {
			customer = customerService.getCustomerById(customerId);
		} catch (Exception exception) {
			SmartShopLoggingHelper.severe(ErrorMessageConstants.CUSTOMER_NOT_FOUND_ID,
					ErrorMessageConstants.CUSTOMER_NOT_FOUND_VALUE, new Object[] { exception.getMessage() }, exception);
			throw new CustomerException(ErrorMessageConstants.CUSTOMER_NOT_FOUND_ID,
					ErrorMessageConstants.CUSTOMER_NOT_FOUND_VALUE, exception, new Object[] { exception.getMessage() });
		}
		SmartShopLoggingHelper.debug(methodName, ErrorMessageConstants.END_METHOD);
		return customer;
	}

	public Customer registerCustomer(Customer customer) {
		String methodName = CLASS_NAME + ".registerCustomer()";
		SmartShopLoggingHelper.debug(methodName, ErrorMessageConstants.START_METHOD);
		Customer registeredCustomer=null;
		try {
			registeredCustomer=customerService.registerCustomer(customer);
		}catch (Exception exception) {
			SmartShopLoggingHelper.severe(ErrorMessageConstants.CUSTOMER_CREATING_ERROR_ID,
					ErrorMessageConstants.CUSTOMER_CREATING_ERROR_VALUE, new Object[] { exception.getMessage() }, exception);
			throw new CustomerException(ErrorMessageConstants.CUSTOMER_CREATING_ERROR_ID,
					ErrorMessageConstants.CUSTOMER_CREATING_ERROR_VALUE, exception, new Object[] { exception.getMessage() });
		}
		SmartShopLoggingHelper.debug(methodName, ErrorMessageConstants.END_METHOD);
		 return registeredCustomer;
	}

	public Customer updateCustomer(Customer customer) throws CustomerException {
		String methodName = CLASS_NAME + ".updateCustomer()";
		SmartShopLoggingHelper.debug(methodName, ErrorMessageConstants.START_METHOD);
		Customer updatedCustomer=null;
		try {
			updatedCustomer=customerService.updateCustomer(customer);
		} catch (Exception exception) {
			SmartShopLoggingHelper.severe(ErrorMessageConstants.CUSTOMER_UPDATING_ERROR_ID,
					ErrorMessageConstants.CUSTOMER_UPDATING_ERROR_VALUE, new Object[] { exception.getMessage() }, exception);
			throw new CustomerException(ErrorMessageConstants.CUSTOMER_UPDATING_ERROR_ID,
					ErrorMessageConstants.CUSTOMER_UPDATING_ERROR_VALUE, exception, new Object[] { exception.getMessage() });
		}
		SmartShopLoggingHelper.debug(methodName, ErrorMessageConstants.END_METHOD);
		return updatedCustomer;
	}

}
