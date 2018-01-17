package com.hexad.smartshop.warehouse;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	private final static Logger logger = LoggerFactory.getLogger(Consumer.class);

	@JmsListener(destination = "wearhouse.queue")
	public void receiveQueue(String... orderDetails) {
		logger.info("Order details has received in Consumer");
		Arrays.asList(orderDetails).forEach(logger::info);
	}

}
