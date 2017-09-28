package com.cndatacom.rbac.system.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.rbac.pojo.SysUser;

/**
 * ϵͳ�û� Service���Ӧ�Ľӿ� 
 * @author yab
 */
public interface ISysUserService extends IBaseService<SysUser,String>,UserDetailsService {
	
	/**
	 * ����ɾ���û���Ϣ
	 * @param ids 
	 */
	public void deleteByIds(String ids);
	
	/**
	 * �û���Ȩ����
	 * @param sysUser
	 * @param roleIds
	 */
	public void grantSysUserRoles(SysUser sysUser, String[] roleIds);
	
	/**
	 * ͨ���û�����ѯSysUser
	 * @param username �û���
	 * @return SysUser����
	 */
	public SysUser findUniqueByUsername(String username);
	
	/**
	 * ͨ����ɫids���ϲ�ѯSysUser������
	 * @param ids
	 */
	public List<SysUser> findSysUserByRoleIds(String ids);
	
	/**
	 * ͨ��sp����id��ѯ��ǰsp��֯�ܹ��µ��û�
	 * @param spinfId
	 * @return
	 */
	public List<SysUser> findSysUserBySpInfoId(String spinfId);
	
	public List<SysUser> chkUsername(String username);
	
	/**
	 * ��ȡȫ���û���email��Ϣ
	 * @return
	 */
	List<Object[]> getAllUserEmail();
	
	SysUser getByEmail(String email);
	
}
