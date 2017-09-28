package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageFDAO;
import com.cndatacom.rbac.pojo.ExpressageF;

@Repository("expressageFHibernate")
public class ExpressageFDAOImpl extends BaseDAOHibernateImpl<ExpressageF,String> implements ExpressageFDAO {


}
