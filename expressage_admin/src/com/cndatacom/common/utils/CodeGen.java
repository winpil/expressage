package com.cndatacom.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CodeGen {
	/**
	 * ������ˮ��
	 * @param fomat ���ڸ�ʽ
	 * @return ��ˮ��
	 */
	public static String genNo(String fomat) {
		String genNumber = "";
		// ҵ����ˮ��
		SimpleDateFormat sdf = new SimpleDateFormat(fomat);
		String nowdate = sdf.format(new Date());
		Integer number = (new Random().nextInt(9000)) + 1000;
		genNumber = nowdate + number.toString();
		return genNumber;
	}
}
