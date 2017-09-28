package com.cndatacom.rbac.system.service;

import java.util.List;
import java.util.Map;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.rbac.pojo.ExpressageCourier;

/**
 * 
 * 类名: ExpressageCourierService</br> 
 * 包名：com.cndatacom.rbac.system.service </br> 
 * 描述: </br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2015-7-1
 */
public interface ExpressageCourierService extends IBaseService<ExpressageCourier, String>{
	
	/**
	 * 
	 * 方法名: getCourierIdsByjw</br>
	 * 详述: 根据经纬度查询附近快递员</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2015-8-13</br>
	 * @return
	 */
	public List<Map<String,String>> getCourierIdsByjw(String latitude,String longitude);
	
	public List<Map<String,Object>> getCourierIdsByjw1(String latitude,String longitude);
}
