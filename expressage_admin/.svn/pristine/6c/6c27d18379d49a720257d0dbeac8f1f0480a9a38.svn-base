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
import com.cndatacom.rbac.pojo.ExpressageOrderLog;
import com.cndatacom.rbac.system.service.ExpressageCourierService;
import com.cndatacom.rbac.system.service.ExpressageFavorableService;
import com.cndatacom.rbac.system.service.ExpressageOrderLogService;
import com.cndatacom.rbac.system.service.ExpressageOrderService;
import com.cndatacom.rbac.system.service.ExpressagePayLogService;
import com.cndatacom.rbac.system.service.ExpressageProductService;
import com.cndatacom.rbac.system.service.ExpressageTokenService;
import com.cndatacom.rbac.system.service.ExpressageUserService;

/**
 * 
 * 类名: ExpressageLogAction</br> 
 * 包名：com.cndatacom.rbac.system.web.action </br> 
 * 描述: 订单日志</br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2016-10-8 
 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
 */
@Controller
@Action("expressageOrderLog")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results( {
	  	@Result(name = "list", location = "/rbac/sys/expressage/order/expressageOrderLogList.jsp", type = "dispatcher"),
	  	@Result(name = "input", location = "/rbac/sys/expressage/order/expressageOrderLogEdit.jsp", type = "dispatcher"),
		@Result(name = "reload", location = "expressageOrderLog!list.action", type = "redirect"),
		@Result(name = "status", type = "json", params = { "root","validateInfo" }),
})

public class ExpressageOrderLogAction extends SimpleActionSupport{
	
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
    private ExpressagePayLogService expressagePayLogService;
	@Resource
    private ExpressageProductService expressageProductService;
	
	
	private ExpressageOrderLog expressageOrderLog;
	private String auName;//搜索框内容
	private String searchName;//搜索名
	private String type;//下单方式
	private String status;//快递状态
	private String orderId;
	Page<ExpressageOrderLog> page = new Page<ExpressageOrderLog>();
	

	
	
	/**
     * 查询列表
     * @return "list"
     * @throws Exception
     */
	@Override
	public String list() throws Exception {
		page.setStart(start);
		page.setLimit(limit);
		StringBuffer sb = new StringBuffer("from ExpressageOrderLog where 1=1");
		
		if(StringUtils.isNotBlank(searchName)){
			sb.append(" and "+searchName+" like '%"+auName+"%'");
		}
		
		if(StringUtils.isNotBlank(type)){
			sb.append("and type = "+type);
		}
		if(StringUtils.isNotBlank(status)){
			sb.append("and status = "+status);
		}
		page = expressageOrderLogService.findPage(page, sb.toString());
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
     * 删除实体
     * @return "reload"
     * @throws Exception
     */
	@Override
	public String delete() throws Exception {
		
		super.delete();
		return "reload";
	}
	
	
	
	

	@Override
	protected IBaseService getManager() {
		
		return expressageOrderLogService;
	}

	@Override
	protected Object createNewInstance() {
		
		return expressageOrderLog;
	}

	@Override
	public Object getModel() {
		
		return getExpressageOrderLog();
	}

	@Override
	public void setModel(Object obj) {
		setExpressageOrderLog((ExpressageOrderLog)obj);
		
	}

	public ExpressageOrderLog getExpressageOrderLog() {
		return expressageOrderLog;
	}

	public void setExpressageOrderLog(ExpressageOrderLog expressageOrderLog) {
		this.expressageOrderLog = expressageOrderLog;
	}

	public String getAuName() {
		return auName;
	}

	public void setAuName(String auName) {
		this.auName = auName;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
}
