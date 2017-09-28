package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageFavorableDAO;
import com.cndatacom.rbac.pojo.ExpressageFavorable;

@Repository("expressageFavorableHibernate")
public class ExpressageFavorableDAOImpl extends BaseDAOHibernateImpl<ExpressageFavorable,String> implements ExpressageFavorableDAO {


}
