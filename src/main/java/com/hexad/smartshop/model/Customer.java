package com.hexad.smartshop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.hexad.smartshop.utils.AppUtils;

@Entity
@Table(name = "CUSTOMER_DETAIL")
public class Customer implements Serializable {

	private static final long serialVersionUID = -7600415208306161758L;

	@Id
	@Column(name = "CUSTOMER_ID")
	@TableGenerator(name = "TABLE_GEN", table = "CUST_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "CUSTOMER_SEQ_NEXT_VAL", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Integer customerId;

	@NotEmpty(message="{NotEmpty.customer.customerName}")
	@Column(name = "CUSTOMER_NAME")
	private String customerName;

	@NotEmpty(message="{NotEmpty.customer.emailId}")
	@Email(message = "{customer.emailId}")
	@Column(name = "EMAIL_ID")
	private String emailId;

	@NotEmpty(message="{NotEmpty.customer.phoneNumber}")
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;
	
	@Length(min = 8, message = "{customer.password}")
	@NotEmpty(message="{NotEmpty.customer.password}")
	private String password;
	
//	@NotEmpty(message="{NotEmpty.customer.addresses}")
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Address.class)
	private List<Address> addresses =new ArrayList<>();

	@Temporal(value = TemporalType.DATE)
	@Column(name = "REGISTRATION_DATE")
	private Date dateOfRegistration;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Address.class)
	private List<Order> listOfOrders = new ArrayList<Order>();
	
	@OneToOne(mappedBy="customer",fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Cart.class)
	private Cart cart;

	
	public Customer() {
	}

	public Customer(String customerName, String emailId, String phoneNumber) {
		this.customerName = customerName;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		dateOfRegistration = AppUtils.getCurrentDate();
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}

	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	
	public List<Order> getListOfOrders() {
		return listOfOrders;
	}

	public void setListOfOrders(List<Order> listOfOrders) {
		this.listOfOrders = listOfOrders;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public void addAddress(Address address) {
		addresses.add(address);
		address.setCustomer(this);
	}

	public void removeAddress(Address address) {
		addresses.remove(address);
		address.setCustomer(null);
	}
	
	public void addOrder(Order order) {
		listOfOrders.add(order);
		order.setCustomer(this);
	}
}
