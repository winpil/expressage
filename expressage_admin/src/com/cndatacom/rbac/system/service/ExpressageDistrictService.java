package com.cndatacom.rbac.system.service;

import java.util.List;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.rbac.pojo.ExpressageDistrict;

/**
 * 
 * ����: ExpressageDistrictService</br> 
 * ������com.cndatacom.rbac.system.service </br> 
 * ����: </br>
 * �����汾�ţ�</br>
 * ������Ա�� ��Э</br>
 * ����ʱ�䣺 2015-10-12
 */
public interface ExpressageDistrictService extends IBaseService<ExpressageDistrict, String>{

	//ͨ��cityId��ȡ�����б�
	public List<ExpressageDistrict> getDistrictByCityId(String cityId);
	
}
