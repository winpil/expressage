package com.cndatacom.rbac.system.service;

import java.util.List;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.rbac.pojo.SysAuthority;

/**
 * ϵͳȨ�� Service��ӿ�
 * @author yab
 *
 */
public interface ISysAuthorityService extends IBaseService<SysAuthority, String>{
	
	/**
	 * ����ɾ������
	 * @param ids �ö��ŷָ����ַ���
	 */
	public void deleteByIds(String ids);
	
	/**
	 * ͨ������������Ȩ��id��ѯȨ����Ϣ
	 * @param propertyName ��������
	 * @param propertyValue ����ֵ
	 * @param authorityId Ȩ��id
	 */
	public List<SysAuthority> findByPropertyNameAndAuthorityId(String propertyName,Object propertyValue,
			String authorityId);
	
	/**
	 * ͨ���˵�id��ѯ��Ӧ��Ȩ��
	 * @param menuId �˵�id
	 * @return
	 */
	public List<SysAuthority> findOwnSysAuthorityByMenuId(String menuId);
	
}
