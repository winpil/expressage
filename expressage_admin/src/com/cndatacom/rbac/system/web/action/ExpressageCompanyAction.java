package com.cndatacom.rbac.system.web.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.common.web.action.SimpleActionSupport;
import com.cndatacom.rbac.pojo.ExpressageCompany;
import com.cndatacom.rbac.system.service.ExpressageCompanyService;
import com.cndatacom.rbac.system.service.ExpressageOrderLogService;
import com.cndatacom.rbac.system.service.ExpressageOrderService;
import com.cndatacom.rbac.system.service.ExpressageTokenService;
import com.cndatacom.rbac.system.service.ExpressageUserService;

/**
 * 
 * ����: ExpressageCompanyAction.java</br> 
 * ������com.cndatacom.rbac.system.web.action</br> 
 * ����: ��˾action</br> 
 * �����汾�ţ�</br> 
 * ������Ա��Ī־��</br>  
 * ����ʱ�䣺 2015-7-31
 */
@Controller
@Action("expressageCompany")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results( {
	  	@Result(name = "list", location = "/rbac/sys/expressage/company/expressageCompanyList.jsp", type = "dispatcher"),
	  	@Result(name = "input", location = "/rbac/sys/expressage/company/expressageCompanyEdit.jsp", type = "dispatcher"),
		@Result(name = "reload", location = "expressageCompany!list.action", type = "redirect"),
		@Result(name = "status", type = "json", params = { "root","validateInfo" }),
})

public class ExpressageCompanyAction extends SimpleActionSupport{
	
	private static final long serialVersionUID = 1L;
	@Resource
    private ExpressageUserService expressageUserService;
	@Resource
    private ExpressageTokenService expressageTokenService;
	@Resource
    private ExpressageOrderService expressageOrderService;
	@Resource
    private ExpressageOrderLogService expressageOrderLogService;
	@Resource
	private ExpressageCompanyService expressageCompanyService;
	
	private ExpressageCompany expressageCompany;
	private String companyId;


	/**
     * ��ѯ�б�
     * @return "list"
     * @throws Exception
     */
	@Override
	public String list() throws Exception {
		return super.list();
	}

	
	/**
     * ���桢����ʵ��
     * @return "reload"
     * @throws Exception
     */
	@Override
	public String save() throws Exception {
		super.save();
		return "reload";
	}

	
	/**
     * ɾ��ʵ��
     * @return "reload"
     * @throws Exception
     */
	@Override
	public String delete() throws Exception {
		super.delete();
		return "reload";
	}

	
	/**
     * �༭ʵ��
     * @return "input"
     * @throws Exception
     */
	@Override
	public String input() throws Exception {
		if(companyId != null){
			expressageCompany = expressageCompanyService.findUniqueBy("companyId", companyId);
		}
		return INPUT;
	}
	
	
	

	
	
	@Override
	protected IBaseService getManager() {
		return expressageCompanyService;
	}

	@Override
	protected Object createNewInstance() {
		return expressageCompany;
	}

	@Override
	public Object getModel() {
		return getExpressageCompany();
	}

	@Override
	public void setModel(Object obj) {
		setExpressageCompany((ExpressageCompany)obj);
	}

	public ExpressageCompany getExpressageCompany() {
		return expressageCompany;
	}

	public void setExpressageCompany(ExpressageCompany expressageCompany) {
		this.expressageCompany = expressageCompany;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	

}
