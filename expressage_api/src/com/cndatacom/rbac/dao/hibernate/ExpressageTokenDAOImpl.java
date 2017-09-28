package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageTokenDAO;
import com.cndatacom.rbac.pojo.ExpressageToken;

@Repository("expressageTokenHibernate")
public class ExpressageTokenDAOImpl extends BaseDAOHibernateImpl<ExpressageToken,String> implements ExpressageTokenDAO {


}
