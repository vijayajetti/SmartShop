package com.hexad.smartshop.model;

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ORDER_DETAILS")
public class Order implements java.io.Serializable {

	private static final long serialVersionUID = 7295379201869832734L;

	@Id
	@Column(name = "ORDER_ID", unique = true, nullable = false)
	@TableGenerator(name = "TABLE_GEN", table = "CUST_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "ORDER_SEQ_NEXT_VAL", initialValue = 100, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long orderId;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false, updatable = true)
	private Customer customer;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "ORDER_DATE_TIME", nullable = false, length = 19)
	private Date dateOfOrder;

	@Column(name = "CURRENT_STATUS", nullable = false, length = 20)
	private String currentStatus;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "DELIVERY_DATE_TIME")
	private Date deliveryDate;

	@Column(name = "TOTAL_PRICE")
	private BigDecimal totalPrice;

	public Order() {
	}

	public Order(Customer customer, Date dateOfOrder, String currentStatus) {
		this.customer = customer;
		this.dateOfOrder = dateOfOrder;
		this.currentStatus = currentStatus;
	}

	public Order(Customer customer) {
		this.customer = customer;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getDateOfOrder() {
		return dateOfOrder;
	}

	public void setDateOfOrder(Date dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public static class BuilderOrder {
		
		private Customer customer;

		private Date dateOfOrder;

		private String currentStatus;

//		private Date deliveryDate;

//		private BigDecimal totalPrice;

		public BuilderOrder setCustomer(Customer customer) {
			this.customer = customer;
			return this;
		}

		public BuilderOrder setDateOfOrder(Date dateOfOrder) {
			this.dateOfOrder = dateOfOrder;
			return this;
		}

		public BuilderOrder setCurrentStatus(String currentStatus) {
			this.currentStatus = currentStatus;
			return this;
		}

		/*public BuilderOrder setDeliveryDate(Date deliveryDate) {
			this.deliveryDate = deliveryDate;
			return this;
		}

		public BuilderOrder setTotalPrice(BigDecimal totalPrice) {
			this.totalPrice = totalPrice;
			return this;
		}*/

		public Order build() {
			Order order = new Order(this.customer, this.dateOfOrder, this.currentStatus);
			return order;
		}
	}
}
