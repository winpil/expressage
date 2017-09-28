package com.cndatacom.rbac.system.service;

import java.util.List;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.rbac.pojo.SysAuthority;

/**
 * 系统权限 Service层接口
 * @author yab
 *
 */
public interface ISysAuthorityService extends IBaseService<SysAuthority, String>{
	
	/**
	 * 批量删除操作
	 * @param ids 用逗号分隔的字符串
	 */
	public void deleteByIds(String ids);
	
	/**
	 * 通过属性名称与权限id查询权限信息
	 * @param propertyName 属性名称
	 * @param propertyValue 属性值
	 * @param authorityId 权限id
	 */
	public List<SysAuthority> findByPropertyNameAndAuthorityId(String propertyName,Object propertyValue,
			String authorityId);
	
	/**
	 * 通过菜单id查询对应的权限
	 * @param menuId 菜单id
	 * @return
	 */
	public List<SysAuthority> findOwnSysAuthorityByMenuId(String menuId);
	
}
