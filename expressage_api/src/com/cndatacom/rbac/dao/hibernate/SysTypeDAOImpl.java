package com.cndatacom.rbac.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ISysTypeDAO;
import com.cndatacom.rbac.pojo.SysType;

/**
 * 系统类型存储表DAO层接口对应实现类
 * @author:yab
 * 2010-06-17 9:27
 */
@Repository("sysTypeHibernate")
public class SysTypeDAOImpl extends BaseDAOHibernateImpl<SysType,String> implements ISysTypeDAO{

	@SuppressWarnings("unchecked")
	public SysType getSysTypeByTypeCodeAndTypeCategoryId(String typeCategoryid, String typeCode) {
		
		List<SysType> allResults = this.getSession()
						  .createQuery("from SysType where typeCategory.typeCategoryid = ? and typeCode = ?")
						  .setParameter(0,typeCategoryid)
						  .setParameter(1,typeCode)
						  .list();
		
		if(allResults.size() > 0){
			return allResults.get(0);
		}
		
		return null;
	}

}
