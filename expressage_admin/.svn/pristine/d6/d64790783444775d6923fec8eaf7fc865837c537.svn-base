package com.cndatacom.rbac.dao.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ISysUserDAO;
import com.cndatacom.rbac.pojo.SysRole;
import com.cndatacom.rbac.pojo.SysUser;

/**
 * 系统用户 DAO层接口对应的实现类
 * 
 * @author yab
 */
@Repository("tsysUserHibernate")
public class SysUserDAOImpl extends BaseDAOHibernateImpl<SysUser, String>
		implements ISysUserDAO {
	public List<SysUser> findSysUserBySpInfoId(Long spinfId) {
		String hql = "select distinct u from SysGroup g join g.spInfo s join g.users u where s.id = ?";
		return this.find(hql, spinfId);
	}

	public void grantSysUserRoles(SysUser sysUser, String[] roleIds) {
		Set<SysRole> sets = new HashSet<SysRole>();
		if (roleIds != null && roleIds.length > 0) {
			for (int i = 0; i < roleIds.length; i++) {
				SysRole sysRole = new SysRole();
				sysRole.setRoleId(roleIds[i]);
				sets.add(sysRole);
			}
		}
		sysUser.setRoles(sets);
		this.save(sysUser);
	}

	public List<SysUser> chkUsername(String username) {
		String hql = "from SecuritySysUser where username = ?";
		return this.find(hql, username);

	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getAllUserEmail() {
		String hql = "select su.name, su.email from SecuritySysUser su where su.email is not null";
		Query query = getSession().createQuery(hql);
		return (List<Object[]>) query.list();
	}

	public List<SysUser> getByEmail(String email) {
		List<SysUser> list = findBy("email", email);
		return list;
	}

}
