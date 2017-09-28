package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageCourierDAO;
import com.cndatacom.rbac.pojo.ExpressageCourier;

@Repository("expressageCourierHibernate")
public class ExpressageCourierDAOImpl extends BaseDAOHibernateImpl<ExpressageCourier,String> implements ExpressageCourierDAO {


}
