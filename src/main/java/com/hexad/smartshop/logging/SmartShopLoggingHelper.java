package com.hexad.smartshop.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmartShopLoggingHelper {

	private final static Logger logger = LoggerFactory.getLogger(SmartShopLoggingHelper.class);

	public static void debug(String methodName, String errorCode) {
		logger.debug(methodName, errorCode);
	}
	public static void debug(String methodName, Object[] args) {
		logger.debug(methodName, args);
	}

	public static void debug(String methodName, String errorCode, Object[] args, Throwable ex) {
		logger.debug(methodName, errorCode, args, ex);
	}

	public static void debug(String methodName, String errorCode, Object[] args) {
		logger.debug(methodName, errorCode, args);
	}

	public static void severe(String methodName, String errorCode, Object[] args) {
		logger.error(methodName, errorCode, args);
	}

	public static void info(String methodName, String errorCode, Object[] args) {
		logger.info(methodName, errorCode, args);
	}

	public static void info(String methodName, String errorCode, Object[] args, Throwable ex) {
		logger.info(methodName, errorCode, args, ex);
	}

	public static void severe(String methodName, String errorCode, Object[] args, Throwable ex) {
		logger.error(methodName, errorCode, args, ex);
	}

}
