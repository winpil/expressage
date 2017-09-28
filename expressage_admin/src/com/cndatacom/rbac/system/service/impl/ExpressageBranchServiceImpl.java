package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageBranchDAO;
import com.cndatacom.rbac.pojo.ExpressageBranch;
import com.cndatacom.rbac.system.service.ExpressageBranchService;

@Service("expressageBranchService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageBranchServiceImpl extends BaseServiceImpl<ExpressageBranch, String> implements ExpressageBranchService{
	@Resource(name = "expressageBranchHibernate")
	private ExpressageBranchDAO expressageBranchDao;

	public ExpressageBranchDAO getBaseDao() {
		return expressageBranchDao;
	}

}
