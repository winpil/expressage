package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageMinutesDAO;
import com.cndatacom.rbac.pojo.ExpressageMinutes;

@Repository("expressageMinutesHibernate")
public class ExpressageMinutesDAOImpl extends BaseDAOHibernateImpl<ExpressageMinutes,String> implements ExpressageMinutesDAO {


}
