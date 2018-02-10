package com.hexad.smartshop.init;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.hexad.smartshop.constants.ErrorMessageConstants;
import com.hexad.smartshop.exception.ProductException;
import com.hexad.smartshop.model.ProductVO;
import com.hexad.smartshop.service.IProductService;
import com.hexad.smartshop.utils.TestUtils;

public class ProductCreationTest {
	
	@InjectMocks
	private ProductCreation productCreation;
	
	@Mock
	private IProductService productService;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void testAddProducts() throws Exception{
		List<ProductVO> productList = TestUtils.getProductList();
		doNothing().when(productService).addProducts(productList);
		productCreation.addProducts(productList);
		verify(productService, times(1)).addProducts(productList);
	}
	
	@Test
	public void testAddProductsThrowException() {
		List<ProductVO> productList = TestUtils.getProductList();
		try {
			doThrow(new ProductException(ErrorMessageConstants.PRODUCT_ADD_ERROR_ID, ErrorMessageConstants.PRODUCT_ADD_ERROR_VALUE)).when(productService).addProducts(productList);
			productCreation.addProducts(productList);
		} catch (ProductException exception) {
			assertEquals("verifying the error code", ErrorMessageConstants.PRODUCT_ADD_ERROR_ID, exception.getErrorCode());
			assertEquals("verifying the error message", ErrorMessageConstants.PRODUCT_ADD_ERROR_VALUE, exception.getErrorMessage());
		} catch (Exception exception) {
			fail("Should not reach here for ProductException: " + exception.getMessage());
		}
	}
	
	@Test
	public void testAddProductsThrowExceptionIfProductListIsEmpty() {
		List<ProductVO> productList = new ArrayList<ProductVO>();
		try {
			productCreation.addProducts(productList);
			fail();
		} catch (IllegalArgumentException exception) {
			assertEquals("Verify the result ",ErrorMessageConstants.INPUT_IS_NULL, exception.getMessage());
		} catch (Exception exception) {
			fail("Should not reach here for IllegalArgumentException: " + exception.getMessage());
		}
	}
	
	@Test
	public void testAddProductsThrowExceptionIfProductListIsNull() {
		try {
			productCreation.addProducts(null);
			fail();
		} catch (IllegalArgumentException exception) {
			assertEquals("Verify the result ",ErrorMessageConstants.INPUT_IS_NULL, exception.getMessage());
		} catch (Exception exception) {
			fail("Should not reach here for IllegalArgumentException: " + exception.getMessage());
		}
	}
	
}
