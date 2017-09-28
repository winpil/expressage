package com.cndatacom.rbac.dao.hibernate;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageStageDAO;
import com.cndatacom.rbac.pojo.ExpressageStage;
import com.cndatacom.rbac.pojo.StageExpressage;

@Repository("expressageStageHibernate")
public class ExpressageStageDAOImpl extends BaseDAOHibernateImpl<ExpressageStage,String> implements ExpressageStageDAO {

	@Override
	public List<StageExpressage> getCourierIdsByjw(String latitude,
			String longitude) {
		
		StringBuffer sb = new StringBuffer(" select *,(2 * 6378.137 * ASIN(SQRT(POW(SIN(PI() * (? - es.latitude) / 360), 2) + COS(PI() * ? / 180) * COS(es.latitude * PI() / 180) * POW(SIN(PI() * (? - es.longitude) / 360), 2)))) as juli from expressage_stage as es order by " +
		" juli asc limit 0, 10");
		
		SQLQuery query = getSession().createSQLQuery(sb.toString());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		query.setString(0, latitude);
		query.setString(1, latitude);
		query.setString(2, longitude);
		//System.out.println(query.list());
		List<StageExpressage> ls=query.list();
		return ls;
	}


}
