package com.hexad.smartshop;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hexad.smartshop.model.Address;
import com.hexad.smartshop.model.Customer;

public class TestUtils {

	public static final Integer CUSTOMER_CREATION_ID = 1000;

	public static final String CUSTOMER_CREATION_PASSWORD = "password";

	public static final String CUSTOMER_CREATION_NAME = "Vijay Jetti";
	
	public static final String CUSTOMER_UDATE_NAME = "Vijay Bhaskar Jetti";

	public static final String CUSTOMER_CREATION_EMAIL_ID = "vijay@gmail.com";
	
	public static final String CUSTOMER_UPDATE_EMAIL_ID = "vijaybhaskar@gmail.com";

	public static final String CUSTOMER_CREATION_PHONE_NUMBER = "8880775555";

	public static final Date CUSTOMER_CREATION_DATE_OF_REG = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

	public static final Long CUSTOMER_ADDRESS1_ID = new Long(100);

	public static final String CUSTOMER_ADDRESS1_STREET = "street1";

	public static final String CUSTOMER_ADDRESS1_CITY = "city1";

	public static final String CUSTOMER_ADDRESS1_STATE = "state1";

	public static final String CUSTOMER_ADDRESS1_PIN = "560036";

	public static final Long CUSTOMER_ADDRESS2_ID = new Long(200);

	public static final String CUSTOMER_ADDRESS2_STREET = "street2";

	public static final String CUSTOMER_ADDRESS2_CITY = "city2";

	public static final String CUSTOMER_ADDRESS2_STATE = "state2";

	public static final String CUSTOMER_ADDRESS2_PIN = "560037";

	public static final Long CUSTOMER_ADDRESS3_ID = new Long(300);

	public static final String CUSTOMER_ADDRESS3_STREET = "street3";

	public static final String CUSTOMER_ADDRESS3_CITY = "city3";

	public static final String CUSTOMER_ADDRESS3_STATE = "state3";

	public static final String CUSTOMER_ADDRESS3_PIN = "560037";

	public static Customer getCustomer() {
		
		Customer customer = new Customer();
		List<Address> addressList = new ArrayList<Address>();
		customer.setCustomerId(CUSTOMER_CREATION_ID);
		customer.setPassword(CUSTOMER_CREATION_PASSWORD);
		customer.setCustomerName(CUSTOMER_CREATION_NAME);
		customer.setEmailId(CUSTOMER_CREATION_EMAIL_ID);
		customer.setDateOfRegistration(CUSTOMER_CREATION_DATE_OF_REG);
		addressList=getAddressList(customer);
		customer.setAddresses(addressList);
		return customer;
	}

	public static Customer getUpdateCustomer() {
		Customer updateCustomer=getCustomer();
		updateCustomer.setCustomerName(CUSTOMER_UDATE_NAME);
		updateCustomer.setEmailId(CUSTOMER_UPDATE_EMAIL_ID);
		return updateCustomer;
	}

	private static List<Address> getAddressList(Customer customer) {

		Address address1 = new Address(CUSTOMER_ADDRESS1_STREET, CUSTOMER_ADDRESS1_CITY, CUSTOMER_ADDRESS1_STATE, CUSTOMER_ADDRESS1_PIN);
		Address address2 = new Address(CUSTOMER_ADDRESS2_STREET, CUSTOMER_ADDRESS2_CITY, CUSTOMER_ADDRESS2_STATE, CUSTOMER_ADDRESS2_PIN);
		Address address3 = new Address(CUSTOMER_ADDRESS3_STREET, CUSTOMER_ADDRESS3_CITY, CUSTOMER_ADDRESS3_STATE, CUSTOMER_ADDRESS3_PIN);
		/*addressList.add(address1);
		addressList.add(address2);
		addressList.add(address3);*/
		customer.addAddress(address1);
		customer.addAddress(address2);
		customer.addAddress(address3);
		return customer.getAddresses();
//		return addressList;
	}

}
