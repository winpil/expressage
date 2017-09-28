package com.cndatacom.rbac.system.service;

import java.util.HashMap;
import java.util.List;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.rbac.pojo.SysMenu;
import javax.servlet.http.HttpSession;

/**
 * 系统菜单 Service层对应接口
 * @author yab
 *
 */
public interface ISysMenuService extends IBaseService<SysMenu,String> {
	/**
	 * 通过父节点查询对应前台显示的菜单信息,此方法被dwr前台调用
	 * @param parentId 父id
	 */
	public List<HashMap<String,Object>> getSysMenuByParentId(String parentId);
	
	/**
	 * 进行前台排序操作，此方法被dwr前台调用
	 * @param id 菜单id
	 * @param orderType 排序类型（升序，降序）
	 */
	public void setTheSortValue(String id,String orderType);
	
	/**
	 * 保存系统菜单方法，并计算排序列
	 * @param sysMenu
	 */
	public void saveSysMenu(SysMenu sysMenu);
	
	/**
	 * 删除系统菜单方法,并更新排序列
	 * @param id
	 */
	public void deleteSysMenu(String id);
	
	/**
	 * 通过菜单id得到对应的URL，此方法为dwr前台调用
	 * @param id 菜单id
	 */
	public String getSysMenuUrlById(String id);
	
	/**
	 * 通过父节点查询对应的前台显示的菜单信息，此方法被dwr前台调用
	 * @param parentId 父id
	 */
	public List<HashMap<String, Object>> getAuthoritySysMenuByParentId(String parentId);
	
	/**
	 * 查询菜单类型为URL的叶子节点
	 */
	public List<SysMenu> findLeafSysMenu();
	

    public List<HashMap<String, Object>> getAuthoritySysMenuByParentId(HttpSession session , String parentId);
    
    
    public String getAuthoritySysMenuConfig(HttpSession session , String parentId);
    
    /**
     * 返回MenuConfig对象的json数据
     */
    public String getJsonSysMenuConfig(HttpSession session , String parentId);
}
