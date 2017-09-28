package com.cndatacom.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CodeGen {
	/**
	 * 生成流水号
	 * @param fomat 日期格式
	 * @return 流水号
	 */
	public static String genNo(String fomat) {
		String genNumber = "";
		// 业务流水号
		SimpleDateFormat sdf = new SimpleDateFormat(fomat);
		String nowdate = sdf.format(new Date());
		Integer number = (new Random().nextInt(9000)) + 1000;
		genNumber = nowdate + number.toString();
		return genNumber;
	}
}
