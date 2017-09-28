package com.cndatacom.rbac.dao;

import java.util.List;

import com.cndatacom.common.orm.IBaseDAO;
import com.cndatacom.rbac.pojo.SysBase;

/**
 * 
 * 类名: ISysBaseDAO</br> 
 * 包名：com.cndatacom.rbac.dao </br> 
 * 描述: 通用接口模板dao层</br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2015-6-29
 */
public interface ISysBaseDAO extends IBaseDAO<SysBase,String>{
	public List getBaseApiList(SysBase sysBase,String paramenter1,String paramenter2,String paramenter3,String paramenter4,String paramenter5,String paramenter6,String pages,String pageSize);
}
