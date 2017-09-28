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
import com.cndatacom.common.service.IBaseService;
import com.cndatacom.common.web.action.SimpleActionSupport;
import com.cndatacom.rbac.common.service.ISysTypeService;
import com.cndatacom.rbac.pojo.SysAuthority;
import com.cndatacom.rbac.pojo.SysMenu;
import com.cndatacom.rbac.pojo.SysRole;
import com.cndatacom.rbac.pojo.SysType;
import com.cndatacom.rbac.system.service.ISysAuthorityService;
import com.cndatacom.rbac.system.service.ISysMenuService;
import com.cndatacom.rbac.system.service.ISysRoleService;

@Controller
@Action("sysAuthority")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results({
    @Result(name = "list", location = "/rbac/sys/authority/SysAuthorityList.jsp", type = "dispatcher"),
    @Result(name = "input", location = "/rbac/sys/authority/SysAuthorityEdit.jsp", type = "dispatcher"),
    @Result(name = "reload", location = "sysAuthority!list.action", type = "redirect"),
    //@Result(type = "json", name = "success", params = {"root", "page", "excludeProperties", "result.*\\.sysRoles"}),
    @Result(name = "status", type = "json", params = {"root","validateInfo"})})
public class SysAuthorityAction extends SimpleActionSupport {

    private static final long serialVersionUID = 1L;
    @Resource
    private ISysRoleService sysRoleService;
    @Resource
    private ISysMenuService sysMenuService;
    @Resource
    private ISysTypeService sysTypeService;
    List<SysType> allResults;
    @Resource
    private ISysAuthorityService sysAuthorityService;
    private SysAuthority sysAuthority;
    // 主键标识
    private String authorityId;
    private String sysMenuId;

    //####################修改时，“菜单”下拉框没赋值#############

    // <editor-fold defaultstate="collapsed" desc="实现基类的抽象方法">
    /**
     *  获取spring注入service对象
     */
    @Override
    protected IBaseService getManager() {
        return sysAuthorityService;
    }

    /**
     *  创建新的实体对象
     */
    @Override
    protected Object createNewInstance() {
        return new SysAuthority();
    }

    /**
     *  获取实体对象
     */
    @Override
    public Object getModel() {
        return getSysAuthority();
    }

    /**
     * 设置实体对象
     */
    @Override
    public void setModel(Object obj) {
        setSysAuthority((SysAuthority) obj);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="覆盖基类中的增、删、查找方法">
    /**
     * 删除选中的实体（批量删除）
     * @return "reload"
     * @throws Exception
     */
    @Override
    public String delete() throws Exception {

        try {
            //#####################需要重构：移入service层
            List<SysRole> results = sysRoleService.findSysRoleByAuthorityIds(getIds());

            if (null != results && results.size() > 0) {

                StringBuilder sb = new StringBuilder();

                sb.append("被删除的权限被如下角色引用：");

                for (SysRole role : results) {
                    sb.append("【" + role.getRoleName() + "】").append(",");
                }
                throw new ActionException(sb.substring(0, sb.length() - 1).toString());
            }

            sysAuthorityService.deleteByIds(getIds());
            addMessage("删除操作成功！");
        } catch (Exception e) {
            addError("删除操作失败！错误原因为：" + e.getMessage(), e);
        }
        return "reload";
    }

    /**
     * 分页查询记录集
     * @return "list"
     * @throws Exception
     */
    @Override
    public String list() throws Exception {
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
            //##################需要重构：移入service层###########
            //##################业务功能需要讨论：是否权限被引用了就不能修改？###########
            if (null != sysAuthority.getAuthorityId()) {
                List<SysRole> roleResults = sysRoleService.findSysRoleByAuthorityId(authorityId);
                if (roleResults.size() > 0) {
                    StringBuilder sb = new StringBuilder("被修改的权限被如下角色引用：【");
                    for (SysRole role : roleResults) {
                        sb.append(role.getRoleName()).append("，");
                    }
                    sb.deleteCharAt(sb.length() - 1).append("】，不能修改！");
                    throw new ActionException(sb.toString());
                }
            }

            List<SysAuthority> results = sysAuthorityService.findByPropertyNameAndAuthorityId("authorityName", sysAuthority.getAuthorityName(), sysAuthority.getAuthorityId());

            if (null != results && results.size() > 0) {
                throw new ActionException("权限名称【" + sysAuthority.getAuthorityName() + "】已经存在！");
            }

            //表示为URL权限类型，要验证URL是否存在
            if (0L == sysAuthority.getAuthorityType().longValue()) {
                results = sysAuthorityService.findByPropertyNameAndAuthorityId("authorityUrl", sysAuthority.getAuthorityUrl(), sysAuthority.getAuthorityId());

                if (null != results && results.size() > 0) {
                    throw new ActionException("权限URL【" + sysAuthority.getAuthorityUrl() + "】已经存在！");
                }
            }

            results = sysAuthorityService.findByPropertyNameAndAuthorityId("authorityNote", sysAuthority.getAuthorityNote(), sysAuthority.getAuthorityId());

            if (null != results && results.size() > 0) {
                throw new ActionException("权限说明【" + sysAuthority.getAuthorityNote() + "】已经存在！");
            }


            sysAuthorityService.save(sysAuthority);
            addMessage("保存操作成功！");

        } catch (Exception e) {
           addError("保存操作失败！错误原因为：" + e.getMessage(),e);
        }
        return "reload";
    }

    @Override
    public String input() throws Exception {
    	if(authorityId != null){
    		sysMenuId = sysAuthority.getSysMenu().getId();
    	}
        //查询所有的权限类型
        //#####################需要重构：移入service层和dao层
        allResults = sysTypeService.find("from SysType where typeCategory.typeCategoryid = '1' ");
        return INPUT;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (StringUtils.isNotEmpty(authorityId)) {
            sysAuthority = sysAuthorityService.getAndInitEntity(authorityId);
        } else {
            sysAuthority = new SysAuthority();
        }

        if (StringUtils.isNotEmpty(sysMenuId )) {
            SysMenu sysMenu = new SysMenu();
            sysMenu.setId(sysMenuId);
            sysAuthority.setSysMenu(sysMenu);
        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="自定义方法">
    /**
     * @return the sysAuthority
     */
    public SysAuthority getSysAuthority() {
        return sysAuthority;
    }

    /**
     * @param sysAuthority the sysAuthority to set
     */
    public void setSysAuthority(SysAuthority sysAuthority) {
        this.sysAuthority = sysAuthority;
    }

    public String getSysMenuId() {
        return sysMenuId;
    }

    public void setSysMenuId(String sysMenuId) {
        this.sysMenuId = sysMenuId;
    }

    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
    }

    public ISysMenuService getSysMenuService() {
        return sysMenuService;
    }

    public List<SysType> getAllResults() {
        return allResults;
    }

    public void setAllResults(List<SysType> allResults) {
        this.allResults = allResults;
    }
    // </editor-fold>
}
