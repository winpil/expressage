package com.cndatacom.rbac.dao.hibernate;
// Generated 2012-3-17 11:30:51 by Hibernate Tools 3.2.1.GA

import  com.cndatacom.rbac.dao.*;
import  com.cndatacom.rbac.pojo.*;
import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import org.springframework.stereotype.Repository;


/**
 *
 *  2012-3-17 11:30:51 
 */
@Repository("sysCityHibernate")
public class SysCityDAOImpl extends BaseDAOHibernateImpl<SysCity, String> implements ISysCityDAO{

	public Long getChildrenSize(String parentId) {
		return ((Number) this.getSession().createQuery(
				"select count(*) from SysCity where parent.id = ?")
				.setParameter(0, parentId).iterate().next()).longValue();
	}
}


