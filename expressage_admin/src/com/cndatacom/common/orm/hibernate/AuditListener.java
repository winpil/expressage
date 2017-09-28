package com.cndatacom.common.orm.hibernate;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.event.SaveOrUpdateEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cndatacom.common.orm.hibernate.pojo.AuditableEntity;

/**
 * ���Զ�Ϊentity��������Ϣ��Hibernate EventListener.
 * 
 * ��hibernateִ��saveOrUpdate()ʱ,�Զ�ΪAuditableEntity��������������Ϣ.
 * 
 * @author calvin
 */
@SuppressWarnings("serial")
public class AuditListener implements SaveOrUpdateEventListener {

	private static Logger logger = LoggerFactory.getLogger(AuditListener.class);

	public void onSaveOrUpdate(SaveOrUpdateEvent event) throws HibernateException {
		Object object = event.getObject();
		
		//���������AuditableEntity����,��������Ϣ.
		if (object instanceof AuditableEntity) {
			AuditableEntity entity = (AuditableEntity) object;
				//�����¶���
			if(entity.getCreatedDate()==null){
				entity.setCreatedDate(new Date());
				logger.info("{}����  {} ����", new Object[] { object.getClass().getName(), new Date() });
			}else {
					//�޸ľɶ���
					entity.setModifiedDate(new Date());
					logger.info("{}����  {} �޸�", new Object[] { object.getClass().getName(), new Date() });
				}
			}
			
		}
	}
