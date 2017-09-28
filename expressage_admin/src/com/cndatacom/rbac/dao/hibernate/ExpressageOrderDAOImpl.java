package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageOrderDAO;
import com.cndatacom.rbac.pojo.ExpressageOrder;

@Repository("expressageOrderHibernate")
public class ExpressageOrderDAOImpl extends BaseDAOHibernateImpl<ExpressageOrder,String> implements ExpressageOrderDAO {


}
