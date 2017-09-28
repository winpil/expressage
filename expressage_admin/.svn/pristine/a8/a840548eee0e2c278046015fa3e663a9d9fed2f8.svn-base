package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageOrderLogDAO;
import com.cndatacom.rbac.pojo.ExpressageOrderLog;
import com.cndatacom.rbac.system.service.ExpressageOrderLogService;

@Service("expressageOrderLogService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageOrderLogServiceImpl extends BaseServiceImpl<ExpressageOrderLog, String> implements ExpressageOrderLogService{
	@Resource(name = "expressageOrderLogHibernate")
	private ExpressageOrderLogDAO expressageOrderLogDao;

	public ExpressageOrderLogDAO getBaseDao() {
		return expressageOrderLogDao;
	}

}
