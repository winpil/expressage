package com.cndatacom.rbac.system.service.impl;

import java.util.List;
import java.util.Map;

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

	@Override
	public List<Map<String,String>> getCourierIdsByjw(String latitude,String longitude) {
		return expressageCourierDao.getCourierIdsByjw(latitude,longitude);
	}

	@Override
	public List<Map<String,Object>> getCourierIdsByjw1(String latitude,
			String longitude) {
		return expressageCourierDao.getCourierIdsByjw1(latitude,longitude);
	}

}
