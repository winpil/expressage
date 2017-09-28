package com.cndatacom.rbac.system.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.rbac.pojo.SysUser;

/**
 * 系统用户 Service层对应的接口 
 * @author yab
 */
public interface ISysUserService extends IBaseService<SysUser,String>,UserDetailsService {
	
	/**
	 * 批量删除用户信息
	 * @param ids 
	 */
	public void deleteByIds(String ids);
	
	/**
	 * 用户授权方法
	 * @param sysUser
	 * @param roleIds
	 */
	public void grantSysUserRoles(SysUser sysUser, String[] roleIds);
	
	/**
	 * 通过用户名查询SysUser
	 * @param username 用户名
	 * @return SysUser对象
	 */
	public SysUser findUniqueByUsername(String username);
	
	/**
	 * 通过角色ids集合查询SysUser的引用
	 * @param ids
	 */
	public List<SysUser> findSysUserByRoleIds(String ids);
	
	/**
	 * 通过sp资质id查询当前sp组织架构下的用户
	 * @param spinfId
	 * @return
	 */
	public List<SysUser> findSysUserBySpInfoId(String spinfId);
	
	public List<SysUser> chkUsername(String username);
	
	/**
	 * 获取全部用户的email信息
	 * @return
	 */
	List<Object[]> getAllUserEmail();
	
	SysUser getByEmail(String email);
	
}
