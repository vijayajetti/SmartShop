package com.hexad.smartshop;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.hexad.smartshop.customer.CustomerRegistration;
import com.hexad.smartshop.model.Cart;
import com.hexad.smartshop.model.Customer;
import com.hexad.smartshop.model.Order;
import com.hexad.smartshop.model.Product;
import com.hexad.smartshop.test.data.CustTestData;
import com.hexad.smartshop.test.data.OrderTestData;

@SpringBootApplication
public class SmartShopApplication {

	private final static Logger logger = LoggerFactory.getLogger(CustomerRegistration.class);

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(SmartShopApplication.class, args);
		CustomerRegistration customerInit = context.getBean(CustomerRegistration.class);
		/*** Uncomment below for new Customer Registration */
		// customerInit.registerCustomer(CustTestData.getCustomer());
		/*** Uncomment below to update the existing Customer details */
		// customerInit.updateCustomer(CustTestData.getUpdateCustomer());
		/*** Uncomment below to get existing Customer details by Id */
		// Customer customer=customerInit.getCustomerById(CustTestData.CUSTOMER_GET_ID);

		Customer customer = CustTestData.getCustomer();
		List<Product> productList = OrderTestData.getProductList(OrderTestData.productPriceMap);
		Cart cart = OrderTestData.buildCart(customer, productList);
		customer.setCart(cart);
		Order orderMade = OrderTestData.buildOrder(customer);
		logger.info("Order Details of : " + orderMade.getCustomer().getCustomerName());
		logger.info("OrderId: " + orderMade.getOrderId() + " | OrderDate: " + orderMade.getDateOfOrder()
				+ " | Current Status: " + orderMade.getCurrentStatus() + " | Expected Delivery Date: "
				+ orderMade.getDeliveryDate() + " | Order Price: " + orderMade.getTotalPrice());

		// SpringApplication.exit(context, () -> 0);
	}
}
