package com.cndatacom.rbac.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageCityDAO;
import com.cndatacom.rbac.pojo.ExpressageCity;
import com.cndatacom.rbac.system.service.ExpressageCityService;

@Service("expressageCityService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageCityServiceImpl extends BaseServiceImpl<ExpressageCity, String> implements ExpressageCityService{
	@Resource(name = "expressageCityHibernate")
	private ExpressageCityDAO expressageCityDao;

	public ExpressageCityDAO getBaseDao() {
		return expressageCityDao;
	}
	
	
	public List<ExpressageCity> getCityByProvincialId(String provincialId){
		List<ExpressageCity> list = find("from ExpressageCity where provincialId = ?", provincialId);
		return list;
	}

}
