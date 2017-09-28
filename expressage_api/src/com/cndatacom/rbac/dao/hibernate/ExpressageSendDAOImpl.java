package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageSendDAO;
import com.cndatacom.rbac.pojo.ExpressageSend;

@Repository("expressageSendHibernate")
public class ExpressageSendDAOImpl extends BaseDAOHibernateImpl<ExpressageSend,String> implements ExpressageSendDAO {

	

}
