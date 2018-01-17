package com.hexad.smartshop.data;

import com.hexad.smartshop.model.Address;
import com.hexad.smartshop.model.Customer;
import com.hexad.smartshop.utils.AppUtils;

public class CustomerInputHelper {
	
	public static Customer getCustomer() throws Exception {
		Customer customer = new Customer();
		customer.setPassword(AppUtils.encryptPassword(CustomerInputConstants.CUSTOMER_CREATION_PASSWORD));
		customer.setCustomerName(CustomerInputConstants.CUSTOMER_CREATION_NAME);
		customer.setPhoneNumber(CustomerInputConstants.CUSTOMER_CREATION_PHONE_NUMBER);
		customer.setEmailId(CustomerInputConstants.CUSTOMER_CREATION_EMAIL_ID);
		customer.setDateOfRegistration(AppUtils.getCurrentDate());
		addAddresses(customer);
		return customer;
	}

	public static Customer getUpdateCustomer() throws Exception {
		Customer updateCustomer= new Customer();
		updateCustomer.setCustomerId(CustomerInputConstants.CUSTOMER_UPDATION_ID);
		updateCustomer.setPassword(AppUtils.encryptPassword(CustomerInputConstants.CUSTOMER_CREATION_PASSWORD));
		updateCustomer.setCustomerName(CustomerInputConstants.CUSTOMER_UDATE_NAME);
		updateCustomer.setEmailId(CustomerInputConstants.CUSTOMER_UPDATE_EMAIL_ID);
		updateCustomer.setPhoneNumber(CustomerInputConstants.CUSTOMER_UPDATION_PHONE_NUMBER);
		updateCustomer.setDateOfRegistration(AppUtils.getCurrentDate());
		addUpdateAddresses(updateCustomer);
		return updateCustomer;
	}

	private static void addAddresses(Customer customer) {
		Address address1 = new Address(CustomerInputConstants.CUSTOMER_ADDRESS1_STREET, CustomerInputConstants.CUSTOMER_ADDRESS1_CITY, CustomerInputConstants.CUSTOMER_ADDRESS1_STATE, CustomerInputConstants.CUSTOMER_ADDRESS1_PIN);
		Address address2 = new Address(CustomerInputConstants.CUSTOMER_ADDRESS2_STREET, CustomerInputConstants.CUSTOMER_ADDRESS2_CITY, CustomerInputConstants.CUSTOMER_ADDRESS2_STATE, CustomerInputConstants.CUSTOMER_ADDRESS2_PIN);
		Address address3 = new Address(CustomerInputConstants.CUSTOMER_ADDRESS3_STREET, CustomerInputConstants.CUSTOMER_ADDRESS3_CITY, CustomerInputConstants.CUSTOMER_ADDRESS3_STATE, CustomerInputConstants.CUSTOMER_ADDRESS3_PIN);
		customer.addAddress(address1);
		customer.addAddress(address2);
		customer.addAddress(address3);
	}
	private static void addUpdateAddresses(Customer customer) {
		Address address1 = new Address(CustomerInputConstants.CUSTOMER_ADDRESS1_UPDATE_STREET, CustomerInputConstants.CUSTOMER_ADDRESS1_UPDATE_CITY, CustomerInputConstants.CUSTOMER_ADDRESS1_UPDATE_STATE, CustomerInputConstants.CUSTOMER_ADDRESS1_UPDATE_PIN);
		address1.setAddressId(CustomerInputConstants.CUSTOMER_ADDRESS1_ID);
		Address address2 = new Address(CustomerInputConstants.CUSTOMER_ADDRESS2_UPDATE_STREET, CustomerInputConstants.CUSTOMER_ADDRESS2_UPDATE_CITY, CustomerInputConstants.CUSTOMER_ADDRESS2_UPDATE_STATE, CustomerInputConstants.CUSTOMER_ADDRESS2_UPDATE_PIN);
		address2.setAddressId(CustomerInputConstants.CUSTOMER_ADDRESS2_ID);
		Address address3 = new Address(CustomerInputConstants.CUSTOMER_ADDRESS3_UPDATE_STREET, CustomerInputConstants.CUSTOMER_ADDRESS3_UPDATE_CITY, CustomerInputConstants.CUSTOMER_ADDRESS3_UPDATE_STATE, CustomerInputConstants.CUSTOMER_ADDRESS3_UPDATE_PIN);
		address3.setAddressId(CustomerInputConstants.CUSTOMER_ADDRESS3_ID);
		customer.addAddress(address1);
		customer.addAddress(address2);
		customer.addAddress(address3);
	}

	public static Customer getCustomerWithId() throws Exception {
		Customer testCustomer = getCustomer();
		testCustomer.setCustomerId(CustomerInputConstants.CUSTOMER_CREATION_ID);
		return testCustomer;
	}
}
