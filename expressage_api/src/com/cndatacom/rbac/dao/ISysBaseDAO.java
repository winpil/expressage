package com.cndatacom.rbac.dao;

import java.util.List;

import com.cndatacom.common.orm.IBaseDAO;
import com.cndatacom.rbac.pojo.SysBase;

/**
 * 
 * ����: ISysBaseDAO</br> 
 * ������com.cndatacom.rbac.dao </br> 
 * ����: ͨ�ýӿ�ģ��dao��</br>
 * �����汾�ţ�</br>
 * ������Ա�� ������</br>
 * ����ʱ�䣺 2015-6-29
 */
public interface ISysBaseDAO extends IBaseDAO<SysBase,String>{
	public List getBaseApiList(SysBase sysBase,String paramenter1,String paramenter2,String paramenter3,String paramenter4,String paramenter5,String paramenter6,String pages,String pageSize);
}
