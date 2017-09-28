package com.cndatacom.rbac.system.web.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.common.web.action.SimpleActionSupport;
import com.cndatacom.common.weixin.inter.YideliMenu;

/** 
 * 类名: YideliWeixinCreatedMenuAction</br>
 * 包名：com.cndatacom.portal.web.action </br>
 * 描述: 翼得利微信公众号菜单创建</br>
 * 发布版本号：v1.0</br>
 * 开发人员： 陆培波</br>
 * 创建时间： Mar 14, 2014
 */ 

@Controller
@Action("weixinCreatedMenu")
@Scope("prototype")
@Namespace("/rbac/sys")
public class YideliWeixinCreatedMenuAction extends SimpleActionSupport{
	
	/** 
	 * 方法名: execute</br>
	 * 详述: 接口入口方法</br>
	 * 开发人员：陆培波</br>
	 * 创建时间：Mar 14, 2014</br>
	 */ 
	public void execute1() {
		try {
			new YideliMenu().createMenu();
		} catch (Exception e) {
		}
	}
	
	public void getMenu() {
		System.out.println(new YideliMenu().getMenu().toString());
	}

	@Override
	protected Object createNewInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IBaseService getManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModel(Object obj) {
		// TODO Auto-generated method stub
		
	}
}
