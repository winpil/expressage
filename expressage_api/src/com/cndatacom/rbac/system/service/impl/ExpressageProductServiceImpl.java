package com.cndatacom.rbac.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ExpressageProductDAO;
import com.cndatacom.rbac.pojo.ExpressageProduct;
import com.cndatacom.rbac.system.service.ExpressageProductService;

@Service("expressageProductService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpressageProductServiceImpl extends BaseServiceImpl<ExpressageProduct, String> implements ExpressageProductService{
	@Resource(name = "expressageProductHibernate")
	private ExpressageProductDAO expressageProductDao;

	public ExpressageProductDAO getBaseDao() {
		return expressageProductDao;
	}

}
