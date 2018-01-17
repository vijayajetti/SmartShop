package com.hexad.smartshop.repository;

import org.springframework.data.repository.CrudRepository;

import com.hexad.smartshop.model.Order;

public interface OrderRepository extends CrudRepository<Order, String>{

}
