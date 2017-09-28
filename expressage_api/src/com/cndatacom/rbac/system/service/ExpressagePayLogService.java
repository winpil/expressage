package com.cndatacom.rbac.system.service;

import java.util.List;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.rbac.pojo.ExpressagePayLog;

/**
 * 
 * 类名: ExpressageCityService</br> 
 * 包名：com.cndatacom.rbac.system.service </br> 
 * 描述: </br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2015-7-1
 */
public interface ExpressagePayLogService extends IBaseService<ExpressagePayLog, String>{
	
	/**
	 * 
	 * 方法名: getEPList</br>
	 * 详述:查询快递员支付记录 </br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2015-7-31</br>
	 * @param courierId
	 * @param pageN
	 * @param pageSize
	 * @return
	 */
	public List<ExpressagePayLog> getEPList(String courierId,String pageN,String pageSize);
}
