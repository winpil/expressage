package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.PpDetailDAO;
import com.cndatacom.rbac.pojo.PpDetail;
import com.cndatacom.rbac.system.service.PpDetailService;

@Service("ppDetailService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class PpDetailServiceImpl extends BaseServiceImpl<PpDetail, String> implements PpDetailService{
	@Resource(name = "ppDetailHibernate")
	private PpDetailDAO ppDetailDao;

	public PpDetailDAO getBaseDao() {
		return ppDetailDao;
	}

}
