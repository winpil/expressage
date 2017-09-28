package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageLogisticsDAO;
import com.cndatacom.rbac.pojo.ExpressageLogistics;
import com.cndatacom.rbac.system.service.ExpressageLogisticsService;

@Service("expressageLogisticsService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageLogisticsServiceImpl extends BaseServiceImpl<ExpressageLogistics, String> implements ExpressageLogisticsService{
	@Resource(name = "expressageLogisticsHibernate")
	private ExpressageLogisticsDAO expressageLogisticsDao;

	public ExpressageLogisticsDAO getBaseDao() {
		return expressageLogisticsDao;
	}

}
