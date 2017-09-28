package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageComCourierDAO;
import com.cndatacom.rbac.pojo.ExpressageComCourier;

@Repository("expressageComCourierHibernate")
public class ExpressageComCourierDAOImpl extends BaseDAOHibernateImpl<ExpressageComCourier,String> implements ExpressageComCourierDAO {


}
