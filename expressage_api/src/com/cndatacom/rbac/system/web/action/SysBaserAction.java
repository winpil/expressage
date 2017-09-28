package com.cndatacom.rbac.system.web.action;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.common.utils.BaseJsonPrint;
import com.cndatacom.common.web.action.SimpleActionSupport;
import com.cndatacom.rbac.pojo.SysBase;
import com.cndatacom.rbac.system.service.ISysBaseService;

@Controller
@Action("sysBase")
@Scope("prototype")
@Namespace("/rbac/sys")
public class SysBaserAction extends SimpleActionSupport{
	@Resource
    private ISysBaseService isysBaseService;
	private SysBase sysBase;
	
	/**
	 * 
	 * 方法名: testBase</br>
	 * 详述: 通用接口模板</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2015-6-29</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/testBase")
	public String testBase() throws Exception{
		//根据接口名获取数据库对应的接口信息
		String baseId = getRequest().getParameter("baseId");
		String paramenter1 = getRequest().getParameter("paramenter1");
		String paramenter2 = getRequest().getParameter("paramenter2");
		String paramenter3 = getRequest().getParameter("paramenter3");
		String paramenter4 = getRequest().getParameter("paramenter4");
		String paramenter5 = getRequest().getParameter("paramenter5");
		String paramenter6 = getRequest().getParameter("paramenter6");
		String pages = getRequest().getParameter("pages");
		String pageSize = getRequest().getParameter("pageSize");
		if(StringUtils.isBlank(baseId)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100001", "接口id不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		sysBase = isysBaseService.findUniqueBy("baseId", baseId);
		System.out.println("baseId========================="+baseId);
		if(sysBase==null||sysBase.getSqlName()==null){
			JSONObject json = BaseJsonPrint.SynthesisJsonArray("100002", "接口不存在", null);
			writerMessageForJson(json);
			return null;
		}
		
		//执行接口信息中的sql语句
		List sList = null;
		try {
			sList = isysBaseService.getBaseApiList(sysBase,paramenter1,paramenter2,paramenter3,paramenter4,paramenter5,paramenter6,pages,pageSize);
		} catch (Exception e) {
			JSONObject json = BaseJsonPrint.SynthesisJsonArray("100003", "sql执行异常", null);
			writerMessageForJson(json);
			e.printStackTrace();
			return null;
		}
		
		if(sysBase.getType().equals("2")||sysBase.getType().equals("3")){
			if(sList.size()>0){
				JSONObject object = JSONObject.fromObject(sList.get(0));//json化对象
				JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", object);
				writerMessageForJson(json);
			}else{
				JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
				writerMessageForJson(json);
			}
			
			
			return null;
		}
		
		
		
		JSONArray jsonArray = JSONArray.fromObject(sList);
		JSONObject json = BaseJsonPrint.SynthesisJsonArray("000000", "成功", jsonArray);
		writerMessageForJson(json);
		return null;
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
	
	
	
}
