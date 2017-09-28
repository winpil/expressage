package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.RuchangDAO;
import com.cndatacom.rbac.pojo.Ruchang;
import com.cndatacom.rbac.system.service.RuchangService;

@Service("ruchangService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class RuchangServiceImpl extends BaseServiceImpl<Ruchang, String> implements RuchangService{
	@Resource(name = "ruchangHibernate")
	private RuchangDAO ruchangDao;

	public RuchangDAO getBaseDao() {
		return ruchangDao;
	}

}
