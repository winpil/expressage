package com.cndatacom.rbac.system.service;

import java.util.List;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.rbac.pojo.SysBase;

/**
 * 
 * 类名: ISysBaseService</br> 
 * 包名：com.cndatacom.rbac.system.service </br> 
 * 描述: 通用接口模板service层</br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2015-6-29
 */
public interface ISysBaseService extends IBaseService<SysBase, String>{
	
	public List getBaseApiList(SysBase sysBase,String paramenter1,String paramenter2,String paramenter3,String paramenter4,String paramenter5,String paramenter6,String pages,String pageSize);
}
