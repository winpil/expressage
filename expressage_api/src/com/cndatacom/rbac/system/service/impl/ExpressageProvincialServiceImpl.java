package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageProvincialDAO;
import com.cndatacom.rbac.pojo.ExpressageProvincial;
import com.cndatacom.rbac.system.service.ExpressageProvincialService;

@Service("expressageProvincialService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageProvincialServiceImpl extends BaseServiceImpl<ExpressageProvincial, String> implements ExpressageProvincialService{
	@Resource(name = "expressageProvincialHibernate")
	private ExpressageProvincialDAO expressageProvincialDao;

	public ExpressageProvincialDAO getBaseDao() {
		return expressageProvincialDao;
	}

}
