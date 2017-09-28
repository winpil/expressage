package com.cndatacom.rbac.system.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cndatacom.common.bean.CheckBoxGroup;
import com.cndatacom.common.bean.CheckNode;
import com.cndatacom.common.exception.ActionException;
import com.cndatacom.common.service.IBaseService;
import com.cndatacom.common.web.action.SimpleActionSupport;
import com.cndatacom.rbac.pojo.SysAuthority;
import com.cndatacom.rbac.pojo.SysMenu;
import com.cndatacom.rbac.pojo.SysRole;
import com.cndatacom.rbac.pojo.SysUser;
import com.cndatacom.rbac.system.service.ISysAuthorityService;
import com.cndatacom.rbac.system.service.ISysMenuService;
import com.cndatacom.rbac.system.service.ISysRoleService;
import com.cndatacom.rbac.system.service.ISysUserService;

@Controller
@Action("sysRole")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results( {
		@Result(name = "list", location = "/rbac/sys/role/SysRoleList.jsp", type = "dispatcher"),
		@Result(name = "input", location = "/rbac/sys/role/SysRoleEdit.jsp", type = "dispatcher"),
		@Result(name = "openSysAuthorities", location = "/rbac/sys/role/SysRoleAuthority.jsp", type = "dispatcher"),
		@Result(name = "reload", location = "sysRole!list.action", type = "redirect"),
		// @Result(type = "json", name = "success", params = { "root", "page",
		// "excludeProperties",
		// "result.*\\.authorities,result.*\\.menus,result.*\\.users" }),
		@Result(name = "status", type = "json", params = { "root","validateInfo" }),
		@Result(name = "menus", type = "json", params = { "root", "sysMenus" }),
		@Result(name = "checkNodes", type = "json", params = { "root","checkNodes" }),
		@Result(name = "checkBoxGroups", type = "json", params = { "root","checkBoxGroups" }) })
public class SysRoleAction extends SimpleActionSupport {

	private static final long serialVersionUID = 1L;
	@Resource
	private ISysAuthorityService sysAuthorityService;
	@Resource(name = "tsysUserService")
	private ISysUserService sysUserService;
	@Resource
	private ISysMenuService sysMenuService;
	// 主键标识
	private String roleId;
	// 批量操作id字串保存
	private String[] authorityIds;
	private SysMenu[] sysMenus;
	private List<CheckNode> checkNodes = new ArrayList<CheckNode>();
	private List<CheckBoxGroup> checkBoxGroups = new ArrayList<CheckBoxGroup>();
	@Resource
	private ISysRoleService sysRoleService;
	private SysRole sysRole;

	// <editor-fold defaultstate="collapsed" desc="实现基类的抽象方法">
	/**
	 * 获取spring注入service对象
	 */
	@Override
	protected IBaseService getManager() {
		return sysRoleService;
	}

	/**
	 * 创建新的实体对象
	 */
	@Override
	protected Object createNewInstance() {
		return new SysRole();
	}

	/**
	 * 获取实体对象
	 */
	@Override
	public Object getModel() {
		return getSysRole();
	}

