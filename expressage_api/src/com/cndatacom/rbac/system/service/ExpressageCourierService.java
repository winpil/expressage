package com.cndatacom.rbac.system.service;

import java.util.List;
import java.util.Map;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.rbac.pojo.ExpressageCourier;

/**
 * 
 * ����: ExpressageCourierService</br> 
 * ������com.cndatacom.rbac.system.service </br> 
 * ����: </br>
 * �����汾�ţ�</br>
 * ������Ա�� ������</br>
 * ����ʱ�䣺 2015-7-1
 */
public interface ExpressageCourierService extends IBaseService<ExpressageCourier, String>{
	
	/**
	 * 
	 * ������: getCourierIdsByjw</br>
	 * ����: ���ݾ�γ�Ȳ�ѯ�������Ա</br>
	 * ������Ա��������</br>
	 * ����ʱ�䣺2015-8-13</br>
	 * @return
	 */
	public List<Map<String,String>> getCourierIdsByjw(String latitude,String longitude);
	
	public List<Map<String,Object>> getCourierIdsByjw1(String latitude,String longitude);
}
