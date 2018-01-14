package com.hexad.smartshop.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.hexad.smartshop.constants.OrderConstants;

public class AppUtils {

	public static Date getCurrentDateWithTime() {
		return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date getCurrentDate() {
		return Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date getPlustHrsDateWithTime(Long hrs) {
		return Date.from(LocalDateTime.now().plusHours(hrs).atZone(ZoneId.systemDefault()).toInstant());
	}

	public static String generateOrderId() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(OrderConstants.ORDER_ID_FORMATTER);
		return OrderConstants.ORDER_ID_PREFIX.concat(LocalDateTime.now().format(formatter));
	}

	public static String encryptPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(password.getBytes());
		byte byteData[] = messageDigest.digest();
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
