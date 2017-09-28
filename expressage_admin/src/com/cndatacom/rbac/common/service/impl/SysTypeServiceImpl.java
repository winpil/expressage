package com.cndatacom.rbac.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cndatacom.common.orm.IBaseDAO;
import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.common.service.ISysTypeService;
import com.cndatacom.rbac.dao.ISysTypeDAO;
import com.cndatacom.rbac.pojo.SysType;

/**
 * 系统类型存储表Service层接口对应的实现类
 * @author:yab
 * 2010-06-17 9:36
 */
@Service("sysTypeService")
public class SysTypeServiceImpl extends BaseServiceImpl<SysType, String> implements ISysTypeService{
	
	@Resource(name="sysTypeHibernate")
	private ISysTypeDAO sysTypeDao;
	
	public IBaseDAO<SysType, String> getBaseDao() {
		return sysTypeDao;
	}

	public List<SysType> findSysTypeBySysTypeCategoryId(String typeCategoryid) {
		return sysTypeDao.find("from SysType where typeCategory.typeCategoryid = ? order by typeId",typeCategoryid);
	}

	public SysType findSysTypeByTypeCodeAndTypeCategoryId(String typeCategoryid, String typeCode) {
		return sysTypeDao.getSysTypeByTypeCodeAndTypeCategoryId(typeCategoryid, typeCode);
	}
}
