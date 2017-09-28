package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageSendDAO;
import com.cndatacom.rbac.pojo.ExpressageSend;
import com.cndatacom.rbac.system.service.ExpressageSendService;

@Service("expressageSendService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageSendServiceImpl extends BaseServiceImpl<ExpressageSend, String> implements ExpressageSendService{
	@Resource(name = "expressageSendHibernate")
	private ExpressageSendDAO expressageSendDao;

	public ExpressageSendDAO getBaseDao() {
		return expressageSendDao;
	}

}
