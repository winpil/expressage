package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageLogDAO;
import com.cndatacom.rbac.pojo.ExpressageLog;
import com.cndatacom.rbac.system.service.ExpressageLogService;

@Service("expressageLogService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageLogServiceImpl extends BaseServiceImpl<ExpressageLog, String> implements ExpressageLogService{
	@Resource(name = "expressageLogHibernate")
	private ExpressageLogDAO expressageLogDao;

	public ExpressageLogDAO getBaseDao() {
		return expressageLogDao;
	}

}
