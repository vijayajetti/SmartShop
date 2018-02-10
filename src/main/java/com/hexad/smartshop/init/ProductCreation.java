package com.hexad.smartshop.init;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.hexad.smartshop.constants.ErrorMessageConstants;
import com.hexad.smartshop.exception.ProductException;
import com.hexad.smartshop.logging.SmartShopLoggingHelper;
import com.hexad.smartshop.model.ProductVO;
import com.hexad.smartshop.service.IProductService;

@Configuration
public class ProductCreation {
	
	private static String CLASS_NAME = ProductCreation.class.getName();

	@Autowired
	private IProductService productService;

	public void addProducts(List<ProductVO> productList) throws Exception {
		String methodName = CLASS_NAME + ".addProducts()";
		SmartShopLoggingHelper.debug(methodName, ErrorMessageConstants.START_METHOD);
		if (productList == null || productList.size() == 0) {
			throw new IllegalArgumentException(ErrorMessageConstants.INPUT_IS_NULL);
		}
		try {
			productService.addProducts(productList);
		} catch (ProductException exception) {
			SmartShopLoggingHelper.severe(ErrorMessageConstants.PRODUCT_ADD_ERROR_ID, ErrorMessageConstants.PRODUCT_ADD_ERROR_VALUE,
					new Object[] { exception.getMessage() }, exception);
			throw new ProductException(ErrorMessageConstants.PRODUCT_ADD_ERROR_ID, ErrorMessageConstants.PRODUCT_ADD_ERROR_VALUE, exception,
					new Object[] { exception.getMessage() });
		}
		SmartShopLoggingHelper.debug(methodName, ErrorMessageConstants.END_METHOD);
	}

}
