package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageRecordDAO;
import com.cndatacom.rbac.pojo.ExpressageRecord;

@Repository("expressageRecordHibernate")
public class ExpressageRecordDAOImpl extends BaseDAOHibernateImpl<ExpressageRecord,String> implements ExpressageRecordDAO {


}
