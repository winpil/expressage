package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageCompanyDAO;
import com.cndatacom.rbac.pojo.ExpressageCompany;
import com.cndatacom.rbac.system.service.ExpressageCompanyService;

@Service("expressageCompanyService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageCompanyServiceImpl extends BaseServiceImpl<ExpressageCompany, String> implements ExpressageCompanyService{
	@Resource(name = "expressageCompanyHibernate")
	private ExpressageCompanyDAO expressageCompanyDao;

	public ExpressageCompanyDAO getBaseDao() {
		return expressageCompanyDao;
	}

}
