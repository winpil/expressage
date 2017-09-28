package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageOrderDAO;
import com.cndatacom.rbac.pojo.ExpressageOrder;
import com.cndatacom.rbac.system.service.ExpressageOrderService;

@Service("expressageOrderService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageOrderServiceImpl extends BaseServiceImpl<ExpressageOrder, String> implements ExpressageOrderService{
	@Resource(name = "expressageOrderHibernate")
	private ExpressageOrderDAO expressageOrderDao;

	public ExpressageOrderDAO getBaseDao() {
		return expressageOrderDao;
	}


}
