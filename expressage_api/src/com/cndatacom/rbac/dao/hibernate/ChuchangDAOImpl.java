package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ChuchangDAO;
import com.cndatacom.rbac.pojo.Chuchang;

@Repository("chuchangHibernate")
public class ChuchangDAOImpl extends BaseDAOHibernateImpl<Chuchang,String> implements ChuchangDAO {


}
