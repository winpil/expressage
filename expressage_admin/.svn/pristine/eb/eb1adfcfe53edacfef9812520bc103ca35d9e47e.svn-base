package com.cndatacom.rbac.system.web.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.common.web.action.SimpleActionSupport;
import com.cndatacom.common.web.struts2.Struts2Utils;
import com.cndatacom.rbac.pojo.ExpressageBranch;
import com.cndatacom.rbac.pojo.ExpressageCity;
import com.cndatacom.rbac.pojo.ExpressageCompany;
import com.cndatacom.rbac.pojo.ExpressageDistrict;
import com.cndatacom.rbac.pojo.ExpressageProvincial;
import com.cndatacom.rbac.system.service.ExpressageBranchService;
import com.cndatacom.rbac.system.service.ExpressageCityService;
import com.cndatacom.rbac.system.service.ExpressageCompanyService;
import com.cndatacom.rbac.system.service.ExpressageDistrictService;
import com.cndatacom.rbac.system.service.ExpressageOrderLogService;
import com.cndatacom.rbac.system.service.ExpressageOrderService;
import com.cndatacom.rbac.system.service.ExpressageProvincialService;
import com.cndatacom.rbac.system.service.ExpressageTokenService;
import com.cndatacom.rbac.system.service.ExpressageUserService;

/**
 * 
 * 类名: expressageBranchAction.java</br>
 * 包名：com.cndatacom.rbac.system.web.action</br> 描述: 网点action</br> 发布版本号：</br>
 * 开发人员：莫志明</br> 创建时间： 2015-8-5
 */
@Controller
@Action("expressageBranch")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results({
		@Result(name = "list", location = "/rbac/sys/expressage/branch/expressageBranchList.jsp", type = "dispatcher"),
		@Result(name = "input", location = "/rbac/sys/expressage/branch/expressageBranchEdit.jsp", type = "dispatcher"),
		@Result(name = "reload", location = "expressageBranch!list.action", type = "redirect"),
		@Result(name = "status", type = "json", params = { "root",
				"validateInfo" }), })
public class expressageBranchAction extends SimpleActionSupport {

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
	private ExpressageBranchService expressageBranchService;

	@Resource
	private ExpressageProvincialService expressageProvincialService;
	@Resource
	private ExpressageCityService expressageCityService;
	@Resource
	private ExpressageDistrictService expressageDistrictService;
	@Resource
	ExpressageCompanyService expressageCompanyService;

	private ExpressageBranch expressageBranch;
	private String branchId;
	
	boolean flag = false;
	
	public Map<String, String> getCompanyMap() {
		Map<String, String> map = new HashMap<String, String>();
		Iterator<ExpressageCompany> iterator = expressageCompanyService.getAll().iterator();
		while (iterator.hasNext()) {
			ExpressageCompany company = iterator.next();
			map.put(company.getCompanyId(),company.getCompanyName());
		}
		return map;
	}

	public Map<String, String> getDistrictMap() {
		Map<String, String> map = new HashMap<String, String>();
		Iterator<ExpressageDistrict> iterator ;
		if(flag){
			iterator = expressageDistrictService.getDistrictByCityId("211").iterator();
		}else{
			iterator = expressageDistrictService.getDistrictByCityId(expressageBranch.getCityId().getCityId()).iterator();
		}
		while (iterator.hasNext()) {
			ExpressageDistrict district = iterator.next();
			map.put(district.getDistrictId(),district.getName());
		}
		return map;
	}

	public Map<String, String> getCityMap() {
		Map<String, String> map = new HashMap<String, String>();
		Iterator<ExpressageCity> iterator ;
		if(flag){
			iterator = expressageCityService.getCityByProvincialId("19").iterator();
		}else{
			iterator = expressageCityService.getCityByProvincialId(expressageBranch.getProvincialId().getProvincialId()).iterator();
		}
		while (iterator.hasNext()) {
			ExpressageCity city = iterator.next();
			map.put(city.getCityId(), city.getName());
		}
		return map;
	}

	/**
	 * 省份列表数据准备
	 */
	public Map<String, String> getProvincialMap() {
		Map<String, String> map = new HashMap<String, String>();
		Iterator<ExpressageProvincial> iterator = expressageProvincialService.getAll().iterator();
		while (iterator.hasNext()) {
			ExpressageProvincial provincial = iterator.next();
			map.put(provincial.getProvincialId(), provincial.getName());
		}
		return map;
	}
	

	/**
	 * 查询列表
	 * 
	 * @return "list"
	 * @throws Exception
	 */
	@Override
	public String list() throws Exception {
		return super.list();
	}

	/**
	 * 保存、增加实体
	 * 
	 * @return "reload"
	 * @throws Exception
	 */
	@Override
	public String save() throws Exception {
		super.save();
		return RELOAD;
	}

	/**
	 * 删除实体
	 * 
	 * @return "reload"
	 * @throws Exception
	 */
	@Override
	public String delete() throws Exception {
		super.delete();
		return RELOAD;
	}

	/**
	 * 编辑实体
	 * 
	 * @return "input"
	 * @throws Exception
	 */
	@Override
	public String input() throws Exception {
		if (branchId != null) {
			expressageBranch = expressageBranchService.findUniqueBy("branchId",branchId);
		}else{
			flag=true;
		}
		return INPUT;
	}

	@Override
	protected IBaseService getManager() {
		return expressageBranchService;
	}

	@Override
	protected Object createNewInstance() {
		return expressageBranch;
	}

	@Override
	public Object getModel() {
		return getExpressageBranch();
	}

	@Override
	public void setModel(Object obj) {
		setExpressageBranch((ExpressageBranch) obj);
	}

	public ExpressageBranch getExpressageBranch() {
		return expressageBranch;
	}

	public void setExpressageBranch(ExpressageBranch expressageBranch) {
		this.expressageBranch = expressageBranch;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

}
