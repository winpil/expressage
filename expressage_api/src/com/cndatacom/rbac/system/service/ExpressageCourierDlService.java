package com.cndatacom.rbac.system.service;

import java.util.List;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.rbac.pojo.ExpressageCourierDl;
import com.cndatacom.rbac.pojo.ExpressagePayLog;

/**
 * 
 * 类名: ExpressageCommentService</br> 
 * 包名：com.cndatacom.rbac.system.service </br> 
 * 描述: </br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2015-7-1
 */
public interface ExpressageCourierDlService extends IBaseService<ExpressageCourierDl, String>{
	public List<ExpressageCourierDl> getEPList(String sqlStr,String pageN,String pageSize);
}
