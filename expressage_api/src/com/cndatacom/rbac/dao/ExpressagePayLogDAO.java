package com.cndatacom.rbac.dao;

import java.util.List;

import com.cndatacom.common.orm.IBaseDAO;
import com.cndatacom.rbac.pojo.ExpressagePayLog;

/**
 * 
 * ����: ExpressageCityDAO</br> 
 * ������com.cndatacom.rbac.dao </br> 
 * ����: ֧����־dao��</br>
 * �����汾�ţ�</br>
 * ������Ա�� ������</br>
 * ����ʱ�䣺 2015-7-1
 */
public interface ExpressagePayLogDAO extends IBaseDAO<ExpressagePayLog,String>{
	
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
