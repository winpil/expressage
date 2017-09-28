package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageIntegralDAO;
import com.cndatacom.rbac.pojo.ExpressageIntegral;
import com.cndatacom.rbac.system.service.ExpressageIntegralService;

@Service("expressageIntegralService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageIntegralServiceImpl extends BaseServiceImpl<ExpressageIntegral, String> implements ExpressageIntegralService{
	@Resource(name = "expressageIntegralHibernate")
	private ExpressageIntegralDAO expressageIntegralDao;

	public ExpressageIntegralDAO getBaseDao() {
		return expressageIntegralDao;
	}

}
