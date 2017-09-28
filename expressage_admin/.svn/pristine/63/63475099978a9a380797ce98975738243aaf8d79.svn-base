package com.cndatacom.rbac.system.service;

import java.util.HashMap;
import java.util.List;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.rbac.pojo.SysGroup;

/**
 * 组织架构Service层接口
 * @author yab
 *
 */
public interface ISysGroupService extends IBaseService<SysGroup,String> {
	
	/**
	 * 进行前台排序操作，此方法被dwr前台调用
	 * @param id 组id
	 * @param orderType 排序类型（升序，降序）
	 */
	public void setTheSortValue(String id,String orderType);
	
	/**
	 * 保存组织架构方法，并计算排序列
	 * @param sysGroup
	 */
	public void saveSysGroup(SysGroup sysGroup);
	
	/**
	 * 删除组织架构方法,并更新排序列
	 * @param id
	 */
	public void deleteSysGroup(String id);
	
	/**
	 * 通过父ID查询对应的子分类，此方法会被dwr前台调用
	 * @param parentId
	 */
	public List<HashMap<String, Object>> getSysGroupByParentId(String parentId);
	
	/**
	 * 通过用户名查询对应的SysGroup信息
	 * @param username
	 * @return
	 */
	public SysGroup findSysGroupByUsername(String username);
}
