package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageDistrictDAO;
import com.cndatacom.rbac.pojo.ExpressageDistrict;


@Repository("expressageDistrictHibernate")
public class ExpressageDistrictDAOImpl extends BaseDAOHibernateImpl<ExpressageDistrict,String> implements ExpressageDistrictDAO {

}
