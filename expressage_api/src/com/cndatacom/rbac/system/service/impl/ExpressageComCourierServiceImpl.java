package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageComCourierDAO;
import com.cndatacom.rbac.pojo.ExpressageComCourier;
import com.cndatacom.rbac.system.service.ExpressageComCourierService;

@Service("expressageComCourierService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageComCourierServiceImpl extends BaseServiceImpl<ExpressageComCourier, String> implements ExpressageComCourierService{
	@Resource(name = "expressageComCourierHibernate")
	private ExpressageComCourierDAO expressageComCourierDao;

	public ExpressageComCourierDAO getBaseDao() {
		return expressageComCourierDao;
	}

}
