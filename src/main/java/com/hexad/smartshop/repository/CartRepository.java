package com.hexad.smartshop.repository;

import org.springframework.data.repository.CrudRepository;

import com.hexad.smartshop.model.Cart;

public interface CartRepository extends CrudRepository<Cart, Long> {

}
