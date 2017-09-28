package com.cndatacom.rbac.system.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.cndatacom.common.bean.CheckBoxGroup;
import com.cndatacom.common.exception.ActionException;
import com.cndatacom.common.orm.PropertyFilter;
import com.cndatacom.common.service.IBaseService;
import com.cndatacom.common.web.action.SimpleActionSupport;
import com.cndatacom.common.web.struts2.Struts2Utils;
import com.cndatacom.iptv.enums.BooleanEnum;
import com.cndatacom.rbac.pojo.SysGroup;
import com.cndatacom.rbac.pojo.SysRole;
import com.cndatacom.rbac.pojo.SysUser;
import com.cndatacom.rbac.system.service.ISysGroupService;
import com.cndatacom.rbac.system.service.ISysRoleService;
import com.cndatacom.rbac.system.service.ISysUserService;

@Controller
@Action("sysUser")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results( {
		@Result(name = "index", location = "/rbac/sys/user/SysUserIndex.jsp", type = "dispatcher"),
		@Result(name = "list", location = "/rbac/sys/user/SysUserList.jsp", type = "dispatcher"),
		@Result(name = "input", location = "/rbac/sys/user/SysUserEdit.jsp", type = "dispatcher"),
		@Result(name = "inputMySelf", location = "/rbac/sys/user/MySelfEdit.jsp", type = "dispatcher"),
		@Result(name = "inputPassword", location = "/rbac/sys/user/UserPasswordEdit.jsp", type = "dispatcher"),
		@Result(name = "inputResetPassword", location = "/rbac/sys/user/ResetPassword.jsp", type = "dispatcher"),
		@Result(name = "editPasswordSuccess", location = "sysUser!inputPassword.action", type = "redirect"),
		@Result(name = "resetPasswordSuccess", location = "sysUser!list.action", type = "redirect"),
		@Result(name = "editMyselfSuccess", location = "sysUser!inputMySelf.action", type = "redirect"),
		@Result(name = "groupTree", location = "/rbac/sys/user/SysUserTree.jsp", type = "dispatcher"),
		@Result(name = "editRole", location = "/rbac/sys/user/UserRoleEdit.jsp", type = "dispatcher"),
		@Result(name = "reload", location = "sysUser!list.action", type = "redirect"),
		// @Result(type = "json", name = "success", params = { "root", "page",
		// "excludeProperties", "result.*\\.roles" }),
		@Result(name = "status", type = "json", params = { "root","validateInfo" }),
		@Result(name = "checkBoxGroups", type = "json", params = { "root","checkBoxGroups" }) })
public class SysUserAction extends SimpleActionSupport {

	private static final long serialVersionUID = 1L;
	@Resource(name = "tsysUserService")
	private ISysUserService sysUserService;
	@Resource
	private ISysRoleService sysRoleService;
	@Resource
	private ISysGroupService sysGroupService;

	private Md5PasswordEncoder md5 = new Md5PasswordEncoder();
	// 主键标识
	private String userId;
	private String mPassword;
	private String[] roleIds;
	private String oldPassword;
	private String username;
	// 组织架构id
	private String groupId;
	private String manufacId;
	private BooleanEnum booleanEnum = BooleanEnum.YES;
	private SysUser sysUser;


	// <editor-fold defaultstate="collapsed" desc="实现基类的抽象方法">
	/**
	 * 获取spring注入service对象
	 */
	@Override
	protected IBaseService getManager() {
		return sysUserService;
	}

	/**
	 * 创建新的实体对象
	 */
	@Override
	protected Object createNewInstance() {
		return new SysUser();
	}

	/**
	 * 获取实体对象
	 */
	@Override
	public Object getModel() {
		return getSysUser();
	}

	/**
	 * 设置实体对象
	 */
	@Override
	public void setModel(Object obj) {
		setSysUser((SysUser) obj);
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
		String result = super.delete();
		// 刷新右边的导航树
		refreshFrame("menutree", "sysUser!groupTree.action");
		return result;
	}

