package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageIdeaDAO;
import com.cndatacom.rbac.pojo.ExpressageIdea;

@Repository("expressageIdeaHibernate")
public class ExpressageIdeaDAOImpl extends BaseDAOHibernateImpl<ExpressageIdea,String> implements ExpressageIdeaDAO {

}
