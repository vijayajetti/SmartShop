package com.hexad.smartshop.mq;

import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer implements CommandLineRunner {
	
	private final static Logger logger = LoggerFactory.getLogger(Producer.class);

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	@Autowired
	private Queue queue;

	@Override
	public void run(String... args) throws Exception {
		send(args);
		logger.info("Order was pushed to Queue sucessfully ");
	}

	public void send(String... msg) {
		this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
		
	}

}