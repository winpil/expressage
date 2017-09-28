package com.cndatacom.common.jstl;

import java.util.Date;

import com.cndatacom.common.utils.Configure;
import com.cndatacom.common.utils.Constants;
import com.cndatacom.common.utils.NongLiUtils;

public class NongLiJstlFunction {
	//取得今天的农历
	public static String getNongLi(){
		return NongLiUtils.getTodayNongLi();
	}
	
	//取得今年的年份
	public static String getYear(){
		return NongLiUtils.getYearByDate(new Date());
	}
	
	//取得今年的月份
	public static String getMonth(){
		return NongLiUtils.getMonthByDate(new Date());
	}
	
	//取得今天的日期
	public static String getDay(){
		return NongLiUtils.getDayByDate(new Date());
	}
	
	//取得今天的星期
	public static String getWeek(){
		return NongLiUtils.getWeekByDate(new Date());
	}
	
	//取得明天的日期
	public static String getTomorrow(){
		return NongLiUtils.getTomorrow(new Date());
	}
	
	public static String getResourcePath() {
		return Configure.getSingleton().values(Constants.RESOURCE_PATH);
	}
}
