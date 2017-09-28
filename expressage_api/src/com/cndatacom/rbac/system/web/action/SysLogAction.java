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
import com.cndatacom.rbac.pojo.SysLog;
import com.cndatacom.rbac.system.service.ISysLogService;

@Controller
@Action("sysLog")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results({
    @Result(name = "list", location = "/rbac/sys/log/SysLogList.jsp", type = "dispatcher"),
    @Result(name = "input", location = "/rbac/sys/log/SysLogEdit.jsp", type = "dispatcher"),
    @Result(name = "reload", location = "sysLog!list.action", type = "redirect"),
    @Result(name = "status", type = "json", params = {"root",
        "validateInfo"})})
public class SysLogAction extends SimpleActionSupport {

    private static final long serialVersionUID = 1L;
    @Resource
    private ISysLogService sysLogService;
    private SysLog sysLog;


    @Override
    protected IBaseService getManager() {
        return sysLogService;
    }


    @Override
    protected Object createNewInstance() {
        return new SysLog();
    }

 
    @Override
    public Object getModel() {
        return getSysLog();
    }


    @Override
    public void setModel(Object obj) {
        setSysLog((SysLog) obj);
    }

    @Override
    public String delete() throws Exception {
        return super.delete();
    }


    @Override
    public String list() throws Exception {
        return super.list();
    }

 
    @Override
    public String save() throws Exception {
        return super.save();
    }
 /**
     * @return the sysLog
     */
    public SysLog getSysLog() {
        return sysLog;
    }

    /**
     * @param sysLog the sysLog to set
     */
    public void setSysLog(SysLog sysLog) {
        this.sysLog = sysLog;
    }
  
}
