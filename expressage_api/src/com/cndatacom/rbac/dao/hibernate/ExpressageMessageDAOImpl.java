package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageMessageDAO;
import com.cndatacom.rbac.pojo.ExpressageMessage;

@Repository("expressageMessageHibernate")
public class ExpressageMessageDAOImpl extends BaseDAOHibernateImpl<ExpressageMessage,String> implements ExpressageMessageDAO {


}
