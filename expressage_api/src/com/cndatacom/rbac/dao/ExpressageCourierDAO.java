package com.cndatacom.rbac.dao;

import java.util.List;
import java.util.Map;

import com.cndatacom.common.orm.IBaseDAO;
import com.cndatacom.rbac.pojo.ExpressageCourier;

/**
 * 
 * ����: ExpressageCourierDAO</br> 
 * ������com.cndatacom.rbac.dao </br> 
 * ����: ���Աdao��</br>
 * �����汾�ţ�</br>
 * ������Ա�� ������</br>
 * ����ʱ�䣺 2015-7-1
 */
public interface ExpressageCourierDAO extends IBaseDAO<ExpressageCourier,String>{
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
