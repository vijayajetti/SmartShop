package com.hexad.smartshop.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hexad.smartshop.model.Cart;
import com.hexad.smartshop.model.Customer;
import com.hexad.smartshop.model.Order;
import com.hexad.smartshop.model.Order.OrderBuilder;
import com.hexad.smartshop.model.Product;
import com.hexad.smartshop.utils.AppUtils;
import com.hexad.smartshop.utils.OrderStatus;

public class OrderInputHelper {
	
	public static Order buildOrder(Customer customer) {
		return  new OrderBuilder()
				.setOrderId(AppUtils.generateOrderId())
				.setCustomer(customer)
				.setDateOfOrder(AppUtils.getCurrentDateWithTime())
				.setCurrentStatus(OrderStatus.NEW.toString())
				.setDeliveryDate(AppUtils.getPlustHrsDateWithTime(Long.valueOf(3)))
				.build();
	}

	public static Cart buildCart(Customer customer, List<Product> productList) {
		return new Cart(customer, productList);
	}

	public static Product createProduct(Long productId, BigDecimal unitPrice) {
		Product product = new Product(productId, OrderInputConstants.productNameMap.get(productId), unitPrice);
		/** Test add and remove item functionality  */
		if(productId==10004) {
		product.addItems(3);
		}else {
			product.addItems(2);
		}
		product.removeItems(1);
		return product;
	}

	public static List<Product> getProductList(Map<Long, BigDecimal> productMap) {
		List<Product> productList = new ArrayList<>();
		for (Map.Entry<Long, BigDecimal> entry : productMap.entrySet()) {
			productList.add(createProduct(entry.getKey(), entry.getValue()));
		}
		return productList;
	}

}
