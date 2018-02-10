package com.hexad.smartshop.repository;

import org.springframework.data.repository.CrudRepository;

import com.hexad.smartshop.model.ProductVO;

public interface ProductRepository extends CrudRepository<ProductVO, Long> {

}
