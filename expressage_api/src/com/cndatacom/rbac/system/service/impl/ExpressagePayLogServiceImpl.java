package com.cndatacom.rbac.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressagePayLogDAO;
import com.cndatacom.rbac.pojo.ExpressagePayLog;
import com.cndatacom.rbac.system.service.ExpressagePayLogService;

@Service("expressagePayLogService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressagePayLogServiceImpl extends BaseServiceImpl<ExpressagePayLog, String> implements ExpressagePayLogService{
	@Resource(name = "expressagePayLogHibernate")
	private ExpressagePayLogDAO expressagePayLogDao;

	public ExpressagePayLogDAO getBaseDao() {
		return expressagePayLogDao;
	}

	@Override
	public List<ExpressagePayLog> getEPList(String courierId, String pageN,
			String pageSize) {
		return expressagePayLogDao.getEPList(courierId, pageN, pageSize);
	}

}
