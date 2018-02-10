package com.hexad.smartshop.utils;

import java.util.ArrayList;
import java.util.List;

import com.hexad.smartshop.data.OrderInputConstants;
import com.hexad.smartshop.model.Cart;
import com.hexad.smartshop.model.Customer;
import com.hexad.smartshop.model.Order;
import com.hexad.smartshop.model.Order.OrderBuilder;
import com.hexad.smartshop.model.Product;
import com.hexad.smartshop.model.ProductVO;

public class TestUtils {

	public static Cart getCartWithProductList() {
		Cart cart = new Cart();
		List<Product> productList = new ArrayList<Product>();
		Product product1 = new Product(OrderInputConstants.PRODUCT_ID_ALMOND, OrderInputConstants.PRODUCT_NAME_ALMOND, OrderInputConstants.PRODUCT_PRICE_ALMOND, 1);
		Product product2 = new Product(OrderInputConstants.PRODUCT_ID_SUGAR, OrderInputConstants.PRODUCT_NAME_SUGAR, OrderInputConstants.PRODUCT_PRICE_SUGAR, 1);
		productList.add(product1);
		productList.add(product2);
		cart.setProductList(productList);
		return cart;
	}
	public static Order buildOrder(Customer customer) {
		
		Order order = new OrderBuilder()
				.setOrderId(AppUtils.generateOrderId())
				.setCustomer(customer)
				.setDateOfOrder(AppUtils.getCurrentDateWithTime())
				.setCurrentStatus(OrderStatus.NEW.toString())
				.setDeliveryDate(AppUtils.getPlustHrsDateWithTime(Long.valueOf(3)))
				.setTotalPrice(customer.getCart().getTotalPrice())
				.build();
		return order;
		
	}
	public static List<ProductVO> getProductList() {
		List<ProductVO> productList= new ArrayList<ProductVO>();
		productList.add(new ProductVO(OrderInputConstants.PRODUCT_ID_ALMOND, OrderInputConstants.PRODUCT_PRICE_ALMOND));
		productList.add(new ProductVO(OrderInputConstants.PRODUCT_ID_SUGAR, OrderInputConstants.PRODUCT_PRICE_SUGAR));
		return productList;
	}
}
