package com.cndatacom.common.jstl;

import java.util.Date;

import com.cndatacom.common.utils.Configure;
import com.cndatacom.common.utils.Constants;
import com.cndatacom.common.utils.NongLiUtils;

public class NongLiJstlFunction {
	//ȡ�ý����ũ��
	public static String getNongLi(){
		return NongLiUtils.getTodayNongLi();
	}
	
	//ȡ�ý�������
	public static String getYear(){
		return NongLiUtils.getYearByDate(new Date());
	}
	
	//ȡ�ý�����·�
	public static String getMonth(){
		return NongLiUtils.getMonthByDate(new Date());
	}
	
	//ȡ�ý��������
	public static String getDay(){
		return NongLiUtils.getDayByDate(new Date());
	}
	
	//ȡ�ý��������
	public static String getWeek(){
		return NongLiUtils.getWeekByDate(new Date());
	}
	
	//ȡ�����������
	public static String getTomorrow(){
		return NongLiUtils.getTomorrow(new Date());
	}
	
	public static String getResourcePath() {
		return Configure.getSingleton().values(Constants.RESOURCE_PATH);
	}
}
