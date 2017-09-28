package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageIntegralDAO;
import com.cndatacom.rbac.pojo.ExpressageIntegral;

@Repository("expressageIntegralHibernate")
public class ExpressageIntegralDAOImpl extends BaseDAOHibernateImpl<ExpressageIntegral,String> implements ExpressageIntegralDAO {


}
