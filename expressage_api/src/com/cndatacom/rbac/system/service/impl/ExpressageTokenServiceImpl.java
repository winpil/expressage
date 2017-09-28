package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageTokenDAO;
import com.cndatacom.rbac.pojo.ExpressageToken;
import com.cndatacom.rbac.system.service.ExpressageTokenService;

@Service("expressageTokenService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageTokenServiceImpl extends BaseServiceImpl<ExpressageToken, String> implements ExpressageTokenService{
	@Resource(name = "expressageTokenHibernate")
	private ExpressageTokenDAO expressageTokenDao;

	public ExpressageTokenDAO getBaseDao() {
		return expressageTokenDao;
	}

}
