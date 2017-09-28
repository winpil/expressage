package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ChuchangDAO;
import com.cndatacom.rbac.pojo.Chuchang;
import com.cndatacom.rbac.system.service.ChuchangService;

@Service("chuchangService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ChuchangServiceImpl extends BaseServiceImpl<Chuchang, String> implements ChuchangService{
	@Resource(name = "chuchangHibernate")
	private ChuchangDAO chuchangDao;

	public ChuchangDAO getBaseDao() {
		return chuchangDao;
	}

}
