package com.cndatacom.rbac.dao;

import java.util.List;

import com.cndatacom.common.orm.IBaseDAO;
import com.cndatacom.rbac.pojo.SysUser;

/**
 * ϵͳ�û� DAO��ӿ�
 * @author yab
 *
 */
public interface ISysUserDAO extends IBaseDAO<SysUser,String> {
	/**
	 * ͨ��spInfoId��ѯ��Ӧ��֯�ܹ�������û�
	 * @param spinfId
	 * @return
	 */
	public List<SysUser> findSysUserBySpInfoId(Long spinfId);
	
	/**
	 * ��ָ���û�������Ȩ����
	 * @param sysUser 
	 * @param roleIds
	 */
	public void grantSysUserRoles(SysUser sysUser, String[] roleIds);
	
	/**
	 * ��֤�û����Ƿ��Ѿ�����
	 * @param username
	 * @return
	 */
	public List<SysUser> chkUsername(String username);
	
	/**
	 * ��ȡȫ���û���email��Ϣ
	 * @return
	 */
	List<Object[]> getAllUserEmail();
	
	List<SysUser> getByEmail(String email);
	
}
