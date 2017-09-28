package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageCourierDAO;
import com.cndatacom.rbac.pojo.ExpressageCourier;
import com.cndatacom.rbac.system.service.ExpressageCourierService;

@Service("expressageCourierService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageCourierServiceImpl extends BaseServiceImpl<ExpressageCourier, String> implements ExpressageCourierService{
	@Resource(name = "expressageCourierHibernate")
	private ExpressageCourierDAO expressageCourierDao;

	public ExpressageCourierDAO getBaseDao() {
		return expressageCourierDao;
	}

}
