package com.hexad.smartshop.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductVO implements Serializable {

	private static final long serialVersionUID = 1576510829709405106L;

	@Id
	private Long id;

	private BigDecimal price;

	private String name;

	public ProductVO() {
		super();
	}

	public ProductVO(Long id, BigDecimal price) {
		super();
		this.id = id;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
