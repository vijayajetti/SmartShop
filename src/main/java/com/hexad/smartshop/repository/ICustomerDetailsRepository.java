package com.hexad.smartshop.repository;

import com.hexad.smartshop.exception.CustomerException;
import com.hexad.smartshop.model.Customer;

public interface ICustomerDetailsRepository {

	public Integer registerCustomer(Customer customer)throws CustomerException;

	public Customer updateCustomer(Customer customer)throws CustomerException;

	public Customer getCustomerById(Integer customerId)throws CustomerException;

}
