package com.hexad.smartshop.data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class OrderInputConstants {

	public static final Map<Long, String> productNameMap = new HashMap<>();

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
		productNameMap.put(PRODUCT_ID_SUGAR, PRODUCT_NAME_SUGAR);
		productNameMap.put(PRODUCT_ID_WHEAT, PRODUCT_NAME_WHEAT);
		productNameMap.put(PRODUCT_ID_RICE, PRODUCT_NAME_RICE);
		productNameMap.put(PRODUCT_ID_ALMOND, PRODUCT_NAME_ALMOND);
		productNameMap.put(PRODUCT_ID_OIL, PRODUCT_NAME_OIL);
	}

	
}
