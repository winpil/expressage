package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageFDAO;
import com.cndatacom.rbac.pojo.ExpressageF;
import com.cndatacom.rbac.system.service.ExpressageFService;

@Service("expressageFService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageFServiceImpl extends BaseServiceImpl<ExpressageF, String> implements ExpressageFService{
	@Resource(name = "expressageFHibernate")
	private ExpressageFDAO expressageFDao;

	public ExpressageFDAO getBaseDao() {
		return expressageFDao;
	}

}
