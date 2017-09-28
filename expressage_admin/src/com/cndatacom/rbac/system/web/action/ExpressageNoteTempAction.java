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
import com.cndatacom.rbac.pojo.ExpressageNote;
import com.cndatacom.rbac.pojo.ExpressageNoteTemp;
import com.cndatacom.rbac.system.service.ExpressageNoteService;
import com.cndatacom.rbac.system.service.ExpressageNoteTempService;

@Controller
@Action("expressageNoteTemp")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results({
		@Result(name = "list", location = "/rbac/sys/expressage/note/expressageNoteTempList.jsp", type = "dispatcher"),
		@Result(name = "input", location = "/rbac/sys/expressage/note/expressageNoteTempEdit.jsp", type = "dispatcher"),
		@Result(name = "reload", location = "expressageNoteTemp!list.action", type = "redirect"),
		@Result(name = "status", type = "json", params = { "root","validateInfo" }), })

public class ExpressageNoteTempAction  extends SimpleActionSupport {

	private static final long serialVersionUID = 1L;

	private String tempId;
	private ExpressageNoteTemp expressageNoteTemp;

	@Resource
	private ExpressageNoteTempService expressageNoteTempService;
	
	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	public ExpressageNoteTemp getExpressageNoteTemp() {
		return expressageNoteTemp;
	}

	public void setExpressageNoteTemp(ExpressageNoteTemp expressageNoteTemp) {
		this.expressageNoteTemp = expressageNoteTemp;
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
		if(tempId!=null){
			expressageNoteTemp = expressageNoteTempService.findUniqueBy("tempId",tempId);
		}
		return INPUT;
	}
	
	@Override
	protected IBaseService getManager() {
		return expressageNoteTempService;
	}

	@Override
	protected Object createNewInstance() {
		return expressageNoteTemp;
	}

	@Override
	public Object getModel() {
		return getExpressageNoteTemp();
	}

	@Override
	public void setModel(Object obj) {
		setExpressageNoteTemp((ExpressageNoteTemp)obj);
	}



}
