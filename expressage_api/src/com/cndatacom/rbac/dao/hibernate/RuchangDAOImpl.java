package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.RuchangDAO;
import com.cndatacom.rbac.pojo.Ruchang;

@Repository("ruchangHibernate")
public class RuchangDAOImpl extends BaseDAOHibernateImpl<Ruchang,String> implements RuchangDAO {


}
