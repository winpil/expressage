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
import com.cndatacom.common.service.IBaseService;
import com.cndatacom.common.web.action.SimpleActionSupport;
import com.cndatacom.rbac.pojo.ExpressageLog;
import com.cndatacom.rbac.system.service.ExpressageCourierService;
import com.cndatacom.rbac.system.service.ExpressageFavorableService;
import com.cndatacom.rbac.system.service.ExpressageLogService;
import com.cndatacom.rbac.system.service.ExpressageMessageService;
import com.cndatacom.rbac.system.service.ExpressageOrderLogService;
import com.cndatacom.rbac.system.service.ExpressageOrderService;
import com.cndatacom.rbac.system.service.ExpressagePayLogService;
import com.cndatacom.rbac.system.service.ExpressageTokenService;
import com.cndatacom.rbac.system.service.ExpressageUserService;

/**
 * 
 * 类名: ExpressageLogActon.java</br> 
 * 包名：com.cndatacom.rbac.system.web.action</br> 
 * 描述: 用户日志action</br> 
 * 发布版本号：</br> 
 * 开发人员：莫志明</br>  
 * 创建时间： 2015-7-30
 */
@Controller
@Action("expressageLog")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results( {
	  	@Result(name = "list", location = "/rbac/sys/expressage/log/expressageLogList.jsp", type = "dispatcher"),
	  	@Result(name = "input", location = "/rbac/sys/expressage/log/expressageLogEdit.jsp", type = "dispatcher"),
		@Result(name = "reload", location = "expressageLog!list.action", type = "redirect"),
		@Result(name = "status", type = "json", params = { "root","validateInfo" }),
})

public class ExpressageLogActon extends SimpleActionSupport{
	
	private static final long serialVersionUID = 1L;
	@Resource
    private ExpressageUserService expressageUserService;
	@Resource
    private ExpressageTokenService expressageTokenService;
	@Resource
    private ExpressageCourierService expressageCourierService;
	@Resource
    private ExpressageOrderService expressageOrderService;
	@Resource
    private ExpressageOrderLogService expressageOrderLogService;
	@Resource
    private ExpressageFavorableService expressageFavorableService;
	@Resource
    private ExpressageMessageService expressageMessageService;
	@Resource
    private ExpressagePayLogService expressagePayLogService;
	@Resource
    private ExpressageLogService expressageLogService;     
	
	
	private ExpressageLog expressageLog;
	private String logId;
	private String type;
	Page<ExpressageLog> page = new Page<ExpressageLog>();
		
	
	/**
     * 查询列表
     * @return "list"
     * @throws Exception
     */
	@Override
	public String list() throws Exception {
		
		page.setStart(start);
		page.setLimit(limit);
		StringBuffer sb = new StringBuffer("from ExpressageLog where 1=1");
		if(StringUtils.isNotBlank(type)){
			if(type.equals("1")){
				sb.append("and type=1");
			}else{
				sb.append("and type=2");
			}
		}
		page = expressageLogService.findPage(page, sb.toString());
		setPage(page);
		return "list";
		
	}

	/**
     * 保存、增加实体
     * @return "reload"
     * @throws Exception
     */
	@Override
	public String save() throws Exception {
		super.save();
		return "reload";
	}
	
	/**
     * 编辑实体
     * @return "input"
     * @throws Exception
     */
	@Override
	public String input() throws Exception {
		if(logId != null){
			expressageLog = expressageLogService.findUniqueBy("logId", logId);
		}
		return INPUT;
	}

	@Override
	protected IBaseService getManager() {
		
		return expressageLogService;
	}

	@Override
	protected Object createNewInstance() {
		
		return expressageLog;
	}

	@Override
	public Object getModel() {
		
		return getExpressageLog();
	}

	@Override
	public void setModel(Object obj) {
		setExpressageLog((ExpressageLog)obj);
		
	}

	public ExpressageLog getExpressageLog() {
		return expressageLog;
	}

	public void setExpressageLog(ExpressageLog expressageLog) {
		this.expressageLog = expressageLog;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
}
