package com.hexad.smartshop.repository;

import org.springframework.data.repository.CrudRepository;

import com.hexad.smartshop.model.Customer;


public interface CustomerRepository  extends CrudRepository<Customer, Integer>{

}
