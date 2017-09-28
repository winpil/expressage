package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageVersionDAO;
import com.cndatacom.rbac.pojo.ExpressageVersion;

@Repository("expressageVersionHibernate")
public class ExpressageVersionDAOImpl extends BaseDAOHibernateImpl<ExpressageVersion,String> implements ExpressageVersionDAO {


}
