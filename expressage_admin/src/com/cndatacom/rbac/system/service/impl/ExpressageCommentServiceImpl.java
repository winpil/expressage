package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageCommentDAO;
import com.cndatacom.rbac.pojo.ExpressageComment;
import com.cndatacom.rbac.system.service.ExpressageCommentService;

@Service("expressageCommentService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageCommentServiceImpl extends BaseServiceImpl<ExpressageComment, String> implements ExpressageCommentService{
	@Resource(name = "expressageCommentHibernate")
	private ExpressageCommentDAO expressageCommentDao;

	public ExpressageCommentDAO getBaseDao() {
		return expressageCommentDao;
	}

}
