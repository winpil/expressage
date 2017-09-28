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
import com.cndatacom.rbac.system.service.ExpressageNoteService;

@Controller
@Action("expressageNote")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results({
		@Result(name = "list", location = "/rbac/sys/expressage/note/expressageNoteList.jsp", type = "dispatcher"),
		@Result(name = "reload", location = "expressageNote!list.action", type = "redirect"),
		@Result(name = "status", type = "json", params = { "root","validateInfo" }), })

public class ExpressageNoteAction  extends SimpleActionSupport {

	private static final long serialVersionUID = 1L;

	private String noteId;
	private ExpressageNote expressageNote;
	
	@Resource
	private ExpressageNoteService expressageNoteService;
	
	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	

	public ExpressageNote getExpressageNote() {
		return expressageNote;
	}

	public void setExpressageNote(ExpressageNote expressageNote) {
		this.expressageNote = expressageNote;
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

	@Override
	protected IBaseService getManager() {
		return expressageNoteService;
	}

	@Override
	protected Object createNewInstance() {
		return expressageNote;
	}

	@Override
	public Object getModel() {
		return getExpressageNote();
	}

	@Override
	public void setModel(Object obj) {
		setExpressageNote((ExpressageNote)obj);
	}



}
