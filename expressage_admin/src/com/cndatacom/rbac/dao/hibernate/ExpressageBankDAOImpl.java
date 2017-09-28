package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageBankDAO;
import com.cndatacom.rbac.pojo.ExpressageBank;

@Repository("expressageBankHibernate")
public class ExpressageBankDAOImpl extends BaseDAOHibernateImpl<ExpressageBank,String> implements ExpressageBankDAO {


}
