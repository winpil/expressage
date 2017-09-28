package com.cndatacom.common.orm.hibernate;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cndatacom.common.exception.ServiceException;
import com.cndatacom.common.utils.Constants;
import com.cndatacom.common.utils.ElementType;
import com.cndatacom.common.utils.ReflectionUtils;
import com.cndatacom.common.utils.Status;
import com.cndatacom.common.utils.StatusIndicate;
import com.cndatacom.common.utils.SysFileUtils;

@Component("iptvHibernateInterceptor")
public class IptvHibernateInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 1L;

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
			
			try {
				if(entity instanceof StatusIndicate){
					for(int i=0;i<propertyNames.length;i++){
					if (null != getAnno(entity, propertyNames[i]) &&  getAnno(entity, propertyNames[i]).equals(ElementType.STATUS)){// ״̬�ж�
							if(types[i].getReturnedClass()== Long.class){
								state[i] = Constants.UNAUDITED_VALUE;
								ReflectionUtils.setFieldValue(entity, propertyNames[i],Constants.UNAUDITED_VALUE);
							}else{
								throw new ServiceException("״̬λ��ֵ���ͱ���ΪLong��");
							}
							
						}else if(null!=getAnno(entity, propertyNames[i]) && (getAnno(entity, propertyNames[i]).equals(ElementType.CREATEDATE) || getAnno(entity, propertyNames[i]).equals(ElementType.MODIFDATE))){
							state[i]=new Date();
							types[i]=Hibernate.TIMESTAMP;
							ReflectionUtils.setFieldValue(entity, propertyNames[i], new Date()); 
						};
					}
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}	
		
		return super.onSave(entity, id, state, propertyNames, types);
	}


	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		try {
			if(entity instanceof StatusIndicate){
				for(int i=0; i<propertyNames.length; i++){

					if(null!=getAnno(entity, propertyNames[i]) && getAnno(entity, propertyNames[i]).equals(ElementType.STATUS)){// �����״̬�ж�[name:1,passworrd=1][name=2,passworrd=1]
						if(!((Long)currentState[i]).equals((Long)previousState[i])){
							String info = isValidateStatus(currentState[i],previousState[i],types[i]);
							if(null != info){
								throw new ServiceException(info);
							}
						}else{
							currentState[i] = Constants.UNAUDITED_VALUE;
							ReflectionUtils.setFieldValue(entity, propertyNames[i], Constants.UNAUDITED_VALUE);
						}
					}else if(null!=getAnno(entity, propertyNames[i]) &&  getAnno(entity, propertyNames[i]).equals(ElementType.MODIFDATE)){
						currentState[i]=new Date();
						types[i]=Hibernate.TIMESTAMP;
						ReflectionUtils.setFieldValue(entity, propertyNames[i], new Date());
					}else if(null!=getAnno(entity, propertyNames[i]) &&  getAnno(entity, propertyNames[i]).equals(ElementType.FILE)){
//						if(null!=previousState[i] && !(currentState[i]).equals(previousState[i])){//�޸�ͼƬɾ��
//							String pathString= Struts2Utils.getServletContext().getRealPath("/") + previousState[i].toString();
//							File file=new File(pathString);
//							if(file.exists()){
//								file.delete();
//							}
//						}
						if(null!=previousState[i] && !(currentState[i]).equals(previousState[i])){//�޸�ͼƬɾ��
							try {
								SysFileUtils.deleteFile(previousState[i].toString());
							} catch(Exception e) {
								logger.error(e.toString());
							}
						}
					};
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		
		return super.onFlushDirty(entity, id, currentState, previousState,
				propertyNames, types);
	}
	
	@Override
	public void onDelete(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		try {
			if(entity instanceof StatusIndicate){
				for(int i=0;i<propertyNames.length;i++){
					if(null!=getAnno(entity, propertyNames[i]) &&  getAnno(entity, propertyNames[i]).equals(ElementType.FILE)){
//						String pathString= Struts2Utils.getServletContext().getRealPath("/") + propertyNames[i].toString();
//						File file=new File(pathString);
//						if(file.exists()){
//							file.delete();
//						}
						try {
							String contentPath = (String)ReflectionUtils.getFieldValue(entity, propertyNames[i]);
							SysFileUtils.deleteFile(contentPath);
						}catch(Exception e) {
							logger.error("��̨��ɾ����" + e.toString());
						}
					}
					}
				}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		
		super.onDelete(entity, id, state, propertyNames, types);
	}
	
	public ElementType getAnno(Object ob,String propertyName) throws SecurityException, NoSuchFieldException{
			Field field= ReflectionUtils.getDeclaredField(ob,propertyName);
		    if(field.isAnnotationPresent(Status.class)){
		    	Status status= (Status)field.getAnnotation(Status.class);
		    	return status.value();
		    };
		    return null;
		    
	}
	
	private String isValidateStatus(Object currentState,Object previousState,Type types){
		
		if(types.getReturnedClass() == Long.class){
		
			Long prevState = (Long) previousState;
			
			Long curState = (Long) currentState;
			
			if(Constants.STOP_VALUE.equals(curState)){
				if(!Constants.ENABLE_VALUE.equals(prevState)){
					return "ֻ�ܶ�����״̬�ļ�¼���в��������ڲ�������״̬�ļ�¼��";
				}
			}else if(Constants.ENABLE_VALUE.equals(curState)){
				if(!Constants.STOP_VALUE.equals(prevState) && !Constants.UNAUDITED_VALUE.equals(prevState)){
					return "ֻ�ܶԴ���ˣ�����״̬�ļ�¼���в��������ڲ��Ǵ����״̬�����״̬�ļ�¼��";
				}
			}else if(Constants.NOTPASS_VALUE.equals(curState)){
				if(!Constants.UNAUDITED_VALUE.equals(prevState)){
					return "ֻ�ܶԴ����״̬�ļ�¼���в��������ڲ��Ǵ���˵ļ�¼��";
				}
			}else if(Constants.PASS_VALUE.equals(curState)){
				if(!Constants.UNAUDITED_VALUE.equals(prevState)){
					return "ֻ�ܶ�δ���״̬�ļ�¼���в��������ڲ���δ��˵ļ�¼��";
				}
			}else {
				return null;
			}
				
		}else {
			return "״̬λ��ֵ���ͱ���ΪLong��";
		}
		
		return null;
	}


	
	
	
	
	
	

}
