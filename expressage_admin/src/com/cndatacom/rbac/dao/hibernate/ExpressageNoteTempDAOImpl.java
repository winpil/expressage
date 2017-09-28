package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageNoteDAO;
import com.cndatacom.rbac.dao.ExpressageNoteTempDAO;
import com.cndatacom.rbac.pojo.ExpressageNote;
import com.cndatacom.rbac.pojo.ExpressageNoteTemp;

@Repository("expressageNoteTempHibernate")
public class ExpressageNoteTempDAOImpl extends BaseDAOHibernateImpl<ExpressageNoteTemp, String> implements ExpressageNoteTempDAO {

			
}
