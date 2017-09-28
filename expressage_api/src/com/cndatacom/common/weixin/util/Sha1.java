package com.cndatacom.common.weixin.util;

import java.security.MessageDigest;

public class Sha1 {
	
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String encode(String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			messageDigest.update(str.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
	    }
	}

	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		//把密文转换成十六进制的字符串形式
		for (int i = 0; i < len; i++) {
			buf.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[i] & 0x0f]);
		}
		return buf.toString();
	}
	
	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		System.out.println(time);
		System.out.println(Sha1.encode("101"));
	}
}