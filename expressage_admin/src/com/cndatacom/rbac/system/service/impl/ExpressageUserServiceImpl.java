package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageUserDAO;
import com.cndatacom.rbac.pojo.ExpressageUser;
import com.cndatacom.rbac.system.service.ExpressageUserService;

@Service("expressageUserService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageUserServiceImpl extends BaseServiceImpl<ExpressageUser, String> implements ExpressageUserService{
	@Resource(name = "expressageUserHibernate")
	private ExpressageUserDAO expressageUserDao;

	public ExpressageUserDAO getBaseDao() {
		return expressageUserDao;
	}

}
