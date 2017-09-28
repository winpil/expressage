package com.cndatacom.common.orm.hibernate;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.event.SaveOrUpdateEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cndatacom.common.orm.hibernate.pojo.AuditableEntity;

/**
 * 在自动为entity添加审计信息的Hibernate EventListener.
 * 
 * 在hibernate执行saveOrUpdate()时,自动为AuditableEntity的子类添加审计信息.
 * 
 * @author calvin
 */
@SuppressWarnings("serial")
public class AuditListener implements SaveOrUpdateEventListener {

	private static Logger logger = LoggerFactory.getLogger(AuditListener.class);

	public void onSaveOrUpdate(SaveOrUpdateEvent event) throws HibernateException {
		Object object = event.getObject();
		
		//如果对象是AuditableEntity子类,添加审计信息.
		if (object instanceof AuditableEntity) {
			AuditableEntity entity = (AuditableEntity) object;
				//创建新对象
			if(entity.getCreatedDate()==null){
				entity.setCreatedDate(new Date());
				logger.info("{}对象  {} 创建", new Object[] { object.getClass().getName(), new Date() });
			}else {
					//修改旧对象
					entity.setModifiedDate(new Date());
					logger.info("{}对象  {} 修改", new Object[] { object.getClass().getName(), new Date() });
				}
			}
			
		}
	}
