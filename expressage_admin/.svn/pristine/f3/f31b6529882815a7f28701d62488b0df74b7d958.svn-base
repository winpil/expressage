package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageMessageDAO;
import com.cndatacom.rbac.pojo.ExpressageMessage;
import com.cndatacom.rbac.system.service.ExpressageMessageService;

@Service("expressageMessageService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageMessageServiceImpl extends BaseServiceImpl<ExpressageMessage, String> implements ExpressageMessageService{
	@Resource(name = "expressageMessageHibernate")
	private ExpressageMessageDAO expressageMessageDao;

	public ExpressageMessageDAO getBaseDao() {
		return expressageMessageDao;
	}

}
