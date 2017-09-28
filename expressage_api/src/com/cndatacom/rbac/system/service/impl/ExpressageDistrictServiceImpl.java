package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageDistrictDAO;
import com.cndatacom.rbac.pojo.ExpressageDistrict;
import com.cndatacom.rbac.system.service.ExpressageDistrictService;

@Service("expressageDistrictService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageDistrictServiceImpl extends BaseServiceImpl<ExpressageDistrict, String> implements ExpressageDistrictService{
	@Resource(name = "expressageDistrictHibernate")
	private ExpressageDistrictDAO expressageDistrictDao;

	public ExpressageDistrictDAO getBaseDao() {
		return expressageDistrictDao;
	}

}
