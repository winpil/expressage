package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageTextDAO;
import com.cndatacom.rbac.pojo.ExpressageText;
import com.cndatacom.rbac.system.service.ExpressageTextService;

@Service("expressageTextService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageTextServiceImpl extends BaseServiceImpl<ExpressageText, String> implements ExpressageTextService{
	@Resource(name = "expressageTextHibernate")
	private ExpressageTextDAO expressageTextDao;

	public ExpressageTextDAO getBaseDao() {
		return expressageTextDao;
	}

}
