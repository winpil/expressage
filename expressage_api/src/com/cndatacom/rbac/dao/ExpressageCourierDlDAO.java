package com.cndatacom.rbac.dao;

import java.util.List;

import com.cndatacom.common.orm.IBaseDAO;
import com.cndatacom.rbac.pojo.ExpressageCourierDl;

/**
 * 
 * 类名: ExpressageComCourierDAO</br> 
 * 包名：com.cndatacom.rbac.dao </br> 
 * 描述: 常用快递员daoceng</br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2015-7-1
 */
public interface ExpressageCourierDlDAO extends IBaseDAO<ExpressageCourierDl,String>{
	public List<ExpressageCourierDl> getEPList(String sqlStr,String pageN,String pageSize);
}
