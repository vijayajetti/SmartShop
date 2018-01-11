package com.hexad.smartshop.repository;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hexad.smartshop.constants.ErrorMessageConstants;
import com.hexad.smartshop.constants.QueryConstants;
import com.hexad.smartshop.exception.CustomerException;
import com.hexad.smartshop.model.Address;
import com.hexad.smartshop.model.Customer;

@Repository
public class CustomerDetailsRepository implements ICustomerDetailsRepository {

	private final Logger logger = LoggerFactory.getLogger(CustomerDetailsRepository.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Integer registerCustomer(Customer customer) throws CustomerException {
		if (isUserExists(customer.getPhoneNumber())) {
			throw new CustomerException(ErrorMessageConstants.CUSTOMER_CONFLICT);
		}
		entityManager.persist(customer);
		logger.info("Customer has been registered sucessfully with Id: " + customer.getCustomerId());
		return customer.getCustomerId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Customer getCustomerById(Integer customerId) throws CustomerException {
		String hql = QueryConstants.CUSTOMER_CREATION_HQL_QUERY.concat(customerId.toString());
		Query query = entityManager.createQuery(hql);
		List<Address> addressList = (List<Address>) query.getResultList();
		Customer customer = addressList.get(0).getCustomer();
		return customer;
	}

	@Override
	public Customer updateCustomer(Customer modifiedCustomer) throws CustomerException {
		Customer customerDetails = getCustomerById(modifiedCustomer.getCustomerId());
		if (customerDetails == null) {
			throw new CustomerException(ErrorMessageConstants.CUSTOMER_NOT_FOUND);
		}
		customerDetails.setCustomerName(modifiedCustomer.getCustomerName());
		customerDetails.setPassword(modifiedCustomer.getPassword());
		customerDetails.setEmailId(modifiedCustomer.getEmailId());
		customerDetails.setPhoneNumber(modifiedCustomer.getPhoneNumber());
		customerDetails.setAddresses(modifiedCustomer.getAddresses());
		customerDetails.setDateOfRegistration(modifiedCustomer.getDateOfRegistration());
		entityManager.flush();
		return getCustomerById(modifiedCustomer.getCustomerId());
	}

	public boolean isUserExists(String phoneNumber) {
		String sql = QueryConstants.CUSTOMER_CHECK_QUERY + QueryConstants.SINGLE_QUETATION + phoneNumber + QueryConstants.SINGLE_QUETATION;
		Query query = entityManager.createNativeQuery(sql);
		BigInteger count = (BigInteger) query.getSingleResult();
		logger.info("Single Result: " + count);
		if (count.intValue() == 1) {
			return true;
		}
		return false;
	}

}
