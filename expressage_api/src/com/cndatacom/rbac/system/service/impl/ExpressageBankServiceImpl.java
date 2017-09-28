package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageBankDAO;
import com.cndatacom.rbac.pojo.ExpressageBank;
import com.cndatacom.rbac.system.service.ExpressageBankService;

@Service("xpressageBankService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageBankServiceImpl extends BaseServiceImpl<ExpressageBank, String> implements ExpressageBankService{
	@Resource(name = "expressageBankHibernate")
	private ExpressageBankDAO expressageBankDao;

	public ExpressageBankDAO getBaseDao() {
		return expressageBankDao;
	}

}
