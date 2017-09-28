package com.cndatacom.rbac.common.service;

import java.util.List;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.rbac.pojo.SysType;

/**
 * ϵͳ���ʹ洢��Service��ӿ�
 * @author:yab
 * 2010-06-17 9:33
 */
public interface ISysTypeService extends IBaseService<SysType,String> {
	
	/**
	 * ͨ��typeCategoryId��ѯ����Ӧ���͵�SysType�б���Ϣ
	 * @param id typeCategoryId
	 * @return SysType�б���Ϣ
	 */
	public List<SysType> findSysTypeBySysTypeCategoryId(String id);
	
	/**
	 * ͨ��typeCategoryid��typeCode��ѯΨһ��SysType��¼
	 * @param typeId
	 * @param typeCode
	 * @return
	 */
	public SysType findSysTypeByTypeCodeAndTypeCategoryId(String typeId,String typeCode);
}
