package com.cndatacom.rbac.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageCourierDlDAO;
import com.cndatacom.rbac.pojo.ExpressageCourierDl;
import com.cndatacom.rbac.system.service.ExpressageCourierDlService;

@Service("expressageCourierDlService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageCourierDlServiceImpl extends BaseServiceImpl<ExpressageCourierDl, String> implements ExpressageCourierDlService{
	@Resource(name = "expressageCourierDlHibernate")
	private ExpressageCourierDlDAO expressageCourierDlDao;

	public ExpressageCourierDlDAO getBaseDao() {
		return expressageCourierDlDao;
	}

	@Override
	public List<ExpressageCourierDl> getEPList(String sqlStr, String pageN,
			String pageSize) {
		return expressageCourierDlDao.getEPList(sqlStr, pageN, pageSize);
	}

}
