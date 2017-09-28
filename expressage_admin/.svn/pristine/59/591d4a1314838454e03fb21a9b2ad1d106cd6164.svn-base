package com.cndatacom.rbac.system.service;

import java.util.HashMap;
import java.util.List;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.rbac.pojo.SysRole;

/**
 * 系统角色 Service层接口
 * @author yab
 */
public interface ISysRoleService extends IBaseService<SysRole,String> {
	
	/**
	 * 批量删除角色信息
	 * @param ids 
	 */
	public void deleteByIds(String ids);
	
	/**
	 * 进行角色授权
	 * @param sysRole
	 * @param authirityIds
	 */
	public void sysRoleAuthorities(SysRole sysRole,String[] authirityIds);
	
	
	/**
	 * 取得所有的角色列表信息，此方法为dwr调用
	 * @return
	 */
	public List<HashMap<String,Object>> getAllRole(String roleId);
	
	/**
	 * 取得指定角色的菜单信息，此方法为dwr调用
	 * @param roleId 角色id
	 */
	public List<HashMap<String,Object>> getSysMenusByRoleId(String roleId);
	
	/**
	 * 对指定的角色进行菜单编辑
	 * @param role 角色
	 * @param ids 菜单值
	 */
	public void editSysMenu(String roleId,String ids);
	
	/**
	 * 通过权限ids集合查询SysRole引用
	 * @param ids
	 */
	public List<SysRole> findSysRoleByAuthorityIds(String ids);
	
	/**
	 * 通过菜单id查询SysRole引用
	 * @param ids
	 */
	public List<SysRole> findSysRoleByMenuId(String id);
	
	/**
	 * 通过属性名称与角色id查询角色信息
	 * @param propertyName 属性名称
	 * @param propertyValue 属性值
	 * @param roleId 角色id
	 */
	public List<SysRole> findByPropertyNameAndRoleId(String propertyName,Object propertyValue,
			String roleId);
	
	/**
	 * 对指定的角色进行授权
	 * @param sysRole
	 * @param ids 权限ids
	 */
	public void grantAuthoritiesToSysRole(SysRole sysRole,String ids);
	
	/**
	 * 通过权限id查询SysRole引用
	 * @param authorityId 权限id
	 */
	public List<SysRole> findSysRoleByAuthorityId(String authorityId);
	
}
