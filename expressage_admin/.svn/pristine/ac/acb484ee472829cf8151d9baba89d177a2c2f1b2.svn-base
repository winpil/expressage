package com.cndatacom.rbac.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageStageDAO;
import com.cndatacom.rbac.pojo.ExpressageStage;
import com.cndatacom.rbac.pojo.StageExpressage;
import com.cndatacom.rbac.system.service.ExpressageStageService;

@Service("expressageStageService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageStageServiceImpl extends BaseServiceImpl<ExpressageStage, String> implements ExpressageStageService{
	@Resource(name = "expressageStageHibernate")
	private ExpressageStageDAO expressageStageDao;

	public ExpressageStageDAO getBaseDao() {
		return expressageStageDao;
	}

	@Override
	public List<StageExpressage> getCourierIdsByjw(String latitude,
			String longitude) {
		return expressageStageDao.getCourierIdsByjw(latitude, longitude);
	}

}
