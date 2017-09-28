package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageDepositDAO;
import com.cndatacom.rbac.pojo.ExpressageDeposit;
import com.cndatacom.rbac.system.service.ExpressageDepositService;

@Service("expressageDepositService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageDepositServiceImpl extends BaseServiceImpl<ExpressageDeposit, String> implements ExpressageDepositService{
	@Resource(name = "expressageDepositHibernate")
	private ExpressageDepositDAO expressageDepositDao;

	public ExpressageDepositDAO getBaseDao() {
		return expressageDepositDao;
	}

}
