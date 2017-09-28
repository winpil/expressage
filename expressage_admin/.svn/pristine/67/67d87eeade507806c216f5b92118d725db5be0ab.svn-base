package com.cndatacom.rbac.system.web.action;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cndatacom.common.orm.Page;
import com.cndatacom.common.orm.PropertyFilter;
import com.cndatacom.common.service.IBaseService;
import com.cndatacom.common.web.action.SimpleActionSupport;
import com.cndatacom.rbac.pojo.SysCity;
import com.cndatacom.rbac.system.service.ISysCityService;



@Controller
@Action("sysCity")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results( {
			@Result(name = "index", location = "/rbac/sys/city/SysCityIndex.jsp", type = "dispatcher"),
            @Result(name = "list", location = "/rbac/sys/city/SysCityList.jsp", type = "dispatcher"),
            @Result(name = "cityTree", location = "/rbac/sys/city/SysCityTree.jsp", type = "dispatcher"),
            @Result(name = "input", location = "/rbac/sys/city/SysCityEdit.jsp", type = "dispatcher"),
            @Result(name = "reload", location = "sysCity!list.action", type = "redirect"),
            @Result(name = "status", type = "json", params = {"root","validateInfo"})
         })
public class SysCityAction extends SimpleActionSupport {

		private static final long serialVersionUID = 1738896387611947680L;
		@Resource
        private ISysCityService sysCityService;
        private SysCity sysCity;
        private SysCity parentCity;
        private String parentId;


		// <editor-fold defaultstate="collapsed" desc="实现基类的抽象方法">
        /**
         *  获取spring注入service对象
         */
        @Override
        protected IBaseService getManager() {
            return sysCityService;
        }

        /**
         *  创建新的实体对象
         */
        @Override
        protected Object createNewInstance() {
            return new SysCity();
        }

        /**
         *  获取实体对象
         */
        @Override
        public Object getModel() {
            return getSysCity();
        }

        /**
         * 设置实体对象
         */
        @Override
        public void setModel(Object obj) {
            setSysCity((SysCity) obj);
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
            return super.delete();
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
             getFilters().add(new PropertyFilter("EQS_parent:parent.id", getId()));
             // 设置默认排序方式
             if (!getPage().isOrderBySetted()) {
                 getPage().setOrderBy("sort");
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
            	if("".equals(sysCity.getId()))
            		sysCity.setId(null);
            	
                sysCityService.saveSysCity(sysCity);
                addMessage("保存操作成功！");
            } catch (Exception e) {
                addError("保存操作失败！错误原因为：" + e.getMessage(), e);
            }
            //刷新右边的导航树
            refreshFrame("citytree", "sysCity!cityTree.action");
            return RELOAD;
        }
        
        @Override
        protected void prepareModel() throws Exception {
            super.prepareModel();
            if (StringUtils.isNotEmpty(parentId)) {
                parentCity = sysCityService.getAndInitEntity(parentId);
                sysCity.setParent(parentCity);
            }
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="自定义方法">
        
        public String index() {
        	return "index";
        }
        
        public String cityTree() {
            return "cityTree";
        }
        
        public String input() throws Exception {
            return INPUT;
        }

        /**
         * @return the sysCity
         */
        public SysCity getSysCity() {
            return sysCity;
        }

        /**
         * @param sysCity the sysCity to set
         */
        public void setSysCity(SysCity sysCity) {
            this.sysCity = sysCity;
        }
        
        public SysCity getParentCity() {
			return parentCity;
		}

		public void setParentCity(SysCity parentCity) {
			this.parentCity = parentCity;
		}

		public String getParentId() {
			return parentId;
		}

		public void setParentId(String parentId) {
			this.parentId = parentId;
		}

        // </editor-fold>
}