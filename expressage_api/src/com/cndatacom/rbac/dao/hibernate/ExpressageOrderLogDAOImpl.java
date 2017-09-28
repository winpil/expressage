package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageOrderLogDAO;
import com.cndatacom.rbac.pojo.ExpressageOrderLog;

@Repository("expressageOrderLogHibernate")
public class ExpressageOrderLogDAOImpl extends BaseDAOHibernateImpl<ExpressageOrderLog,String> implements ExpressageOrderLogDAO {


}
