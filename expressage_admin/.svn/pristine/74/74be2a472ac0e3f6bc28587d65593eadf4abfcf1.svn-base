package com.cndatacom.rbac.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ISysBaseDAO;
import com.cndatacom.rbac.pojo.SysBase;

@Repository("sysBaseHibernate")
public class SysBaseDAOImpl extends BaseDAOHibernateImpl<SysBase,String> implements ISysBaseDAO {

	@Override
	public List getBaseApiList(SysBase sysBase,String paramenter1,String paramenter2,String paramenter3,String paramenter4,String paramenter5,String paramenter6,String paramenter7) {
		SQLQuery query = getSession().createSQLQuery(sysBase.getSqlName());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if(StringUtils.isNotBlank(sysBase.getParamenter1())){
			query.setString(0, paramenter1);
		}
		if(StringUtils.isNotBlank(sysBase.getParamenter2())){
			query.setString(1, paramenter2);
		}
		if(StringUtils.isNotBlank(sysBase.getParamenter3())){
			query.setString(2, paramenter3);
		}
		if(StringUtils.isNotBlank(sysBase.getParamenter4())){
			query.setString(3, paramenter4);
		}
		if(StringUtils.isNotBlank(sysBase.getParamenter5())){
			query.setString(4, paramenter5);
		}
		if(StringUtils.isNotBlank(sysBase.getPages())&&StringUtils.isNotBlank(sysBase.getPageSize())){
			query.setFirstResult(Integer.parseInt(paramenter6));
			query.setMaxResults(Integer.parseInt(paramenter7));
		}
		
		
//		query.setFirstResult(sqlName.getPageStart());      
//		query.setMaxResults(sqlName.getPageLimit());  
		List list=query.list();
		return list;
	}

}
