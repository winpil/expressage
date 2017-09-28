package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageCommentDAO;
import com.cndatacom.rbac.pojo.ExpressageComment;

@Repository("expressageCommentHibernate")
public class ExpressageCommentDAOImpl extends BaseDAOHibernateImpl<ExpressageComment,String> implements ExpressageCommentDAO {


}
