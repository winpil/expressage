package com.cndatacom.rbac.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageNoteDAO;
import com.cndatacom.rbac.pojo.ExpressageNote;
import com.cndatacom.rbac.pojo.NoteExpressage;

@Repository("expressageNoteHibernate")
public class ExpressageNoteDAOImpl extends BaseDAOHibernateImpl<ExpressageNote, String> implements ExpressageNoteDAO {
	
	@Override
	public List<ExpressageNote> getMessagesList(String courierId,String startDate,String endDate){

		StringBuffer sb = new StringBuffer(" from ExpressageNote where courierId = '"+courierId+"' and noteTime >= '"
				+startDate+"' and noteTime <= '"+endDate+"' order by noteTime desc ");

		Query query = getSession().createQuery(sb.toString());

		return (List<ExpressageNote>) query.list();
	}
			
}
