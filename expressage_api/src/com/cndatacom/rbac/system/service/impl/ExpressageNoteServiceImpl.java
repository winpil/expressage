package com.cndatacom.rbac.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageNoteDAO;
import com.cndatacom.rbac.pojo.ExpressageNote;
import com.cndatacom.rbac.pojo.NoteExpressage;
import com.cndatacom.rbac.system.service.ExpressageNoteService;

@Service("expressageNoteService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageNoteServiceImpl extends BaseServiceImpl<ExpressageNote, String> implements ExpressageNoteService {

	@Resource(name = "expressageNoteHibernate")
	private ExpressageNoteDAO expressageNoteDAO;

	public ExpressageNoteDAO getBaseDao() {
		return expressageNoteDAO;
	}

	/*
	 * 根据快递员ID查询TA的短信记录
	 */
	@Override
	public List<ExpressageNote> getMessagesRecorder(String courierId,String startDate,String endDate){
		return expressageNoteDAO.getMessagesList(courierId,startDate,endDate);
	}

}
