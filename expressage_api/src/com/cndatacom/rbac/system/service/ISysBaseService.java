package com.cndatacom.rbac.system.service;

import java.util.List;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.rbac.pojo.SysBase;

/**
 * 
 * ����: ISysBaseService</br> 
 * ������com.cndatacom.rbac.system.service </br> 
 * ����: ͨ�ýӿ�ģ��service��</br>
 * �����汾�ţ�</br>
 * ������Ա�� ������</br>
 * ����ʱ�䣺 2015-6-29
 */
public interface ISysBaseService extends IBaseService<SysBase, String>{
	
	public List getBaseApiList(SysBase sysBase,String paramenter1,String paramenter2,String paramenter3,String paramenter4,String paramenter5,String paramenter6,String pages,String pageSize);
}
