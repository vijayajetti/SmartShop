package com.hexad.smartshop.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

@Entity
@Table(name = "cart")
public class Cart implements java.io.Serializable {

	private static final long serialVersionUID = -7122640782505379507L;

	@Id
	@Column(name = "CART_ID")
	@TableGenerator(name = "TABLE_GEN", table = "CUST_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "CART_SEQ_NEXT_VAL", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long cartId;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Product.class)
	private List<Product> productList = new ArrayList<Product>();

	@JoinColumn(name = "CUSTOMER_ID")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Customer.class)
	private Customer customer;
	
	@Transient
	private BigDecimal totalPrice;

	public Cart() {
	}

	public Cart(Customer customer) {
		this.customer = customer;
	}

	public Cart(Customer customer, List<Product> productList) {
		this.customer = customer;
		this.productList = productList;
	}

	public Long getIdCart() {
		return cartId;
	}

	public void setIdCart(Long cartId) {
		this.cartId = cartId;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void addProduct(Product product) {
		productList.add(product);
		product.setCart(this);
	}

	public void removeAddress(Product product) {
		productList.remove(product);
		product.setCart(null);
	}

	public BigDecimal getTotalPrice() {
		totalPrice = BigDecimal.ZERO;
		for (Product product : this.productList) {
			totalPrice = totalPrice.add(product.calculateProductPirce());
		}
		return totalPrice;
	}
}