	/**
	 * 设置实体对象
	 */
	@Override
	public void setModel(Object obj) {
		setSysRole((SysRole) obj);
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="覆盖基类中的增、删、查找方法">
	/**
	 * 删除选中的实体（批量删除）
	 * 
	 * @return "reload"
	 * @throws Exception
	 */
	@Override
	public String delete() throws Exception {
		try {
			// ###############需要重构：移入service层##################
			List<SysUser> results = sysUserService
					.findSysUserByRoleIds(getIds());

			if (null != results && results.size() > 0) {

				StringBuilder sb = new StringBuilder();

				sb.append("被删除的角色被如下用户引用：");

				for (SysUser user : results) {
					sb.append("【" + user.getName() + "】").append(",");
				}

				throw new ActionException(sb.substring(0, sb.length() - 1)
						.toString());
			}

			sysRoleService.deleteByIds(getIds());

			addMessage("删除操作成功！");

		} catch (Exception e) {
			addError("删除操作失败！错误原因为：" + e.getMessage(), e);
		}

		return RELOAD;
	}

	/**
	 * 分页查询记录集
	 * 
	 * @return "list"
	 * @throws Exception
	 */
	@Override
	public String list() throws Exception {
		return super.list();
	}

	/**
	 * 新增、更新实体时保存
	 * 
	 * @return "reload"
	 * @throws Exception
	 */
	@Override
	public String save() throws Exception {
		// ############需要重构：移入service层####################
		List<SysRole> results = sysRoleService.findByPropertyNameAndRoleId(
				"roleName", sysRole.getRoleName().trim(), sysRole.getRoleId());
		if (null != results && results.size() > 0) {
			addError(sysRole.getRoleName() + "已经存在，请重新输入！",
					new ActionException());
			return RELOAD;
		}
		return super.save();
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotEmpty(roleId)) {
			sysRole = sysRoleService.getAndInitEntity(roleId);
		} else {
			sysRole = new SysRole();
		}
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="自定义方法">
	/**
	 * @return the sysRole
	 */
	public SysRole getSysRole() {
		return sysRole;
	}

	/**
	 * @param sysRole
	 *            the sysRole to set
	 */
	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	// </editor-fold>
	public List<CheckNode> getCheckNodes() {
		return checkNodes;
	}

	public void setCheckNodes(List<CheckNode> checkNodes) {
		this.checkNodes = checkNodes;
	}

	public SysMenu[] getSysMenus() {
		return sysMenus;
	}

	public void setSysMenus(SysMenu[] sysMenus) {
		this.sysMenus = sysMenus;
	}

	public String[] getAuthorityIds() {
		return authorityIds;
	}

	public void setAuthorityIds(String[] authorityIds) {
		this.authorityIds = authorityIds;
	}

	public List<CheckBoxGroup> getCheckBoxGroups() {
		return checkBoxGroups;
	}

	public void setCheckBoxGroups(List<CheckBoxGroup> checkBoxGroups) {
		this.checkBoxGroups = checkBoxGroups;
	}

	public String openSysAuthorities() {
		if (roleId != null) {
			sysRole = sysRoleService.getAndInitEntity(roleId);
		}
		return "openSysAuthorities";
	}

	// 列出所有checkBoxGroups的属性
	public String listCheckedCheckBoxGroups() throws Exception {
		try {

			List<SysAuthority> results = sysAuthorityService
					.find("from SysAuthority order by authorityId");

			CheckBoxGroup checkBoxGroup = null;

			Set<SysAuthority> checkedSysAuthorities = sysRole.getAuthorities();

			for (SysAuthority sysAuthority : results) {

				checkBoxGroup = new CheckBoxGroup();

				checkBoxGroup.setBoxLabel(sysAuthority.getAuthorityNote());

				checkBoxGroup.setInputValue(String.valueOf(sysAuthority
						.getAuthorityId()));

				checkBoxGroup.setName("authorityIds");

				if (checkedSysAuthorities.contains(sysAuthority)) {
					checkBoxGroup.setChecked(true);
				}

				checkBoxGroups.add(checkBoxGroup);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		return "checkBoxGroups";
	}

	public void prepareListCheckedCheckBoxGroups() throws Exception {
		if (StringUtils.isNotEmpty(roleId)) {
			sysRole = sysRoleService.getAndInitEntity(roleId);
		} else {
			sysRole = new SysRole();
		}
	}

	public void prepareEditSysAuthorities() throws Exception {
		if (StringUtils.isNotEmpty(roleId)) {
			sysRole = sysRoleService.getAndInitEntity(roleId);
		} else {
			sysRole = new SysRole();
		}
	}

	public String editSysAuthorities() throws Exception {
		try {
			sysRoleService.grantAuthoritiesToSysRole(sysRole, getIds());

			getValidateInfo().setSuccess(true);

			getValidateInfo().setMsg("保存权限操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			getValidateInfo().setSuccess(false);
			getValidateInfo().setMsg("保存权限操作失败！错误原因为：" + e.getMessage());
		}

		return "status";
	}

	public String getMenus() throws Exception {
		/*
		 * SysRole role = sysRoleService.getAndInitEntity(roleId); List<SysMenu>
		 * list = sysRoleService
		 * .find("from SysMenu where parent is null order by theSort asc,id asc"
		 * ); Set<SysMenu> existMenus = role.getMenus();
		 * 
		 * // TODO 要修改成递归的形式 for (SysMenu menu : list) { if
		 * (existMenus.contains(menu)) { menu.setChecked(true);
		 * 
		 * for (SysMenu subMenu : menu.getChildren()) { if
		 * (existMenus.contains(subMenu)) { subMenu.setChecked(true);
		 * 
		 * for (SysMenu sub2Menu : subMenu.getChildren()) {
		 * 
		 * if (existMenus.contains(sub2Menu)) { sub2Menu.setChecked(true); }
		 * 
		 * for (SysMenu sub3Menu : sub2Menu.getChildren()) {
		 * 
		 * if (existMenus.contains(sub3Menu)) { sub3Menu.setChecked(true); } } }
		 * } } } }
		 * 
		 * sysMenus = list.toArray(new SysMenu[list.size()]);
		 * 
		 * return "menus"; }
		 * 
		 * public String editMenus() throws Exception {
		 * 
		 * try { sysRoleService.editSysMenu(roleId, ids);
		 * 
		 * validateInfo.setSuccess(true);
		 * 
		 * validateInfo.setMsg("保存角色菜单操作成功！"); } catch (Exception e) {
		 * e.printStackTrace();
		 * 
		 * validateInfo.setSuccess(false);
		 * 
		 * validateInfo.setMsg("保存角色菜单操作失败！错误原因为：" + e.getMessage()); }
		 */

		return "status";

	}

	//获取权限
	public String getSysAuthorities() throws Exception {
		try {
			SysRole sysRole = sysRoleService.getAndInitEntity(roleId);
			Set<SysAuthority> checkedSysAuthorities = sysRole.getAuthorities();
			List<SysMenu> results = sysMenuService.findLeafSysMenu();

			for (SysMenu sysMenu : results) {
				List<CheckNode> checkNodeChildren = new ArrayList<CheckNode>();

				CheckNode checkNode = new CheckNode();
				checkNode.setText(sysMenu.getMenuName());
				Set<SysAuthority> authorities = sysMenu.getAuthorities();

				for (SysAuthority authority : authorities) {
					CheckNode childCheckNode = new CheckNode();
					childCheckNode.setId(authority.getAuthorityId());
					childCheckNode.setText(authority.getAuthorityNote());
					
					if (checkedSysAuthorities.contains(authority)) {
						childCheckNode.setChecked(true);
					}
					checkNodeChildren.add(childCheckNode);
				}
				checkNode.setChildren(checkNodeChildren);
				checkNodes.add(checkNode);
			}

		} catch (Exception e) {}

		return "checkNodes";
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
