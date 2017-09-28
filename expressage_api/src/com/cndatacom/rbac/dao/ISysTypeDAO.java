package com.cndatacom.rbac.dao;

import com.cndatacom.common.orm.IBaseDAO;
import com.cndatacom.rbac.pojo.SysType;

/**
 * ϵͳ���ʹ洢��DAO��ӿ�
 * @author:yab
 * 2010-06-17 9:25
 */
public interface ISysTypeDAO extends IBaseDAO<SysType,String> {
	
	public SysType getSysTypeByTypeCodeAndTypeCategoryId(String typeCategoryid,String typeCode);
}
