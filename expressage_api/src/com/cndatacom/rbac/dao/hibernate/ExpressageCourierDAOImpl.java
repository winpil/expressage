package com.cndatacom.rbac.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.cndatacom.common.orm.hibernate.BaseDAOHibernateImpl;
import com.cndatacom.rbac.dao.ExpressageCourierDAO;
import com.cndatacom.rbac.pojo.ExpressageCourier;

@Repository("expressageCourierHibernate")
public class ExpressageCourierDAOImpl extends BaseDAOHibernateImpl<ExpressageCourier,String> implements ExpressageCourierDAO {

	@Override
	public List<Map<String,String>> getCourierIdsByjw(String latitude,String longitude) {
		StringBuffer sb = new StringBuffer(" select (select token_name from expressage_token where type = '2' and user_id = ec.courier_id ) as tokenName from expressage_courier as ec where is_work = '1' " +
		" and (2 * 6378.137 * ASIN(SQRT(POW(SIN(PI() * (? - ec.latitude) / 360), 2) + COS(PI() * ? / 180) * COS(ec.latitude * PI() / 180) * POW(SIN(PI() * (? - ec.longitude) / 360), 2))))<1.5 order by " +
		"(2 * 6378.137 * ASIN(SQRT(POW(SIN(PI() * (? - ec.latitude) / 360), 2) + COS(PI() * ? / 180) * COS(ec.latitude * PI() / 180) * POW(SIN(PI() * (? - ec.longitude) / 360), 2)))) asc ");
		
		SQLQuery query = getSession().createSQLQuery(sb.toString());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		query.setString(0, latitude);
		query.setString(1, latitude);
		query.setString(2, longitude);
		query.setString(3, latitude);
		query.setString(4, latitude);
		query.setString(5, longitude);
		//System.out.println(query.list());
		List<Map<String,String>> ls=query.list();
		return ls;
	}

	@Override
	public List<Map<String, Object>> getCourierIdsByjw1(String latitude,
			String longitude) {
		StringBuffer sb = new StringBuffer(" select *,(2 * 6378.137 * ASIN(SQRT(POW(SIN(PI() * (? - ec.latitude) / 360), 2) + COS(PI() * ? / 180) * COS(ec.latitude * PI() / 180) * POW(SIN(PI() * (? - ec.longitude) / 360), 2)))) as juli from expressage_courier as ec where ec.is_work = '1' " +
				" and (2 * 6378.137 * ASIN(SQRT(POW(SIN(PI() * (? - ec.latitude) / 360), 2) + COS(PI() * ? / 180) * COS(ec.latitude * PI() / 180) * POW(SIN(PI() * (? - ec.longitude) / 360), 2))))<1.5" +
				" and to_days(ec.ll_date) = to_days(now()) order by " +
				"(2 * 6378.137 * ASIN(SQRT(POW(SIN(PI() * (? - ec.latitude) / 360), 2) + COS(PI() * ? / 180) * COS(ec.latitude * PI() / 180) * POW(SIN(PI() * (? - ec.longitude) / 360), 2)))) asc ");
				
				SQLQuery query = getSession().createSQLQuery(sb.toString());
				query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				
				query.setString(0, latitude);
				query.setString(1, latitude);
				query.setString(2, longitude);
				query.setString(3, latitude);
				query.setString(4, latitude);
				query.setString(5, longitude);
				query.setString(6, latitude);
				query.setString(7, latitude);
				query.setString(8, longitude);
				//System.out.println(query.list());
				List<Map<String, Object>> ls=query.list();
				return ls;
	}


}