	/**
	 * 分页查询记录集
	 * 
	 * @return "list"
	 * @throws Exception
	 */
	@Override
	public String list() throws Exception {
		if (null == groupId) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			SysUser ownUser = sysUserService.findUniqueByUsername(username);
			groupId = ownUser.getSysGroup().getGroupId();
		}
		getFilters().add(new PropertyFilter("EQS_sysGroup.groupId", groupId + ""));
		//按创建时间排序显示
		sysUserService.findPageOrderBy(getPage(), new Order[]{Order.desc("createdtime")}, getFilters());
		return LIST;
	}

	/**
	 * 新增、更新实体时保存
	 * 
	 * @return "reload"
	 * @throws Exception
	 */
	@Override
	public String save() throws Exception {
		try {
			// TODO进行用户组织架构的验证

			// 设置组织架构
			if (null != groupId) {
				SysGroup sysGroup = sysGroupService.get(groupId);
				sysUser.setSysGroup(sysGroup);
			}

			List<SysUser> results = null;

			if (null == userId) {
				results = sysUserService.findBy("username", sysUser.getUsername());
			}
			if (getId() == null || "".equals(getId())) {
				sysUser.setCreatedtime(new Date());
			}

			if (null != results && results.size() > 0) {
				addError("username", new ActionException());
			} else {
				sysUserService.save(sysUser);
				addMessage("保存操作成功！");
			}
		} catch (Exception e) {
			addError("保存操作失败！错误原因为：" + e.getMessage(), e);
		}
		refreshFrame("menutree", "sysUser!groupTree.action");
		return RELOAD;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotEmpty(userId)) {
			sysUser = sysUserService.getAndInitEntity(userId);
			querySysUserExtendConfig();
		} else {
			sysUser = new SysUser();
		}

		if (mPassword != null && !"".equals(mPassword.trim())) {
			sysUser.setPassword(md5.encodePassword(mPassword.trim(), null));
		}
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="自定义方法">
	/**
	 * 查询出所有的用户email信息
	 */
	public String getAllUserEmail() throws Exception {
		try {
			List<Object[]> list = sysUserService.getAllUserEmail();
			List<Object[]> listFilter = new ArrayList<Object[]>();
			for(Object[] obj:list){
				if(!obj[1].toString().trim().equals("")){
					listFilter.add(obj);
				}
			}
			getValidateInfo().setSuccess(true);
			getValidateInfo().setResult(listFilter);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			getValidateInfo().setSuccess(false);
		}
		return "status";
	}

	private void querySysUserExtendConfig() {
		manufacId = sysUser.getManufacId();
	}


	/**
	 * @return the sysUser
	 */
	public SysUser getSysUser() {
		return sysUser;
	}

	/**
	 * @param sysUser
	 *            the sysUser to set
	 */
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	public String getMPassword() {
		return mPassword;
	}

	public void setMPassword(String mPassword) {
		this.mPassword = mPassword;
	}

	private List<CheckBoxGroup> checkBoxGroups = new ArrayList<CheckBoxGroup>();

	public List<CheckBoxGroup> getCheckBoxGroups() {
		return checkBoxGroups;
	}

	public void setCheckBoxGroups(List<CheckBoxGroup> checkBoxGroups) {
		this.checkBoxGroups = checkBoxGroups;
	}

	public String index() {
		return "index";
	}

	// 列出所有checkBoxGroups的属性

	public String listCheckedCheckBoxGroups() throws Exception {
		try {

			List<SysRole> results = sysRoleService.getAll();

			CheckBoxGroup checkBoxGroup = null;

			Set<SysRole> checkedSysRoles = sysUser.getRoles();

			for (SysRole sysRole : results) {

				checkBoxGroup = new CheckBoxGroup();

				checkBoxGroup.setBoxLabel(sysRole.getRoleName());

				checkBoxGroup
						.setInputValue(String.valueOf(sysRole.getRoleId()));

				checkBoxGroup.setName("roleIds");

				if (checkedSysRoles.contains(sysRole)) {
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

	public String input() throws Exception {
		return INPUT;
	}

	public String groupTree() {
		return "groupTree";
	}

	public void chkUsername() throws Exception {
		HttpServletResponse response = Struts2Utils.getResponse();
		String result = "";
		List<SysUser> sysUsers = sysUserService.chkUsername(sysUser
				.getUsername());
		// 如果list集合大于0说明已经存在该用户
		if (sysUsers.size() > 0) {
			result = "1";
		} else {
			result = "2";
		}
		response.getWriter().print(result);
		response.getWriter().flush();
		response.getWriter().close();
	}

	public String inputMySelf() {

		try {
			String username = SecurityContextHolder.getContext()
					.getAuthentication().getName();
			sysUser = sysUserService.findUniqueByUsername(username);
			userId = sysUser.getUserId();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		return "inputMySelf";
	}

	public String inputPassword() {
		try {
			String username = SecurityContextHolder.getContext()
					.getAuthentication().getName();
			sysUser = sysUserService.findUniqueByUsername(username);
			userId = sysUser.getUserId();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return "inputPassword";
	}

	public String inputResetPassword() {
		try {
			sysUser = sysUserService.getAndInitEntity(userId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		return "inputResetPassword";
	}

	public String getmPassword() {
		return mPassword;
	}

	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public void prepareGrantSysUserRoles() throws Exception {
		if (StringUtils.isNotEmpty(userId)) {
			sysUser = sysUserService.getAndInitEntity(userId);
		} else {
			sysUser = new SysUser();
		}
	}

	public void prepareListCheckedCheckBoxGroups() throws Exception {
		if (StringUtils.isNotEmpty(userId)) {
			sysUser = sysUserService.getAndInitEntity(userId);
		} else {
			sysUser = new SysUser();
		}
	}

	public void prepareEditRole() {
		try {
			prepareModel();
		} catch (Exception e) {
		}
	}

	private List roleList = null;

	public String editRole() {
		try {
			List<SysRole> results = sysRoleService.getAll();
			List<String> tmpList = new ArrayList<String>();
			roleList = results;
			CheckBoxGroup checkBoxGroup = null;

			Set<SysRole> checkedSysRoles = sysUser.getRoles();

			for (SysRole sysRole : results) {

				checkBoxGroup = new CheckBoxGroup();

				checkBoxGroup.setBoxLabel(sysRole.getRoleName());

				checkBoxGroup
						.setInputValue(String.valueOf(sysRole.getRoleId()));

				checkBoxGroup.setName("roleIds");

				if (checkedSysRoles.contains(sysRole)) {
					checkBoxGroup.setChecked(true);
					tmpList.add(sysRole.getRoleId());
				}
				checkBoxGroups.add(checkBoxGroup);
			}
			roleIds = (String[]) tmpList.toArray(new String[0]);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		return "editRole";
	}

	/**
	 * 用户修改自定义信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editMyself() throws Exception {

		try {
			// SysUser ownUser = sysUserService.findUnique(
			// "from SecuritySysUser where username = ? and password = ?",
			// new Object[]{username, md5.encodePassword(oldPassword.trim(),
			// null)});
			//
			// if (null == ownUser) {
			// validateInfo.setMsg("");
			// validateInfo.getErrors().put("oldPassword", "原始密码输入错误！");
			// } else {
			sysUserService.save(sysUser);

			addMessage("更新信息操作成功！");
			// }

		} catch (Exception e) {
			addError("更新信息操作失败！错误原因为：" + e.getMessage(), e);
		}

		return "editMyselfSuccess";
	}

	// 验证原始密码输入是否正确
	public void checkPassword() throws Exception {
		String result = "";
		HttpServletResponse response = Struts2Utils.getResponse();
		try {
			SysUser ownUser = sysUserService.findUnique(
					"from SecuritySysUser where username = ? and password = ?",
					new Object[] { sysUser.getUsername(),
							md5.encodePassword(oldPassword.trim(), null) });

			if (null == ownUser) {
				result = "1";// 原始密码输入错误的情况
			} else {
				result = "2";
			}
			response.getWriter().print(result);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			// addError("更新信息操作失败！错误原因为：" + e.getMessage(), e);
		}
	}

	public String editPassword() throws Exception {
		try {
			sysUserService.save(sysUser);
			addMessage("修改密码操作成功！");
		} catch (Exception e) {
			addError("修改密码操作失败！错误原因为：" + e.getMessage(), e);
		}
		return "editPasswordSuccess";
	}

	public String resetPassword() throws Exception {
		try {
			sysUserService.save(sysUser);
			addMessage("密码重置成功！");
		} catch (Exception e) {
			addError("密码重置失败！错误原因为：" + e.getMessage(), e);
		}
		return "resetPasswordSuccess";
	}

	public void prepareResetPassword() throws Exception {
		if (StringUtils.isNotEmpty(userId)) {
			sysUser = sysUserService.getAndInitEntity(userId);
		} else {
			sysUser = new SysUser();
		}

		if (mPassword != null && !"".equals(mPassword.trim())) {
			sysUser.setPassword(md5.encodePassword(mPassword.trim(), null));
		}
	}

	public void prepareEditPassword() throws Exception {
		if (StringUtils.isNotEmpty(userId)) {
			sysUser = sysUserService.getAndInitEntity(userId);
		} else {
			sysUser = new SysUser();
		}

		if (mPassword != null && !"".equals(mPassword.trim())) {
			sysUser.setPassword(md5.encodePassword(mPassword.trim(), null));
		}
	}

	public void prepareEditMyself() throws Exception {

		if (StringUtils.isNotEmpty(userId)) {
			sysUser = sysUserService.getAndInitEntity(userId);
		} else {
			sysUser = new SysUser();
		}

		if (mPassword != null && !"".equals(mPassword.trim())) {
			sysUser.setPassword(md5.encodePassword(mPassword.trim(), null));
		}
	}

	public String grantSysUserRoles() throws Exception {
		try {
			sysUserService.grantSysUserRoles(sysUser, roleIds);
			addMessage("保存用户角色操作成功！");
		} catch (Exception e) {
			addError("保存用户角色操作失败！错误原因为：" + e.getMessage(), e);
		}

		return "reload";

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the booleanEnum
	 */
	public BooleanEnum getBooleanEnum() {
		return booleanEnum;
	}

	public List getEnabledStatusList() {
		return booleanEnum.getEnumList();
	}

	public List getAccountStatusList() {
		return booleanEnum.getEnumList();
	}

	/**
	 * @return the roleList
	 */
	public List getRoleList() {
		return roleList;
	}

	public String getManufacId() {
		return manufacId;
	}

	public void setManufacId(String manufacId) {
		this.manufacId = manufacId;
	}

	// </editor-fold>
}
