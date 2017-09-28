
package com.cndatacom.rbac.dao;
// Generated 2012-3-17 11:30:51 by Hibernate Tools 3.2.1.GA

import  com.cndatacom.rbac.pojo.*;
import com.cndatacom.common.orm.*;

/**
 *
 * @author 
 */
public interface ISysCityDAO extends IBaseDAO<SysCity, String> {
	
	/**
	 * 取得子节点的个数
	 * @param parentId 父节点id
	 * @return 返回子节点的个数
	 */
	public Long getChildrenSize(String parentId);

}


