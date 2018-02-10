package com.hexad.smartshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hexad.smartshop.constants.ErrorMessageConstants;
import com.hexad.smartshop.data.OrderInputConstants;
import com.hexad.smartshop.exception.ProductException;
import com.hexad.smartshop.logging.SmartShopLoggingHelper;
import com.hexad.smartshop.model.ProductVO;
import com.hexad.smartshop.repository.ProductRepository;
import com.hexad.smartshop.service.IProductService;

@Service
@Transactional
public class ProductService implements IProductService {

	private static String CLASS_NAME = ProductService.class.getName();

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void addProducts(List<ProductVO> productList) {
		String methodName = CLASS_NAME + ".addProducts()";
		SmartShopLoggingHelper.debug(methodName, ErrorMessageConstants.START_METHOD);
		if (productList == null || productList.size() == 0) {
			throw new IllegalArgumentException(ErrorMessageConstants.INPUT_IS_NULL);
		}
		try {
			for (ProductVO product : productList) {
				product.setName(OrderInputConstants.productNameMap.get(product.getId()));
				productRepository.save(product);
			}
		} catch (ProductException exception) {
			SmartShopLoggingHelper.severe(ErrorMessageConstants.PRODUCT_ADD_ERROR_ID, ErrorMessageConstants.PRODUCT_ADD_ERROR_VALUE,
					new Object[] { exception.getMessage() }, exception);
			throw new ProductException(ErrorMessageConstants.PRODUCT_ADD_ERROR_ID, ErrorMessageConstants.PRODUCT_ADD_ERROR_VALUE, exception,
					new Object[] { exception.getMessage() });
		}
		SmartShopLoggingHelper.debug(methodName, ErrorMessageConstants.END_METHOD);
	}

}
