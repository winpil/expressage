package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageProductDAO;
import com.cndatacom.rbac.pojo.ExpressageProduct;

@Repository("expressageProductHibernate")
public class ExpressageProductDAOImpl extends BaseDAOHibernateImpl<ExpressageProduct,String> implements ExpressageProductDAO {


}
