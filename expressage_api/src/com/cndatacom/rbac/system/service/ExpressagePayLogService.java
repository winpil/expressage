package com.cndatacom.rbac.system.service;

import java.util.List;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.rbac.pojo.ExpressagePayLog;

/**
 * 
 * ����: ExpressageCityService</br> 
 * ������com.cndatacom.rbac.system.service </br> 
 * ����: </br>
 * �����汾�ţ�</br>
 * ������Ա�� ������</br>
 * ����ʱ�䣺 2015-7-1
 */
public interface ExpressagePayLogService extends IBaseService<ExpressagePayLog, String>{
	
	/**
	 * 
	 * ������: getEPList</br>
	 * ����:��ѯ���Ա֧����¼ </br>
	 * ������Ա��������</br>
	 * ����ʱ�䣺2015-7-31</br>
	 * @param courierId
	 * @param pageN
	 * @param pageSize
	 * @return
	 */
	public List<ExpressagePayLog> getEPList(String courierId,String pageN,String pageSize);
}
