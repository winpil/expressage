package com.cndatacom.rbac.dao;

import java.util.List;

import com.cndatacom.common.orm.IBaseDAO;
import com.cndatacom.rbac.pojo.ExpressageStage;
import com.cndatacom.rbac.pojo.StageExpressage;

/**
 * 
 * 类名: ExpressageBankDAO</br> 
 * 包名：com.cndatacom.rbac.dao </br> 
 * 描述: 银行卡Dao层</br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2015-7-1
 */
public interface ExpressageStageDAO extends IBaseDAO<ExpressageStage,String>{
	
	/**
	 * 
	 * 方法名: getCourierIdsByjw</br>
	 * 详述: 查询附近驿站</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2015-8-20</br>
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public List<StageExpressage> getCourierIdsByjw(String latitude,String longitude);
}
