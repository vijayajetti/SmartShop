package com.hexad.smartshop.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODCUT_DETAILS")
public class Product implements java.io.Serializable {

	private static final long serialVersionUID = 1152281526135560574L;

	@Id
	@Column(name = "PRODUCT_ID", unique = true, nullable = false)
//	@TableGenerator(name = "TABLE_GEN", table = "CUST_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PRODUCT_SEQ_NEXT_VAL", initialValue = 100, allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long productId;

	@Column(name = "PRODUCT_NAME", length = 100)
	private String productName;

	@Column(name = "UNIT_PRICE", nullable = false, precision = 10)
	private BigDecimal unitPrice;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Cart.class)
	@JoinColumn(name = "CART_ID", nullable = false, updatable = true)
	private Cart cart;

	@Column(name = "QUANTITY")
	private int items;

	public Product() {
	}

	public Product(Long productId, BigDecimal unitPrice) {
		this.productId = productId;
		this.unitPrice = unitPrice;
	}

	public Product(Long productId, String productName, BigDecimal unitPrice) {
		this.productId = productId;
		this.productName = productName;
		this.unitPrice = unitPrice;
	}

	public Product(Cart cart, Long productId, String productName, BigDecimal unitPrice, Integer items) {
		this.cart = cart;
		this.productId = productId;
		this.productName = productName;
		this.unitPrice = unitPrice;
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

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public int getItems() {
		return items;
	}

	public void setItems(int items) {
		this.items = items;
	}

	public void addItems(int item) {
		this.items = this.items + item;
	}

	public void removeItems(int item) {
		this.items = this.items - item;
	}

	public BigDecimal calculateProductPirce() {
		BigDecimal total = BigDecimal.ZERO;
		if(this.items>0)
		total = total.add(this.unitPrice.multiply(new BigDecimal(this.items)));
		return total;
	}

}
