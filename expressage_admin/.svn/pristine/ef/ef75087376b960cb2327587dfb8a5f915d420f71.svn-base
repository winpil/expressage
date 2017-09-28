package com.cndatacom.rbac.system.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.cndatacom.common.orm.Page;
import com.cndatacom.common.orm.PropertyFilter;
import com.cndatacom.common.service.IBaseService;
import com.cndatacom.common.web.action.SimpleActionSupport;
import com.cndatacom.rbac.pojo.SysGroup;
import com.cndatacom.rbac.pojo.SysUser;
import com.cndatacom.rbac.system.service.ISysGroupService;

@Controller
@Action("sysGroup")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results({
    @Result(name = "index", location = "/rbac/sys/group/SysGroupIndex.jsp", type = "dispatcher"),
    @Result(name = "list", location = "/rbac/sys/group/SysGroupList.jsp", type = "dispatcher"),
    @Result(name = "input", location = "/rbac/sys/group/SysGroupEdit.jsp", type = "dispatcher"),
    @Result(name = "groupTree", location = "/rbac/sys/group/SysGroupTree.jsp", type = "dispatcher"),
    @Result(name = "reload", location = "sysGroup!list.action", type = "redirect"),
    @Result(name = "leafGroups", type = "json", params = {"root", "leafGroups"}),
    @Result(name = "authorityGroups", type = "json", params = {"root", "treeNodes"}),
    @Result(name = "status", type = "json", params = {"root", "validateInfo"})
//    @Result(type = "redirectAction", name = "reload", params = {"actionName", "sysGroup!listChildrenSysGroups", "parentId", "${parentId}"}),
//    @Result(type = "redirectAction", name = "listChildrenGroups", params = {"actionName", "sysGroup!listChildrenSysGroups", "parentId", "${parentId}"})
    })
public class SysGroupAction extends SimpleActionSupport {

    private static final long serialVersionUID = 1L;
    // 主键标识
    private String groupId;
    //父id
    private String parentId;
    private String userId;
    private SysGroup rootGroup;
    private List<SysGroup> childrenGroups;
    @Resource
    private ISysGroupService sysGroupService;
    private SysGroup sysGroup;

    // <editor-fold defaultstate="collapsed" desc="实现基类的抽象方法">
    /**
     *  获取spring注入service对象
     */
    @Override
    protected IBaseService getManager() {
        return sysGroupService;
    }

    /**
     *  创建新的实体对象
     */
    @Override
    protected Object createNewInstance() {
        return new SysGroup();
    }

    /**
     *  获取实体对象
     */
    @Override
    public Object getModel() {
        return getSysGroup();
    }

