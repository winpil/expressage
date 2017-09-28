package com.cndatacom.rbac.system.service;

import java.util.List;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.rbac.pojo.ExpressageStage;
import com.cndatacom.rbac.pojo.StageExpressage;

/**
 * 
 * ����: ExpressageBankService</br> 
 * ������com.cndatacom.rbac.system.service </br> 
 * ����: ���п�server��</br>
 * �����汾�ţ�</br>
 * ������Ա�� ������</br>
 * ����ʱ�䣺 2015-7-1
 */
public interface ExpressageStageService extends IBaseService<ExpressageStage, String>{
	/**
	 * 
	 * ������: getCourierIdsByjw</br>
	 * ����: ��ѯ������վ</br>
	 * ������Ա��������</br>
	 * ����ʱ�䣺2015-8-20</br>
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public List<StageExpressage> getCourierIdsByjw(String latitude,String longitude);
}
