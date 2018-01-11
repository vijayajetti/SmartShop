package com.hexad.smartshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hexad.smartshop.exception.CustomerException;
import com.hexad.smartshop.model.Customer;
import com.hexad.smartshop.repository.ICustomerDetailsRepository;

@Transactional
@Service
public class CustomerDetailsService implements ICustomerDetailsService {

	@Autowired
	private ICustomerDetailsRepository customerRepository;

	@Override
	public Integer registerCustomer(Customer customer) throws CustomerException{
		return customerRepository.registerCustomer(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException{
		return customerRepository.updateCustomer(customer);
	}

	@Override
	public Customer getCustomerById(Integer customerId) throws CustomerException{
		return customerRepository.getCustomerById(customerId);
	}

}
