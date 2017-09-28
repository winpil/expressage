package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageMinutesDAO;
import com.cndatacom.rbac.pojo.ExpressageMinutes;
import com.cndatacom.rbac.system.service.ExpressageMinutesService;

@Service("xpressageMinutesService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageMinutesServiceImpl extends BaseServiceImpl<ExpressageMinutes, String> implements ExpressageMinutesService{
	@Resource(name = "expressageMinutesHibernate")
	private ExpressageMinutesDAO expressageMinutesDao;

	public ExpressageMinutesDAO getBaseDao() {
		return expressageMinutesDao;
	}

}
