package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageLogisticsDAO;
import com.cndatacom.rbac.pojo.ExpressageLogistics;

@Repository("expressageLogisticsHibernate")
public class ExpressageLogisticsDAOImpl extends BaseDAOHibernateImpl<ExpressageLogistics,String> implements ExpressageLogisticsDAO {


}
