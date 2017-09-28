package com.cndatacom.rbac.dao;

import java.util.List;

import com.cndatacom.common.orm.IBaseDAO;
import com.cndatacom.rbac.pojo.SysMenu;

/**
 * 系统菜单 DAO层接口
 * @author yab
 */
public interface ISysMenuDAO extends IBaseDAO<SysMenu, String> {
	
	/**
	 * 取得子节点的个数
	 * @param parentId 父节点id
	 * @return 返回子节点的个数
	 */
	public Long getChildrenSize(String parentId);
	
	/**
	 * 通过用户id查询对应的菜单id集合
	 * @param userId 用户id
	 */
	public List<String> getSysMenuIds(String userId);
	
	
	/**
	 * 通过用户id查询对应的菜单id集合
	 * @param userId 用户id
	 */
	public List<String> findSysMenuIds(String userId);
	
	/**
	 * 查询用户拥有的权限菜单
	 * @param userId 用户id
	 */
	public List<SysMenu> findUserLefSysMenu(String userId);
}
