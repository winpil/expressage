package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageNoteDAO;
import com.cndatacom.rbac.pojo.ExpressageNote;
import com.cndatacom.rbac.system.service.ExpressageNoteService;

@Service("expressageNoteService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageNoteServiceImpl extends BaseServiceImpl<ExpressageNote, String> implements ExpressageNoteService {

	@Resource(name = "expressageNoteHibernate")
	private ExpressageNoteDAO expressageNoteDAO;

	public ExpressageNoteDAO getBaseDao() {
		return expressageNoteDAO;
	}
	
}
