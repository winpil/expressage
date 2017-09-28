package com.cndatacom.rbac.system.service;
// Generated 2012-3-17 11:30:51 by Hibernate Tools 3.2.1.GA

import java.util.HashMap;
import java.util.List;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.rbac.pojo.SysCity;

/**
 *
 *  2012-3-17 11:30:51 
 */
public interface  ISysCityService extends IBaseService< SysCity,  String>{

	/**
	 * 通过父节点查询对应前台显示的地市信息,此方法被dwr前台调用
	 * @param parentId 父id
	 */
	public List<HashMap<String,Object>> getSysCityByParentId(String parentId);
	
	/**
	 * 保存系统地市方法，并计算排序列
	 * @param sysCity
	 */
	public void saveSysCity(SysCity sysCity);
	
	public Long getChildrenSize(String parentId) throws Exception;
}
