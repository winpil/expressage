package com.cndatacom.rbac.dao;

import java.util.List;
import java.util.Map;

import com.cndatacom.common.orm.IBaseDAO;
import com.cndatacom.rbac.pojo.ExpressageCourier;

/**
 * 
 * 类名: ExpressageCourierDAO</br> 
 * 包名：com.cndatacom.rbac.dao </br> 
 * 描述: 快递员dao层</br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2015-7-1
 */
public interface ExpressageCourierDAO extends IBaseDAO<ExpressageCourier,String>{
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
