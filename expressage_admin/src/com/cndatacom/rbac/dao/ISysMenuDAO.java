package com.cndatacom.rbac.dao;

import java.util.List;

import com.cndatacom.common.orm.IBaseDAO;
import com.cndatacom.rbac.pojo.SysMenu;

/**
 * ϵͳ�˵� DAO��ӿ�
 * @author yab
 */
public interface ISysMenuDAO extends IBaseDAO<SysMenu, String> {
	
	/**
	 * ȡ���ӽڵ�ĸ���
	 * @param parentId ���ڵ�id
	 * @return �����ӽڵ�ĸ���
	 */
	public Long getChildrenSize(String parentId);
	
	/**
	 * ͨ���û�id��ѯ��Ӧ�Ĳ˵�id����
	 * @param userId �û�id
	 */
	public List<String> getSysMenuIds(String userId);
	
	
	/**
	 * ͨ���û�id��ѯ��Ӧ�Ĳ˵�id����
	 * @param userId �û�id
	 */
	public List<String> findSysMenuIds(String userId);
	
	/**
	 * ��ѯ�û�ӵ�е�Ȩ�޲˵�
	 * @param userId �û�id
	 */
	public List<SysMenu> findUserLefSysMenu(String userId);
}
