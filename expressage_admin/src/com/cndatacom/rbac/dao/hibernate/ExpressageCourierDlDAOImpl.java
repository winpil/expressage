package com.cndatacom.rbac.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageCourierDlDAO;
import com.cndatacom.rbac.pojo.ExpressageCourierDl;
import com.cndatacom.rbac.pojo.ExpressagePayLog;

@Repository("expressageCourierDlHibernate")
public class ExpressageCourierDlDAOImpl extends BaseDAOHibernateImpl<ExpressageCourierDl,String> implements ExpressageCourierDlDAO {

	@Override
	public List<ExpressageCourierDl> getEPList(String sqlStr, String pageN,
			String pageSize) {
		
		Integer ps = Integer.parseInt(pageN);
		Integer pn = Integer.parseInt(pageSize);
		
		
		Query query = getSession().createQuery(sqlStr);
		query.setFirstResult((ps-1)*pn);
		query.setMaxResults(pn*ps);
		return (List<ExpressageCourierDl>) query.list();
	}
}
