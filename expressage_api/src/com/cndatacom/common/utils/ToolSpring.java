package com.cndatacom.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/** 
 * ����: ToolSpring</br> 
 * ������com.cndatacom.common.utils </br> 
 * ����: ������ͨ�����spring���������</br>
 * �����汾�ţ�1.0</br>
 * ������Ա�� ����</br>
 * ����ʱ�䣺 2013-7-22 
 */ 

public final class ToolSpring implements ApplicationContextAware {
	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		if (ToolSpring.applicationContext == null) {
			ToolSpring.applicationContext = applicationContext;
			System.out.println("========ApplicationContext���óɹ�,����ͨ�����ͨ������ToolSpring.getAppContext()��ȡapplicationContext����,applicationContext="+applicationContext + "========");
		}
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}
}
