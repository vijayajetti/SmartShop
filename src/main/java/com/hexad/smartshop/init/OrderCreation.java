package com.hexad.smartshop.init;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.hexad.smartshop.constants.ErrorMessageConstants;
import com.hexad.smartshop.data.OrderInputConstants;
import com.hexad.smartshop.exception.OrderException;
import com.hexad.smartshop.logging.SmartShopLoggingHelper;
import com.hexad.smartshop.model.Cart;
import com.hexad.smartshop.model.Customer;
import com.hexad.smartshop.model.Order;
import com.hexad.smartshop.model.Product;
import com.hexad.smartshop.service.IOrderService;

@Configuration
public class OrderCreation {

	private static String CLASS_NAME = OrderCreation.class.getName();
	private static Map<Long, Product> productMap = new HashMap<Long, Product>();

	@Autowired
	private IOrderService orderService;

	public Order createCart(Customer customer) {
		String methodName = CLASS_NAME + ".createCart()";
		SmartShopLoggingHelper.debug(methodName, ErrorMessageConstants.START_METHOD);
		Order order = null;
		Cart cart = null;
		List<Product> productList = new ArrayList<>();
		try {
			productList.addAll(productMap.values());
			cart = new Cart(customer, productList);
			customer.setCart(cart);
			order = orderService.makeAnOrder(customer);
		} catch (Exception exception) {
			SmartShopLoggingHelper.severe(ErrorMessageConstants.ORDER_CREATING_ERROR_ID, ErrorMessageConstants.ORDER_CREATING_ERROR_VALUE, new Object[] { exception.getMessage() }, exception);
			throw new OrderException(ErrorMessageConstants.ORDER_CREATING_ERROR_ID, ErrorMessageConstants.ORDER_CREATING_ERROR_VALUE, exception, new Object[] { exception.getMessage() });
		}
		SmartShopLoggingHelper.debug(methodName, ErrorMessageConstants.END_METHOD);
		return order;
	}

	public static void addProductToCart(Long productId, BigDecimal unitPrice) {
		String methodName = CLASS_NAME + ".addProductToCart()";
		SmartShopLoggingHelper.debug(methodName, ErrorMessageConstants.START_METHOD);
		Product product = new Product(productId, OrderInputConstants.productNameMap.get(productId), unitPrice, 1);
		if (productMap.containsKey(productId)) {
			product = productMap.get(productId);
			product.setItems(product.getItems() + 1);
		}
		productMap.put(productId, product);
		SmartShopLoggingHelper.debug(methodName, ErrorMessageConstants.START_METHOD);
	}

	public static void removeProductFromCart(Long productId) {
		String methodName = CLASS_NAME + ".addProductToCart()";
		SmartShopLoggingHelper.debug(methodName, ErrorMessageConstants.START_METHOD);
		if (productMap.containsKey(productId)) {
			productMap.get(productId).setItems(productMap.get(productId).getItems() - 1);
		} else {
			productMap.remove(productId);
		}
		SmartShopLoggingHelper.debug(methodName, ErrorMessageConstants.START_METHOD);
	}

}
