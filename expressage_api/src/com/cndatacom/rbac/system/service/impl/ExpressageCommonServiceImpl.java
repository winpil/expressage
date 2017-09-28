package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageCommonDAO;
import com.cndatacom.rbac.pojo.ExpressageCommon;
import com.cndatacom.rbac.system.service.ExpressageCommonService;

@Service("expressageCommonService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageCommonServiceImpl extends BaseServiceImpl<ExpressageCommon, String> implements ExpressageCommonService{
	@Resource(name = "expressageCommonHibernate")
	private ExpressageCommonDAO expressageCommonDao;

	public ExpressageCommonDAO getBaseDao() {
		return expressageCommonDao;
	}

}
