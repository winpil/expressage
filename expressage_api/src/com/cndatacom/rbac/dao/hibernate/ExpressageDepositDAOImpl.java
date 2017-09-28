package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageDepositDAO;
import com.cndatacom.rbac.pojo.ExpressageDeposit;

@Repository("expressageDepositHibernate")
public class ExpressageDepositDAOImpl extends BaseDAOHibernateImpl<ExpressageDeposit,String> implements ExpressageDepositDAO {


}
