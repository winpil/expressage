package com.cndatacom.rbac.dao;

import com.cndatacom.common.orm.IBaseDAO;
import com.cndatacom.rbac.pojo.SysGroup;

/**
 * 组织架构DAO层接口
 * @author yab
 *
 */
public interface ISysGroupDAO extends IBaseDAO<SysGroup,String> {
	
	/**
	 * 取得子节点的个数
	 * @param parentId 父节点id
	 * @return 返回子节点的个数
	 */
	public Long getChildrenSize(String parentId);
}
