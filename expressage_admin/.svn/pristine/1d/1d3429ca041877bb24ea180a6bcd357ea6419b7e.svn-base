package com.cndatacom.rbac.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.bean.MenuConfig;
import com.cndatacom.common.exception.ServiceException;
import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.common.utils.Constants;
import com.cndatacom.rbac.dao.ISysMenuDAO;
import com.cndatacom.rbac.pojo.SysMenu;
import com.cndatacom.rbac.pojo.SysUser;
import com.cndatacom.rbac.system.service.ISysMenuService;
import com.google.gson.Gson;

/**
 * 系统菜单 Service层对应的实现类
 * 
 * @author yab
 * 
 */
@Service("sysMenuService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu, String>
		implements ISysMenuService {

	@Resource(name = "sysMenuHibernate")
	private ISysMenuDAO sysMenuDao;

	public ISysMenuDAO getBaseDao() {
		return sysMenuDao;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<HashMap<String, Object>> getSysMenuByParentId(String parentId) {
		List<SysMenu> results = sysMenuDao.find("from SysMenu where parent.id = ? order by theSort asc,id asc",parentId);
		List<HashMap<String, Object>> subMenus = new ArrayList<HashMap<String, Object>>();
		for (SysMenu sysMenu : results) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", sysMenu.getId());
			map.put("text", sysMenu.getMenuName());
			map.put("leaf", sysMenu.isLeaf());
			subMenus.add(map);
		}
		return subMenus;
	}

	public synchronized void setTheSortValue(String id, String orderType) {
		SysMenu ownMenu = sysMenuDao.getAndInitEntity(id);

		SysMenu parentMenu = ownMenu.getParent();

		Long ownSort = ownMenu.getTheSort();

		// 向上移动
		if (Constants.ORDER_UP.equals(orderType)) {
			if (ownMenu.getTheSort().longValue() != 1L) {
				SysMenu upMenu = sysMenuDao.findUnique(
						"from SysMenu where parent = ? and theSort = ?",
						new Object[] { parentMenu, ownSort - 1 });

				upMenu.setTheSort(ownSort);

				ownMenu.setTheSort(ownSort - 1);
			}
			// 向下移动
		} else if (Constants.ORDER_DOWN.equals(orderType)) {
			long size = sysMenuDao.getChildrenSize(parentMenu.getId());

			if (ownSort.longValue() != size) {
				SysMenu downMenu = sysMenuDao.findUnique(
						"from SysMenu where parent = ? and theSort = ?",
						new Object[] { parentMenu, ownSort + 1 });
				downMenu.setTheSort(ownSort);

				ownMenu.setTheSort(ownSort + 1);
			}
		}

	}

	public synchronized void deleteSysMenu(String id) {
		try {
			SysMenu sysMenu = sysMenuDao.getAndInitEntity(id);

			if (null != sysMenu && !sysMenu.isLeaf()) {
				throw new ServiceException("菜单不是叶子结点，不能被删除！");
			}

			if (null != sysMenu) {
				List<SysMenu> results = sysMenuDao.find(
						"from SysMenu where parent = ? and theSort > ?",
						new Object[] { sysMenu.getParent(),
								sysMenu.getTheSort() });

				for (SysMenu menu : results) {
					menu.setTheSort(menu.getTheSort() - 1);
				}
			}
			sysMenuDao.delete(sysMenu);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
			throw new ServiceException(e.getMessage());
		}
	}

	public synchronized void saveSysMenu(SysMenu sysMenu) {
		try {
			long size = sysMenuDao.getChildrenSize(sysMenu.getParent().getId());

			if (null == sysMenu.getId()) {
				sysMenu.setTheSort(size + 1);
			}

			sysMenuDao.save(sysMenu);

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public String getSysMenuUrlById(String id) {
		SysMenu menu = sysMenuDao.getAndInitEntity(id);
		return menu.getUrl();
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<HashMap<String, Object>> getAuthoritySysMenuByParentId(
			String parentId) {

		List<HashMap<String, Object>> subMenus = new ArrayList<HashMap<String, Object>>();

		WebContext context = WebContextFactory.get();

		HttpSession session = context.getSession();

		SecurityContext obj = (SecurityContext) session
				.getAttribute("SPRING_SECURITY_CONTEXT");

		SysUser user = (SysUser) (obj.getAuthentication().getPrincipal());

		if (null != user) {
			List<String> menuIds = (List<String>) session.getAttribute(user
					.getUsername());

			if (null == menuIds) {
				menuIds = this.sysMenuDao.findSysMenuIds(user.getUserId());
				session.setAttribute(user.getUsername(), menuIds);
			}

			List<SysMenu> results = sysMenuDao
					.find(
							"from SysMenu where parent.id = ? order by theSort asc,id asc",
							parentId);

			for (SysMenu sysMenu : results) {

				if (menuIds.contains(sysMenu.getId())) {
					HashMap<String, Object> map = new HashMap<String, Object>();

					map.put("id", sysMenu.getId());

					map.put("text", sysMenu.getMenuName());

					map.put("leaf", sysMenu.isLeaf());

					subMenus.add(map);
				}
			}
		}

		return subMenus;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<SysMenu> findLeafSysMenu() {
		List<SysMenu> results = null;
		try {
			results = sysMenuDao.find("from SysMenu order by id desc");

			for (Iterator<SysMenu> iter = results.iterator(); iter.hasNext();) {
				if (!iter.next().isLeaf()) {
					iter.remove();
				}
			}

		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServiceException("加载叶子节点菜单出错！");
		}

		return results;
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> getAuthoritySysMenuByParentId(
			HttpSession session, String parentId) {
		List<HashMap<String, Object>> subMenus = new ArrayList<HashMap<String, Object>>();

		SecurityContext obj = (SecurityContext) session
				.getAttribute("SPRING_SECURITY_CONTEXT");

		SysUser user = (SysUser) (obj.getAuthentication().getPrincipal());

		if (null != user) {
			List<String> menuIds = (List<String>) session.getAttribute(user
					.getUsername());

			if (null == menuIds) {
				menuIds = this.sysMenuDao.findSysMenuIds(user.getUserId());
				session.setAttribute(user.getUsername(), menuIds);
			}

			List<SysMenu> results = sysMenuDao
					.find(
							"from SysMenu where parent.id = ? order by theSort asc,id asc",
							parentId);

			for (SysMenu sysMenu : results) {

				if (menuIds.contains(sysMenu.getId())) {
					HashMap<String, Object> map = new HashMap<String, Object>();

					map.put("id", sysMenu.getId());

					map.put("text", sysMenu.getMenuName());

					map.put("leaf", sysMenu.isLeaf());

					subMenus.add(map);
				}
			}
		}

		return subMenus;
	}

	@SuppressWarnings("unchecked")
	public String getAuthoritySysMenuConfig(HttpSession session, String parentId) {
		SecurityContext obj = (SecurityContext) session
				.getAttribute("SPRING_SECURITY_CONTEXT");

		String config = "";
		SysUser user = (SysUser) (obj.getAuthentication().getPrincipal());

		if (null != user) {
			List<String> menuIds = (List<String>) session.getAttribute(user
					.getUsername());

			if (null == menuIds) {
				menuIds = this.sysMenuDao.findSysMenuIds(user.getUserId());
				session.setAttribute(user.getUsername(), menuIds);
			}

			config = getSubMenusConfig(parentId, menuIds);
		}
		return config;
	}

	private String getSubMenusConfig(String parentId,
			List<String> avaibleMenuIds) {
		List<SysMenu> results = sysMenuDao
				.find(
						"from SysMenu where parent.id = ? and theSort > 0 order by theSort asc,id asc",
						parentId);

		String config = "[";

		for (SysMenu sysMenu : results) {
			config = config + "[";
			if (avaibleMenuIds.contains(sysMenu.getId())) {
				config = config + "'" + sysMenu.getId().toString() + "',";
				config = config + "'" + sysMenu.getMenuName() + "',";
				if (sysMenu.isLeaf()) {
					config = config + "'" + sysMenu.getUrl() + "'" + ",[]";
				} else {
					config = config + "'#'" + ",";
					config = config
							+ getSubMenusConfig(sysMenu.getId(), avaibleMenuIds);
				}
			}
			config = config + "],";
		}
		if (config.endsWith(",")) {
			config = config.substring(0, config.length() - 1);
		}
		return config = config + "]";
	}

	@SuppressWarnings("unchecked")
	public String getJsonSysMenuConfig(HttpSession session, String parentId) {
		SecurityContext obj = (SecurityContext) session
				.getAttribute("SPRING_SECURITY_CONTEXT");

		List<MenuConfig> lists = new ArrayList<MenuConfig>();
		SysUser user = (SysUser) (obj.getAuthentication().getPrincipal());

		if (null != user) {
			List<String> menuIds = (List<String>) session.getAttribute(user
					.getUsername());

			if (null == menuIds) {
				menuIds = this.sysMenuDao.findSysMenuIds(user.getUserId());
				session.setAttribute(user.getUsername(), menuIds);
			}

			String hql = "from SysMenu where parent.id = ? order by theSort asc,id asc";

			List<SysMenu> results = sysMenuDao.find(hql, parentId);

			for (SysMenu sysMenu : results) {
				if (menuIds.contains(sysMenu.getId())) {
					MenuConfig menuConfig = new MenuConfig();
					menuConfig.setId(sysMenu.getId());
					menuConfig.setText(sysMenu.getMenuName());
					menuConfig.setUrl(sysMenu.getUrl());

					lists.add(menuConfig);

					if (!sysMenu.isLeaf()) {
						getSubMenusConfig(menuConfig, sysMenu.getId(), menuIds);
					}
				}
			}

		}

		Gson gson = new Gson();

		return gson.toJson(lists);
	}

	private void getSubMenusConfig(MenuConfig parentConfig, String parentId,
			List<String> avaibleMenuIds) {
		String hql = "from SysMenu where parent.id = ? order by theSort asc,id asc";

		List<SysMenu> results = sysMenuDao.find(hql, parentId);

		for (SysMenu sysMenu : results) {
			if (avaibleMenuIds.contains(sysMenu.getId())) {
				MenuConfig subMenuConfig = new MenuConfig();
				subMenuConfig.setId(sysMenu.getId());
				subMenuConfig.setText(sysMenu.getMenuName());
				subMenuConfig.setUrl(sysMenu.getUrl());

				parentConfig.getChildren().add(subMenuConfig);

				if (!sysMenu.isLeaf()) {
					getSubMenusConfig(subMenuConfig, sysMenu.getId(),
							avaibleMenuIds);
				}
			}
		}
	}
}
