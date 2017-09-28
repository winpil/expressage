package com.cndatacom.rbac.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ISysMenuDAO;
import com.cndatacom.rbac.pojo.SysMenu;

/**
 * 系统菜单 DAO层接口对应的实现类
 * 
 * @author yab
 */
@Repository("sysMenuHibernate")
public class SysMenuDAOImpl extends BaseDAOHibernateImpl<SysMenu, String>
		implements ISysMenuDAO {

	public Long getChildrenSize(String parentId) {
		return ((Number) this.getSession().createQuery(
				"select count(*) from SysMenu where parent.id = ?")
				.setParameter(0, parentId).iterate().next()).longValue();
	}

	public List<String> getSysMenuIds(String userId) {
		String hql = "select distinct m.id from SysMenu m "
            + "inner join m.roles as r " + "inner join r.users as u "
            + "where u.userId=?";
        return find(hql, userId);
	}
	
	public List<String> findSysMenuIds(String userId) {
		List<String> menuIds = new ArrayList<String>();
		List<SysMenu> menus = findAuthoritySysMenus(userId);
		for (SysMenu menu : menus) {
			findAuthorityMenuId(menuIds, menu);
		}
		return menuIds;
	}
	
	private List<SysMenu> findAuthoritySysMenus(String userId) {
		String strSql = "select distinct m from SysAuthority s inner join s.sysMenu m inner join s.sysRoles r inner join r.users u where u.userId = ?";
		return find(strSql, userId);
	}
	
	private void findAuthorityMenuId(List<String> menuIds, SysMenu sysMenu) {
		System.out.println("===================================="+sysMenu.getParent()+""+sysMenu.getMenuName());
		if (sysMenu.getParent() == null) {
			if (!menuIds.contains(sysMenu.getId())) {
				menuIds.add(sysMenu.getId());
			}
		} else {
			if (!menuIds.contains(sysMenu.getId())) {
				menuIds.add(sysMenu.getId());
			}
			findAuthorityMenuId(menuIds, sysMenu.getParent());
		}
	}

	public List<SysMenu> findUserLefSysMenu(String userId) {
		String hql = "select distinct m from SecuritySysUser u join u.roles r join r.authorities a join a.sysMenu m where u.userId = ?";
		return find(hql, userId);
	}
}
