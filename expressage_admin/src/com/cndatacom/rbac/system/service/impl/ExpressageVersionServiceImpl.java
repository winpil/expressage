package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageVersionDAO;
import com.cndatacom.rbac.pojo.ExpressageVersion;
import com.cndatacom.rbac.system.service.ExpressageVersionService;

@Service("expressageVersionService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageVersionServiceImpl extends BaseServiceImpl<ExpressageVersion, String> implements ExpressageVersionService{
	@Resource(name = "expressageVersionHibernate")
	private ExpressageVersionDAO expressageVersionDao;

	public ExpressageVersionDAO getBaseDao() {
		return expressageVersionDao;
	}

}
