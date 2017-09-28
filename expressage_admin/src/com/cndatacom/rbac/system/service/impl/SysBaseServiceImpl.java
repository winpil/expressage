package com.cndatacom.rbac.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ISysBaseDAO;
import com.cndatacom.rbac.pojo.SysBase;
import com.cndatacom.rbac.system.service.ISysBaseService;

@Service("sysBaseService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class SysBaseServiceImpl extends BaseServiceImpl<SysBase, String> implements ISysBaseService{
	@Resource(name = "sysBaseHibernate")
	private ISysBaseDAO modelBaseDao;

	public ISysBaseDAO getBaseDao() {
		return modelBaseDao;
	}

	@Override
	public List getBaseApiList(SysBase sysBase,String paramenter1,String paramenter2,String paramenter3,String paramenter4,String paramenter5,String paramenter6,String paramenter7) {
		return modelBaseDao.getBaseApiList(sysBase,paramenter1,paramenter2,paramenter3,paramenter4,paramenter5,paramenter6,paramenter7);
	}
}
