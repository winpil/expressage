package com.cndatacom.rbac.system.service.impl;
// Generated 2010-11-26 11:23:16 by Hibernate Tools 3.2.1.GA


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ISysLogDAO;
import com.cndatacom.rbac.pojo.SysLog;
import com.cndatacom.rbac.system.service.ISysLogService;


/**
 * ��־����Service��ʵ����
 * 
 * @author yab
 */

@Service("sysLogService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
public class SysLogServiceImpl extends BaseServiceImpl< SysLog,String> implements
		ISysLogService {

	@Resource(name="sysLogHibernate")
	private ISysLogDAO sysLogDao;

	public ISysLogDAO getBaseDao() {
		return sysLogDao;
	}
	
	/**
	 * ��д������־����
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
	public void save(SysLog sysLog){
		sysLogDao.save(sysLog);
	}
}
