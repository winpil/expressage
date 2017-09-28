package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageIdeaDAO;
import com.cndatacom.rbac.pojo.ExpressageIdea;
import com.cndatacom.rbac.system.service.ExpressageIdeaService;

@Service("expressageIdeaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageIdeaServiceImpl extends BaseServiceImpl<ExpressageIdea, String> implements ExpressageIdeaService{
	@Resource(name = "expressageIdeaHibernate")
	private ExpressageIdeaDAO expressageIdeaDao;

	public ExpressageIdeaDAO getBaseDao() {
		return expressageIdeaDao;
	}

}
