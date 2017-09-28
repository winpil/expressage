package com.cndatacom.rbac.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressagePayLogDAO;
import com.cndatacom.rbac.pojo.ExpressagePayLog;

@Repository("expressagePayLogHibernate")
public class ExpressagePayLogDAOImpl extends BaseDAOHibernateImpl<ExpressagePayLog,String> implements ExpressagePayLogDAO {

	@Override
	public List<ExpressagePayLog> getEPList(String courierId, String pageN,
			String pageSize) {
		
		Integer ps = Integer.parseInt(pageN);
		Integer pn = Integer.parseInt(pageSize);
		StringBuffer sb = new StringBuffer("  from ExpressagePayLog where status <> '0' and courierId = '"+courierId+"' order by createDate desc ");
		
		
		Query query = getSession().createQuery(sb.toString());
		query.setFirstResult((ps-1)*pn);
		query.setMaxResults(pn*ps-1);
		return (List<ExpressagePayLog>) query.list();
	}


}
