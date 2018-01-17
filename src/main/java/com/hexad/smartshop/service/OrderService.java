package com.hexad.smartshop.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hexad.smartshop.constants.ErrorMessageConstants;
import com.hexad.smartshop.constants.OrderConstants;
import com.hexad.smartshop.exception.OrderException;
import com.hexad.smartshop.logging.SmartShopLoggingHelper;
import com.hexad.smartshop.model.Customer;
import com.hexad.smartshop.model.Order;
import com.hexad.smartshop.model.Order.OrderBuilder;
import com.hexad.smartshop.mq.Producer;
import com.hexad.smartshop.repository.OrderRepository;
import com.hexad.smartshop.utils.AppUtils;
import com.hexad.smartshop.utils.OrderStatus;

@Transactional
@Service
public class OrderService implements IOrderService {

	private static String CLASS_NAME = OrderService.class.getName();

	@Autowired
	private Producer producer;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order makeAnOrder(Customer customer) throws Exception{
		String methodName = CLASS_NAME + ".makeAnOrder()";
		SmartShopLoggingHelper.debug(methodName, ErrorMessageConstants.START_METHOD);
		Order order=null;
		if (customer.getCart().getTotalPrice().compareTo(BigDecimal.ZERO) <= 0) {
			SmartShopLoggingHelper.severe(methodName, ErrorMessageConstants.ORDER_AMOUNT_ZERO, new Object[] {});
			throw new IllegalArgumentException(ErrorMessageConstants.ORDER_AMOUNT_ZERO);
		}
		try {
			order=orderRepository.save(buildOrder(customer));
		}catch (OrderException exception) {
			SmartShopLoggingHelper.severe(ErrorMessageConstants.ORDER_CREATING_ERROR_ID, ErrorMessageConstants.ORDER_CREATING_ERROR_VALUE,
					new Object[] { exception.getMessage() }, exception);
			throw new OrderException(ErrorMessageConstants.ORDER_CREATING_ERROR_ID, ErrorMessageConstants.ORDER_CREATING_ERROR_VALUE,
					exception, new Object[] { exception.getMessage() });
		}
		String[] orderData = {
				OrderConstants.ORDER_DETAILS + order.getCustomer().getCustomerName(),
				OrderConstants.ORDER_ID + order.getOrderId(),
				OrderConstants.ORDER_DATE + order.getDateOfOrder(),
				OrderConstants.ORDER_CURRENT_STATUS + order.getCurrentStatus(),
				OrderConstants.ORDER_ETA + order.getDeliveryDate(),
				OrderConstants.ORDER_TOTAL_PRICE + order.getTotalPrice()};
		try {
			producer.run(orderData);
		} catch (Exception exception) {
			SmartShopLoggingHelper.severe(ErrorMessageConstants.ORDER_SENDING_ERROR_ID, ErrorMessageConstants.ORDER_SENDING_ERROR_VALUE,
					new Object[] { exception.getMessage() }, exception);
			throw new OrderException(ErrorMessageConstants.ORDER_SENDING_ERROR_ID, ErrorMessageConstants.ORDER_SENDING_ERROR_VALUE,
					exception, new Object[] { exception.getMessage() });
		}
		SmartShopLoggingHelper.debug(methodName, ErrorMessageConstants.END_METHOD);
		return order;
	}

	private Order buildOrder(Customer customer) {
		return new OrderBuilder().setOrderId(AppUtils.generateOrderId()).setCustomer(customer)
				.setDateOfOrder(AppUtils.getCurrentDateWithTime()).setCurrentStatus(OrderStatus.NEW.toString())
				.setDeliveryDate(AppUtils.getPlustHrsDateWithTime(Long.valueOf(3))).setTotalPrice(customer.getCart().getTotalPrice())
				.build();
	}
}
