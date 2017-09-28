package com.cndatacom.rbac.common.service;

import java.util.List;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.rbac.pojo.SysType;

/**
 * 系统类型存储表Service层接口
 * @author:yab
 * 2010-06-17 9:33
 */
public interface ISysTypeService extends IBaseService<SysType,String> {
	
	/**
	 * 通过typeCategoryId查询出对应类型的SysType列表信息
	 * @param id typeCategoryId
	 * @return SysType列表信息
	 */
	public List<SysType> findSysTypeBySysTypeCategoryId(String id);
	
	/**
	 * 通过typeCategoryid与typeCode查询唯一的SysType记录
	 * @param typeId
	 * @param typeCode
	 * @return
	 */
	public SysType findSysTypeByTypeCodeAndTypeCategoryId(String typeId,String typeCode);
}
