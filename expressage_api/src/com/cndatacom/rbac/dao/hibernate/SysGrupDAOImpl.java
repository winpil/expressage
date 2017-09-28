package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ISysGroupDAO;
import com.cndatacom.rbac.pojo.SysGroup;

/**
 * 组织架构DAO层实现类
 * @author yab
 *
 */
@Repository("sysGrupHibernate")
public class SysGrupDAOImpl extends BaseDAOHibernateImpl<SysGroup, String> implements
		ISysGroupDAO {
	public Long getChildrenSize(String parentId) {
		return ((Number) this.getSession().createQuery(
				"select count(*) from SysGroup where parent.id = ?")
				.setParameter(0, parentId).iterate().next()).longValue();
	}

}
