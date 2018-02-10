package com.hexad.smartshop.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.hexad.smartshop.constants.ErrorMessageConstants;
import com.hexad.smartshop.exception.ProductException;
import com.hexad.smartshop.service.ProductService;
import com.hexad.smartshop.model.ProductVO;
import com.hexad.smartshop.repository.ProductRepository;
import com.hexad.smartshop.utils.TestUtils;

public class ProductServiceTest {

	@InjectMocks
	private ProductService productService;

	@Mock
	private ProductRepository productRepository;

	private ProductVO product;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
		product = new ProductVO();
	}

	@Test
	public void testAddProducts() throws Exception {
		when(productRepository.save(product)).thenReturn(product);
		productService.addProducts(Arrays.asList(product, product));
		verify(productRepository, times(2)).save(product);
	}

	@Test
	public void testAddProductsThrowException() {
		List<ProductVO> productList = TestUtils.getProductList();
		try {
			when(productRepository.save(productList.get(0))).thenThrow(new ProductException(ErrorMessageConstants.PRODUCT_ADD_ERROR_ID, ErrorMessageConstants.PRODUCT_ADD_ERROR_VALUE));
			productService.addProducts(productList);
		} catch (ProductException exception) {
			assertEquals("verifying the error code", ErrorMessageConstants.PRODUCT_ADD_ERROR_ID, exception.getErrorCode());
			assertEquals("verifying the error message", ErrorMessageConstants.PRODUCT_ADD_ERROR_VALUE, exception.getErrorMessage());
		} catch (Exception exception) {
			fail("Should not reach here for ProductException: " + exception.getMessage());
		}
	}

	@Test
	public void testAddProductsThrowExceptionIfProductListIsNull() {
		try {
			productService.addProducts(null);
			fail();
		} catch (IllegalArgumentException exception) {
			assertEquals("Verify the result ", ErrorMessageConstants.INPUT_IS_NULL, exception.getMessage());
		} catch (Exception exception) {
			fail("Should not reach here for IllegalArgumentException: " + exception.getMessage());
		}
	}

	@Test
	public void testAddProductsThrowExceptionIfProductListIsEmpty() {
		List<ProductVO> productList = new ArrayList<ProductVO>();
		try {
			productService.addProducts(productList);
			fail();
		} catch (IllegalArgumentException exception) {
			assertEquals("Verify the result ", ErrorMessageConstants.INPUT_IS_NULL, exception.getMessage());
		} catch (Exception exception) {
			fail("Should not reach here for IllegalArgumentException: " + exception.getMessage());
		}
	}

}
