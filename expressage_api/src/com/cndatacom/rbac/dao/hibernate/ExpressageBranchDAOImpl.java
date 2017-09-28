package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageBranchDAO;
import com.cndatacom.rbac.pojo.ExpressageBranch;

@Repository("expressageBranchHibernate")
public class ExpressageBranchDAOImpl extends BaseDAOHibernateImpl<ExpressageBranch,String> implements ExpressageBranchDAO {


}
