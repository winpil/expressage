package com.cndatacom.rbac.system.web.action;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.common.web.action.SimpleActionSupport;
import com.cndatacom.rbac.pojo.SysBase;
import com.cndatacom.rbac.system.service.ISysBaseService;

@Controller
@Action("sysBase")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results( {
	  	@Result(name = "list", location = "/rbac/sys/base/sysBaseApiList.jsp", type = "dispatcher"),
	  	@Result(name = "input", location = "/rbac/sys/base/sysBaseApiEdit.jsp", type = "dispatcher"),
		@Result(name = "reload", location = "sysBase!list.action", type = "redirect"),
		@Result(name = "status", type = "json", params = { "root","validateInfo" }),
})
public class SysBaserAction extends SimpleActionSupport{
	private static final long serialVersionUID = 1L;
	@Resource
    private ISysBaseService isysBaseService;
	private SysBase sysBase;
	private String baseId;
	
	@Override
	public String list() throws Exception {
		return super.list();
	}  
	
	@Override
	public String input() throws Exception {
		if(StringUtils.isNotBlank(baseId)){
			sysBase = isysBaseService.findUniqueBy("baseId", baseId);
		}
		
		return INPUT;
	}
	
	@Override
    public String save() throws Exception {
//		if(StringUtils.isBlank(sysBase.getBaseId())){
//			baseId = null;
//		}
		
		isysBaseService.save(sysBase);
		
		return RELOAD;
    }

	@Override
	protected Object createNewInstance() {
		return sysBase;
	}

	@Override
	protected IBaseService getManager() {
		return isysBaseService;
	}

	@Override
	public Object getModel() {
		return getSysBase();
	}

	@Override
	public void setModel(Object obj) {
		setSysBase((SysBase) obj);
	}

	public SysBase getSysBase() {
		return sysBase;
	}

	public void setSysBase(SysBase sysBase) {
		this.sysBase = sysBase;
	}

	public String getBaseId() {
		return baseId;
	}

	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}
	
	
	
}
