package com.cndatacom.rbac.dao;

import java.util.List;

import com.cndatacom.common.orm.IBaseDAO;
import com.cndatacom.rbac.pojo.SysUser;

/**
 * 系统用户 DAO层接口
 * @author yab
 *
 */
public interface ISysUserDAO extends IBaseDAO<SysUser,String> {
	/**
	 * 通过spInfoId查询对应组织架构下面的用户
	 * @param spinfId
	 * @return
	 */
	public List<SysUser> findSysUserBySpInfoId(Long spinfId);
	
	/**
	 * 对指定用户进行授权操作
	 * @param sysUser 
	 * @param roleIds
	 */
	public void grantSysUserRoles(SysUser sysUser, String[] roleIds);
	
	/**
	 * 验证用户名是否已经存在
	 * @param username
	 * @return
	 */
	public List<SysUser> chkUsername(String username);
	
	/**
	 * 获取全部用户的email信息
	 * @return
	 */
	List<Object[]> getAllUserEmail();
	
	List<SysUser> getByEmail(String email);
	
}
