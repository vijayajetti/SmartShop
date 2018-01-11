package com.hexad.smartshop.constants;

public class QueryConstants {
	
	public static final String CUSTOMER_CREATION_HQL_QUERY = "FROM Address as address where address.customer= ";
	public static final String CUSTOMER_CHECK_QUERY = "SELECT COUNT(*) FROM customer_detail WHERE phone_number = ";
	public static final String SINGLE_QUETATION="'";

}
