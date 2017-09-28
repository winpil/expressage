package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ISysAuthorityDAO;
import com.cndatacom.rbac.pojo.SysAuthority;

/**
 *	ϵͳȨ��DAO��ʵ����
 *  @author yab
 */
@Repository("sysAuthorityHibernate")
public class SysAuthorityDAOImpl extends BaseDAOHibernateImpl<SysAuthority,String> implements
		ISysAuthorityDAO {
}
