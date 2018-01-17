package com.hexad.smartshop.service;

import com.hexad.smartshop.model.Customer;
import com.hexad.smartshop.model.Order;

public interface IOrderService {

	public Order makeAnOrder(Customer customer)throws Exception;
	

}