    /**
     * 设置实体对象
     */
    @Override
    public void setModel(Object obj) {
        setSysGroup((SysGroup) obj);
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
            //需要重构:移入service层
//            SysUser loginUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            Long ownGroupId = loginUser.getSysGroup().getGroupId();
//            if (getGroupId() <= ownGroupId) {
//                throw new ActionException("此组织架构不能被删除！");
//            }
            //sysGroupService.deleteSysGroup(getGroupId());
            //需要先判断有没有子节点
            sysGroupService.deleteByIds(getPksByIds());
            addMessage("删除操作成功！");
        } catch (Exception e) {
            addError("删除操作失败！错误原因为：" + e.getMessage(), e);
        }
        //刷新右边的导航树
        refreshFrame("menutree", "sysGroup!groupTree.action");
        return RELOAD;
    }

    /**
     * 分页查询记录集
     * @return "list"
     * @throws Exception
     */
    @Override
    public String list() throws Exception {

        SysUser loginUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String ownGroupId = loginUser.getSysGroup().getGroupId();

        if (null == parentId) {
            parentId = ownGroupId;
        } 
//        else if (parentId < ownGroupId) {
//            parentId = ownGroupId;
//        }

        getFilters().add(new PropertyFilter("EQS_parent.id", parentId + ""));

        //设置默认排序方式
        if (!getPage().isOrderBySetted()) {
            getPage().setOrderBy("orderId");
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
            getManager().save(getModel());
            addMessage("数据保存成功！");
            //刷新右边的导航树
        refreshFrame("menutree", "sysGroup!groupTree.action");
        } catch (Exception e) {
            addError("数据保存失败！", e);
        }
        
        return RELOAD;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (getGroupId() != null) {
            sysGroup = sysGroupService.getAndInitEntity(getGroupId());
        } else {
            sysGroup = new SysGroup();
        }

        if (parentId != null) {
            SysGroup parentGroup = sysGroupService.getAndInitEntity(parentId);

            sysGroup.setParent(parentGroup);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="自定义方法">
    /**
     * @return the sysGroup
     */
    public SysGroup getSysGroup() {
        return sysGroup;
    }

    /**
     * @param sysGroup the sysGroup to set
     */
    public void setSysGroup(SysGroup sysGroup) {
        this.sysGroup = sysGroup;
    }

    public List<SysGroup> getChildrenGroups() {
        return childrenGroups;
    }

    public void setChildrenGroups(List<SysGroup> childrenGroups) {
        this.childrenGroups = childrenGroups;
    }

    public SysGroup getRootGroup() {
        return rootGroup;
    }

    public void setRootGroup(SysGroup rootGroup) {
        this.rootGroup = rootGroup;
    }
    List<SysGroup> rootGroups;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<SysGroup> getRootGroups() {
        return rootGroups;
    }

    public void setRootGroups(List<SysGroup> rootGroups) {
        this.rootGroups = rootGroups;
    }
    private Long[] roleIds;

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    private List<SysGroup> leafGroups;

    public List<SysGroup> getLeafGroups() {
        return leafGroups;
    }

    public void setLeafGroups(List<SysGroup> leafGroups) {
        this.leafGroups = leafGroups;
    }

    public String index() {
        return "index";
    }

    public String groupTree() {
        return "groupTree";
    }
//
//
//
//    public String input() throws Exception {
//        return INPUT;
//    }

    public String listChildrenSysGroups() throws Exception {

        SysUser loginUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String ownGroupId = loginUser.getSysGroup().getGroupId();

        if (null == parentId) {
            parentId = ownGroupId;
        }
//        else if (parentId < ownGroupId) {
//            parentId = ownGroupId;
//        }


        //childrenGroups = sysGroupService.find("from SysGroup where parent.groupId = ? order by orderId ,groupId",parentId);

        rootGroup = sysGroupService.get(parentId);

        return "childrenGroups";
    }

    public String listSysGroups() throws Exception {

        childrenGroups = sysGroupService.find("from SysGroup where parent.id = ? order by orderId , id", parentId);

        rootGroup = sysGroupService.get("1");

        return "listSysGroups";
    }

    public String listLeafGroups() throws Exception {

        /*try {
        leafGroups = sysGroupService.findLeafSysGroup();
        } catch (Exception e) {
        logger.error(e.toString());
        }*/

        return "leafGroups";
    }

    public String listAuthorityGroups() throws Exception {

        /*try{
        treeNodes = sysGroupService.getAuthoritySysGroupByParentId(userId, parentId);
        }catch(Exception e){
        }
         */
        return "authorityGroups";
    }

    public void prepareGrantSysGroupRoles() throws Exception {
        if (getGroupId() != null) {
            sysGroup = sysGroupService.getAndInitEntity(getGroupId());
        } else {
            sysGroup = new SysGroup();
        }
    }

    public void prepareListCheckedCheckBoxGroups() throws Exception {
        if (getGroupId() != null) {
            sysGroup = sysGroupService.getAndInitEntity(getGroupId());
        } else {
            sysGroup = new SysGroup();
        }
    }

    public String findSysGroup() throws Exception {

        sysGroup = sysGroupService.get(getGroupId());

        System.out.println(sysGroup);

        return "sysGroup";
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * @return the groupId
     */
    public String getGroupId() {
        return StringUtils.isEmpty(groupId) ?null:groupId;
    }

    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    // </editor-fold>
}
