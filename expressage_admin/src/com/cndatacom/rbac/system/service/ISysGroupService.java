package com.cndatacom.rbac.system.service;

import java.util.HashMap;
import java.util.List;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.rbac.pojo.SysGroup;

/**
 * ��֯�ܹ�Service��ӿ�
 * @author yab
 *
 */
public interface ISysGroupService extends IBaseService<SysGroup,String> {
	
	/**
	 * ����ǰ̨����������˷�����dwrǰ̨����
	 * @param id ��id
	 * @param orderType �������ͣ����򣬽���
	 */
	public void setTheSortValue(String id,String orderType);
	
	/**
	 * ������֯�ܹ�������������������
	 * @param sysGroup
	 */
	public void saveSysGroup(SysGroup sysGroup);
	
	/**
	 * ɾ����֯�ܹ�����,������������
	 * @param id
	 */
	public void deleteSysGroup(String id);
	
	/**
	 * ͨ����ID��ѯ��Ӧ���ӷ��࣬�˷����ᱻdwrǰ̨����
	 * @param parentId
	 */
	public List<HashMap<String, Object>> getSysGroupByParentId(String parentId);
	
	/**
	 * ͨ���û�����ѯ��Ӧ��SysGroup��Ϣ
	 * @param username
	 * @return
	 */
	public SysGroup findSysGroupByUsername(String username);
}
