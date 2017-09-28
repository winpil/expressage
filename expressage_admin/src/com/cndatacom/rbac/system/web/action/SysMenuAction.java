package com.cndatacom.rbac.system.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cndatacom.common.exception.ActionException;
import com.cndatacom.common.orm.Page;
import com.cndatacom.common.orm.PropertyFilter;
import com.cndatacom.common.service.IBaseService;
import com.cndatacom.common.web.action.SimpleActionSupport;
import com.cndatacom.rbac.pojo.SysAuthority;
import com.cndatacom.rbac.pojo.SysMenu;
import com.cndatacom.rbac.system.service.ISysAuthorityService;
import com.cndatacom.rbac.system.service.ISysMenuService;

@Controller
@Action("sysMenu")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results({
    @Result(name = "index", location = "/rbac/sys/menu/SysMenuIndex.jsp", type = "dispatcher"),
    @Result(name = "menuTree", location = "/rbac/sys/menu/SysMenuTree.jsp", type = "dispatcher"),
    @Result(name = "list", location = "/rbac/sys/menu/SysMenuList.jsp", type = "dispatcher"),
    @Result(name = "input", location = "/rbac/sys/menu/SysMenuEdit.jsp", type = "dispatcher"),
    @Result(name = "reload", location = "sysMenu!list.action", type = "redirect"),
    @Result(name = "status", type = "json", params = {"root","validateInfo"}),
    @Result(name = "leafMenus", type = "json", params = {"root","leafMenus"}),
    @Result(name = "rootMenus", type = "json", params = {"root","rootMenus"})})
public class SysMenuAction extends SimpleActionSupport {

    private static final long serialVersionUID = 1L;
    @Resource
    private ISysMenuService sysMenuService;
    @Resource
    private ISysAuthorityService sysAuthorityService;
    // 父id
    private String parentId;
    private SysMenu sysMenu;
    private SysMenu parentMenu;
    private String data;
    List<SysMenu> rootMenus;
    private List<SysMenu> leafMenus;

    // <editor-fold defaultstate="collapsed" desc="实现基类的抽象方法">
    /**
     *  获取spring注入service对象
     */
    @Override
    protected IBaseService getManager() {
        return sysMenuService;
    }

    /**
     *  创建新的实体对象
     */
    @Override
    protected Object createNewInstance() {
        return new SysMenu();
    }

    /**
     *  获取实体对象
     */
    @Override
    public Object getModel() {
        return getSysMenu();
    }

