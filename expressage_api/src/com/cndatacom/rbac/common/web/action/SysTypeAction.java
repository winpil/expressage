package com.cndatacom.rbac.common.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cndatacom.common.orm.Page;
import com.cndatacom.common.web.action.CrudActionSupport;
import com.cndatacom.rbac.common.service.ISysTypeService;
import com.cndatacom.rbac.pojo.SysType;

/**
 * 系统类型Action类
 * @author yab
 * 2010-06-17 15:49
 */
@Controller
@Scope("prototype")
@Action("sysType")
@Namespace("/rbac")
@Results( {
		@Result(type = "json", name = "success", params = { "root", "page","excludeNullProperties", "true" }),
		@Result(name = "allResults", type = "json", params = { "root","allResults" })})
public class SysTypeAction extends CrudActionSupport<SysType> {

	private static final long serialVersionUID = -1742412433659653155L;
	
	private String typeId;
	
	Page<SysType> page = new Page<SysType>();
	
	private String typeCode;
	
	List<SysType> allResults;
	
	private String checkItems;//选择项
	private String itemsValue;//值
	
	private Long typeCategoryid;

	@Resource
	ISysTypeService sysTypeService;


	@Override
	public String input() throws Exception {
		return null;
	}

	public String delete() throws Exception {
		return null;
	}

	public String list() throws Exception {
		
		page.setStart(start);
		page.setLimit(limit);
		
		page = sysTypeService.findPage(page,"from SysType where typeCategory.typeCategoryid = ? order by typeId",typeCategoryid);
		
		return SUCCESS;
	}
	
	
	/**
	 * 查询当前类型下所有的SysType信息
	 */
	public String listSysTypeByTypeId() throws Exception {
		
		allResults = sysTypeService.find("from SysType where typeCategory.typeCategoryid = ?", typeCategoryid);
		
		return "allResults";
	}

	@Override
	protected void prepareModel() throws Exception {
		if(typeId != null){
			sysType = sysTypeService.getAndInitEntity(typeId);
		}else {
			sysType = new SysType();
		}
	}

	public String save() throws Exception {
		return null;
	}

	public SysType getModel() {
		return sysType;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public Page<SysType> getPage() {
		return page;
	}

	public void setPage(Page<SysType> page) {
		this.page = page;
	}
	
	public String getCheckItems() {
		return checkItems;
	}

	public void setCheckItems(String checkItems) {
		this.checkItems = checkItems;
	}

	public String getItemsValue() {
		return itemsValue;
	}

	public void setItemsValue(String itemsValue) {
		this.itemsValue = itemsValue;
	}

	public List<SysType> getAllResults() {
		return allResults;
	}

	public void setAllResults(List<SysType> allResults) {
		this.allResults = allResults;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	private SysType sysType;

	public SysType getSysType() {
		return sysType;
	}

	public void setSysType(SysType sysType) {
		this.sysType = sysType;
	}

	public Long getTypeCategoryid() {
		return typeCategoryid;
	}

	public void setTypeCategoryid(Long typeCategoryid) {
		this.typeCategoryid = typeCategoryid;
	}
}
