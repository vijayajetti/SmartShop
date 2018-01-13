package com.hexad.smartshop.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "PRODCUT_DETAILS")
public class Product implements java.io.Serializable {

	private static final long serialVersionUID = 1152281526135560574L;

	@Id
	@Column(name = "PRODUCT_ID", unique = true, nullable = false)
	@TableGenerator(name = "TABLE_GEN", table = "CUST_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PRODUCT_SEQ_NEXT_VAL", initialValue = 100, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long productId;

	@Column(name = "PRODUCT_NAME", length = 100)
	private String productName;

	@Column(name = "PRICE", nullable = false, precision = 10)
	private BigDecimal price;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Cart.class)
	@JoinColumn(name = "CART_ID", nullable = false, updatable = true)
	private Cart cart;

	@Column(name = "QUANTITY")
	private Integer items ;

	public Product() {
	}

	public Product(String productName, BigDecimal price) {
		this.productName = productName;
		this.price = price;
	}

	public Product(Cart cart, String productName, BigDecimal price, Integer items) {
		this.cart = cart;
		this.productName = productName;
		this.price = price;
		this.items = items;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Integer getItems() {
		return items;
	}

	public void setItems(Integer items) {
		this.items = items;
	}

	public void addItems(Integer item) {
		this.items++;
	}

	public void removeAddress(Integer item) {
		this.items--;
	}

	public BigDecimal calculateProductPirce() {
		BigDecimal total = BigDecimal.ZERO;
		total.add(this.price.multiply(new BigDecimal(this.getItems())));
		return total;
	}

}