    /**
     * 设置实体对象
     */
    @Override
    public void setModel(Object obj) {
        setSysMenu((SysMenu) obj);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="覆盖基类中的增、删、查找方法">
    /**
     * 删除选中的实体（批量删除）（菜单是否可以直接删除，不做级联）
     * @return "reload"
     * @throws Exception
     */
    @Override
    public String delete() throws Exception {
        try {
            // <editor-fold defaultstate="collapsed" desc="移入service层">
            List pks = getPksByIds();
            for (int i = 0; i < pks.size(); i++) {
                String id =  (String)pks.get(i);
                List<SysAuthority> results = sysAuthorityService.findOwnSysAuthorityByMenuId(id);

                if (null != results && results.size() > 0) {
                    StringBuilder sb = new StringBuilder("被删除的菜单被如下权限引用：【");
                    for (SysAuthority auth : results) {
                        sb.append(auth.getAuthorityNote()).append(",");
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append("】，不能被删除！");

                    throw new ActionException(sb.toString());
                }
                sysMenuService.deleteSysMenu(id);
            }// </editor-fold>
            addMessage("删除操作成功！");
        } catch (Exception e) {
            addError("删除操作失败！错误原因为：" + e.getMessage(), e);
        }
        //刷新右边的导航树
        refreshFrame("menutree", "sysMenu!menuTree.action");

        return RELOAD;
    }

    /**
     * 分页查询记录集
     * @return "list"
     * @throws Exception
     */
    @Override
    public String list() throws Exception {
        if (getId() == null || "".equals(getId())) {
            setId("1");
        }
        parentId = getId();

        //添加过滤条件
        getFilters().add(new PropertyFilter("EQS_parent:pi.id", getId() + ""));
        // 设置默认排序方式
        if (!getPage().isOrderBySetted()) {
            getPage().setOrderBy("theSort");
            getPage().setOrder(Page.ASC);
        }

        return super.list();
    }

    /**
     * 新增、更新实体时保存
     * @return "reload"
     * @throws Exception
     */
    @Override
    public String save() throws Exception {
        try {
            // 验证添加菜单项的父菜单是否有权限引用
            if (null != parentId) {
                List<SysAuthority> results = sysAuthorityService.findOwnSysAuthorityByMenuId(parentId);

                if (null != results && results.size() > 0) {
                    StringBuilder sb = new StringBuilder("被删除的菜单被如下权限引用：");
                    for (SysAuthority auth : results) {
                        sb.append("【" + auth.getAuthorityName()).append(",");
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append("】，不能添加子菜单！");

                    throw new ActionException(sb.toString());
                }
            }
            sysMenuService.saveSysMenu(sysMenu);
            addMessage("保存操作成功！");
        } catch (Exception e) {
            addError("保存操作失败！错误原因为：" + e.getMessage(), e);
        }
        //刷新右边的导航树
        refreshFrame("menutree", "sysMenu!menuTree.action");
        return RELOAD;
    }

    @Override
    protected void prepareModel() throws Exception {
        super.prepareModel();
        if (StringUtils.isNotEmpty(parentId)) {
            parentMenu = sysMenuService.getAndInitEntity(parentId);
            sysMenu.setParent(parentMenu);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="自定义方法">
    /**
     * @return the sysMenu
     */
    public SysMenu getSysMenu() {
        return sysMenu;
    }

    /**
     * @param sysMenu the sysMenu to set
     */
    public void setSysMenu(SysMenu sysMenu) {
        this.sysMenu = sysMenu;
    }

   
    public List<SysMenu> getRootMenus() {
        return rootMenus;
    }

    public void setRootMenus(List<SysMenu> rootMenus) {
        this.rootMenus = rootMenus;
    }
    private Long[] roleIds;

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<SysMenu> getLeafMenus() {
        return leafMenus;
    }

    public void setLeafMenus(List<SysMenu> leafMenus) {
        this.leafMenus = leafMenus;
    }

    public String index() {
        return "index";
    }

    public String menuTree() {
        return "menuTree";
    }

    public String input() throws Exception {
        return INPUT;
    }

    /*
     * listLeafMenus()新版本没有用到
     */
//    public String listLeafMenus() throws Exception {
//
//        try {
//            leafMenus = sysMenuService.findLeafSysMenu();
//        } catch (Exception e) {
//            logger.error(e.toString());
//        }
//
//        return "leafMenus";
//    }
    /*
     * listLeafMenus()新版本没有用到
     */
//    public void prepareGrantSysMenuRoles() throws Exception {
//        if (id != null) {
//            sysMenu = sysMenuService.getAndInitEntity(id);
//        } else {
//            sysMenu = new SysMenu();
//        }
//    }
    /*
     * prepareListCheckedCheckBoxGroups()新版本没有用到
     */
//    public void prepareListCheckedCheckBoxGroups() throws Exception {
//        if (id != null) {
//            sysMenu = sysMenuService.getAndInitEntity(id);
//        } else {
//            sysMenu = new SysMenu();
//        }
//    }
//    protected void goUrl(String target, String url) throws IOException {
//        HttpServletResponse response = Struts2Utils.getResponse();
//        if (!target.equals("")) {
//            target += ".";
//        }
//        response.getWriter().print(
//                "<script>" + target + "location.href=\"" + url
//                + "\";</script>");
//        response.getWriter().flush();
//        response.getWriter().close();
//    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public SysMenu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(SysMenu parentMenu) {
        this.parentMenu = parentMenu;
    }

     // </editor-fold>
}
