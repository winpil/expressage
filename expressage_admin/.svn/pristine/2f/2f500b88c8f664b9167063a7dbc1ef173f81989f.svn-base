package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageFavorableDAO;
import com.cndatacom.rbac.pojo.ExpressageFavorable;
import com.cndatacom.rbac.system.service.ExpressageFavorableService;

@Service("expressageFavorableService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageFavorableServiceImpl extends BaseServiceImpl<ExpressageFavorable, String> implements ExpressageFavorableService{
	@Resource(name = "expressageFavorableHibernate")
	private ExpressageFavorableDAO expressageFavorableDao;

	public ExpressageFavorableDAO getBaseDao() {
		return expressageFavorableDao;
	}

}
