package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageNoteDAO;
import com.cndatacom.rbac.dao.ExpressageNoteTempDAO;
import com.cndatacom.rbac.pojo.ExpressageNote;
import com.cndatacom.rbac.pojo.ExpressageNoteTemp;
import com.cndatacom.rbac.system.service.ExpressageNoteService;
import com.cndatacom.rbac.system.service.ExpressageNoteTempService;

@Service("expressageNoteTempService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageNoteTempServiceImpl extends BaseServiceImpl<ExpressageNoteTemp, String> implements ExpressageNoteTempService {

	@Resource(name = "expressageNoteTempHibernate")
	private ExpressageNoteTempDAO expressageNoteTempDAO;

	public ExpressageNoteTempDAO getBaseDao() {
		return expressageNoteTempDAO;
	}
	
}
