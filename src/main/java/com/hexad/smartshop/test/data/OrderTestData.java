package com.hexad.smartshop.test.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hexad.smartshop.model.Cart;
import com.hexad.smartshop.model.Customer;
import com.hexad.smartshop.model.Order;
import com.hexad.smartshop.model.Order.OrderBuilder;
import com.hexad.smartshop.model.Product;
import com.hexad.smartshop.utils.AppUtils;
import com.hexad.smartshop.utils.OrderStatus;

public class OrderTestData {
	private static final Logger logger = LoggerFactory.getLogger(OrderTestData.class);
	public static final String ORDER_CURRENT_STATUS = "Order Placed";

	public static final Map<Long, String> productNameMap = new HashMap<>();

	public static final Map<Long, BigDecimal> productPriceMap = new HashMap<>();

	public static final Long PRODUCT_ID_SUGAR = Long.valueOf(10001);

	public static final Long PRODUCT_ID_WHEAT = Long.valueOf(10002);

	public static final Long PRODUCT_ID_RICE = Long.valueOf(10003);

	public static final Long PRODUCT_ID_ALMOND = Long.valueOf(10004);

	public static final Long PRODUCT_ID_OIL = Long.valueOf(10005);

	public static final String PRODUCT_NAME_SUGAR = "Sugar";

	public static final String PRODUCT_NAME_WHEAT = "Wheat";

	public static final String PRODUCT_NAME_RICE = "Rice";

	public static final String PRODUCT_NAME_ALMOND = "Almond";

	public static final String PRODUCT_NAME_OIL = "OIL";

	public static final BigDecimal PRODUCT_PRICE_SUGAR = BigDecimal.valueOf(45.75);

	public static final BigDecimal PRODUCT_PRICE_WHEAT = BigDecimal.valueOf(50.65);

	public static final BigDecimal PRODUCT_PRICE_RICE = BigDecimal.valueOf(70.00);

	public static final BigDecimal PRODUCT_PRICE_ALMOND = BigDecimal.valueOf(750.00);

	public static final BigDecimal PRODUCT_PRICE_OIL = BigDecimal.valueOf(120.00);

	static {
		productPriceMap.put(PRODUCT_ID_SUGAR, PRODUCT_PRICE_SUGAR);
		productPriceMap.put(PRODUCT_ID_WHEAT, PRODUCT_PRICE_WHEAT);
		productPriceMap.put(PRODUCT_ID_RICE, PRODUCT_PRICE_RICE);
		productPriceMap.put(PRODUCT_ID_ALMOND, PRODUCT_PRICE_ALMOND);
		productPriceMap.put(PRODUCT_ID_OIL, PRODUCT_PRICE_OIL);

		productNameMap.put(PRODUCT_ID_SUGAR, PRODUCT_NAME_SUGAR);
		productNameMap.put(PRODUCT_ID_WHEAT, PRODUCT_NAME_WHEAT);
		productNameMap.put(PRODUCT_ID_RICE, PRODUCT_NAME_RICE);
		productNameMap.put(PRODUCT_ID_ALMOND, PRODUCT_NAME_ALMOND);
		productNameMap.put(PRODUCT_ID_OIL, PRODUCT_NAME_OIL);
	}

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
		Product product = new Product(productId, productNameMap.get(productId), unitPrice);
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
