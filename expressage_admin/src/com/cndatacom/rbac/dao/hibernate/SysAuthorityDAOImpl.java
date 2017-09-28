package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ISysAuthorityDAO;
import com.cndatacom.rbac.pojo.SysAuthority;

/**
 *	系统权限DAO层实现类
 *  @author yab
 */
@Repository("sysAuthorityHibernate")
public class SysAuthorityDAOImpl extends BaseDAOHibernateImpl<SysAuthority,String> implements
		ISysAuthorityDAO {
}
