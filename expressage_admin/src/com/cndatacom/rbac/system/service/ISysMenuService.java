package com.cndatacom.rbac.system.service;

import java.util.HashMap;
import java.util.List;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.rbac.pojo.SysMenu;
import javax.servlet.http.HttpSession;

/**
 * ϵͳ�˵� Service���Ӧ�ӿ�
 * @author yab
 *
 */
public interface ISysMenuService extends IBaseService<SysMenu,String> {
	/**
	 * ͨ�����ڵ��ѯ��Ӧǰ̨��ʾ�Ĳ˵���Ϣ,�˷�����dwrǰ̨����
	 * @param parentId ��id
	 */
	public List<HashMap<String,Object>> getSysMenuByParentId(String parentId);
	
	/**
	 * ����ǰ̨����������˷�����dwrǰ̨����
	 * @param id �˵�id
	 * @param orderType �������ͣ����򣬽���
	 */
	public void setTheSortValue(String id,String orderType);
	
	/**
	 * ����ϵͳ�˵�������������������
	 * @param sysMenu
	 */
	public void saveSysMenu(SysMenu sysMenu);
	
	/**
	 * ɾ��ϵͳ�˵�����,������������
	 * @param id
	 */
	public void deleteSysMenu(String id);
	
	/**
	 * ͨ���˵�id�õ���Ӧ��URL���˷���Ϊdwrǰ̨����
	 * @param id �˵�id
	 */
	public String getSysMenuUrlById(String id);
	
	/**
	 * ͨ�����ڵ��ѯ��Ӧ��ǰ̨��ʾ�Ĳ˵���Ϣ���˷�����dwrǰ̨����
	 * @param parentId ��id
	 */
	public List<HashMap<String, Object>> getAuthoritySysMenuByParentId(String parentId);
	
	/**
	 * ��ѯ�˵�����ΪURL��Ҷ�ӽڵ�
	 */
	public List<SysMenu> findLeafSysMenu();
	

    public List<HashMap<String, Object>> getAuthoritySysMenuByParentId(HttpSession session , String parentId);
    
    
    public String getAuthoritySysMenuConfig(HttpSession session , String parentId);
    
    /**
     * ����MenuConfig�����json����
     */
    public String getJsonSysMenuConfig(HttpSession session , String parentId);
}
