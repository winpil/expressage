package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageRecordDAO;
import com.cndatacom.rbac.pojo.ExpressageRecord;
import com.cndatacom.rbac.system.service.ExpressageRecordService;

@Service("expressageRecordService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageRecordServiceImpl extends BaseServiceImpl<ExpressageRecord, String> implements ExpressageRecordService{
	@Resource(name = "expressageRecordHibernate")
	private ExpressageRecordDAO expressageRecordDao;

	public ExpressageRecordDAO getBaseDao() {
		return expressageRecordDao;
	}

}
