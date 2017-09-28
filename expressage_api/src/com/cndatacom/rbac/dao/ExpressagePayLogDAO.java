package com.cndatacom.rbac.dao;

import java.util.List;

import com.cndatacom.common.orm.IBaseDAO;
import com.cndatacom.rbac.pojo.ExpressagePayLog;

/**
 * 
 * 类名: ExpressageCityDAO</br> 
 * 包名：com.cndatacom.rbac.dao </br> 
 * 描述: 支付日志dao层</br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2015-7-1
 */
public interface ExpressagePayLogDAO extends IBaseDAO<ExpressagePayLog,String>{
	
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
