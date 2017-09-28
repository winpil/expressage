package com.cndatacom.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/** 
 * 类名: ToolSpring</br> 
 * 包名：com.cndatacom.common.utils </br> 
 * 描述: 用于普通类调用spring对象的配置</br>
 * 发布版本号：1.0</br>
 * 开发人员： 潘勇</br>
 * 创建时间： 2013-7-22 
 */ 

public final class ToolSpring implements ApplicationContextAware {
	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		if (ToolSpring.applicationContext == null) {
			ToolSpring.applicationContext = applicationContext;
			System.out.println("========ApplicationContext配置成功,在普通类可以通过调用ToolSpring.getAppContext()获取applicationContext对象,applicationContext="+applicationContext + "========");
		}
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}
}
