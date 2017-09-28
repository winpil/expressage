package com.cndatacom.rbac.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ISysRoleDAO;
import com.cndatacom.rbac.pojo.SysRole;

/**
 *	ϵͳ��ɫDAO��ʵ����
 *  @author yab
 */
@Repository("sysRoleHibernate")
public class SysRoleDAOImpl extends BaseDAOHibernateImpl<SysRole, String> implements
		ISysRoleDAO {
}
