package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageUserDAO;
import com.cndatacom.rbac.pojo.ExpressageUser;

@Repository("expressageUserHibernate")
public class ExpressageUserDAOImpl extends BaseDAOHibernateImpl<ExpressageUser,String> implements ExpressageUserDAO {


}
