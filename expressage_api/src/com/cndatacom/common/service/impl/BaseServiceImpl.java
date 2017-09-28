package com.cndatacom.common.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.cndatacom.common.exception.ServiceException;
import com.cndatacom.common.orm.IBaseDAO;
import com.cndatacom.common.orm.Page;
import com.cndatacom.common.orm.PropertyFilter;
import com.cndatacom.common.orm.StatusModel;
import com.cndatacom.common.orm.PropertyFilter.MatchType;
import com.cndatacom.common.service.IBaseService;

/**
 * BaseServiceʵ����
 * @author yab
 * 2010.11.17 �������쳣�Ĳ�׽
 */
@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
public abstract class BaseServiceImpl<T, PK extends Serializable> implements IBaseService<T, PK> {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public abstract IBaseDAO<T, PK>  getBaseDao();
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Page<T> getAll(final Page<T> page){
		try{
			return getBaseDao().getAll(page);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("��ҳ��ѯ����ʧ�ܣ�");
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Page<T> findPage(final Page<T> page, final String hql,
			final Object... values){
		try{
		return getBaseDao().findPage(page, hql, values);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("��������ҳ��ѯ����ʧ�ܣ�");
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Page<T> findPage(final Page<T> page, final String hql,
			final Map<String, ?> values){
		try{
			return getBaseDao().findPage(page, hql, values);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("��������ҳ��ѯ����ʧ�ܣ�");
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Page<T> findPage(final Page<T> page, final Criterion... criterions){
		try{
			return getBaseDao().findPage(page, criterions);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("��������ҳ��ѯ����ʧ�ܣ�");
		}
	}
	
	public Page<T> findPageOrderBy(Page<T> page, Order[] orders, List<PropertyFilter> filters) {
		try{
			return getBaseDao().findPageOrderBy(page, orders, filters);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("��������ҳ��ѯ����ʧ�ܣ�");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<T> findBy(final String propertyName, final Object value,
			final MatchType matchType){
		try{
			return getBaseDao().findBy(propertyName, value, matchType);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("�����Բ�ѯ����ʧ�ܣ�");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<T> find(List<PropertyFilter> filters){
		try{
			return getBaseDao().find(filters);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("��Filter��ѯ��ѯ����ʧ�ܣ�");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Page<T> findPage(final Page<T> page,
			final List<PropertyFilter> filters){
		try{
			return getBaseDao().findPage(page, filters);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("��Filter��ѯ��ҳ����ʧ�ܣ�");
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public boolean isPropertyUnique(final String propertyName,
			final Object newValue, final Object oldValue){
		try{
			return getBaseDao().isPropertyUnique(propertyName, newValue, oldValue);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("���������ƣ�ֵ��ѯ����ʧ�ܣ�");
		}
	}
	
	public void save(final T entity){
		try{
			getBaseDao().save(entity);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("��������ʧ�ܣ�");
		}
	}

	public void delete(final T entity){
		try{
			getBaseDao().delete(entity);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("��ʵ��ɾ������ʧ�ܣ�");
		}
	}

	public void delete(final PK id){
		try{
			getBaseDao().delete(id);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("������ɾ������ʧ�ܣ�");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public T get(final PK id){
		try{
			return getBaseDao().get(id);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("��������ѯ����ʧ�ܣ�");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<T> getAll(){
		try{
			return getBaseDao().getAll();
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("��ѯȫ������ʧ�ܣ�");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<T> getAll(String orderBy, boolean isAsc){
		try{
			return getBaseDao().getAll(orderBy, isAsc);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("ͨ�������ֶβ�ѯȫ������ʧ�ܣ�");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<T> findBy(final String propertyName, final Object value){
		try{
			return getBaseDao().findBy(propertyName, value);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("ͨ��ָ���ֶβ�ѯȫ������ʧ�ܣ�");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public T findUniqueBy(final String propertyName, final Object value){
		try{
			return getBaseDao().findUniqueBy(propertyName, value);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("ͨ��ָ���ֶβ�ѯΨһ��¼ʧ�ܣ�");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<T> findByIds(List<PK> ids){
		try{
			return getBaseDao().findByIds(ids);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("ͨ����ѯlist���ϼ�¼ʧ�ܣ�");
		}
	}
	
	
//	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
//	public List<T> findByIds(List<PK> ids){
//		List<T> list = null;
//		try{
//			Assert.notNull(ids,"���ҵļ��ϲ���Ϊ�գ�");
//			for(PK id : ids){
//				System.out.println("sfdjaksljflks:"+id);
//				list.add(getBaseDao().get(id));
//			}
//			return list;
//		}catch(Exception e){
//			e.printStackTrace();
//			logger.error(e.toString());
//			throw new ServiceException("ͨ����ѯlist���ϼ�¼ʧ�ܣ�");
//		}
//	}
	

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public <X> List<X> find(final String hql, final Object... values){
		try{
			return getBaseDao().find(hql, values);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("ͨ����ѯHQL����ѯ��¼ʧ�ܣ�");
		}
	}
	
//	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
//	public <X> List<X> find(final String hql,Integer limit, final Object... values){
//		try{
//			return getBaseDao().find(hql, limit, values);
//		}catch(Exception e){
//			logger.error(e.toString());
//			throw new ServiceException("ͨ����ѯHQL����ѯ��¼ʧ�ܣ�");
//		}
//	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public <X> List<X> find(final String hql, final Map<String, ?> values){
		try{
			return getBaseDao().find(hql, values);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("ͨ����ѯHQL����ѯ��¼ʧ�ܣ�");
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public <X> X findUnique(final String hql, final Object... values){
		try{
			return getBaseDao().findUnique(hql,values);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("ͨ����ѯHQL����ѯΨһ��¼ʧ�ܣ�");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public <X> X findUnique(final String hql, final Map<String, ?> values){
		try{
			return getBaseDao().findUnique(hql, values);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("ͨ����ѯHQL����ѯΨһ��¼ʧ�ܣ�");
		}
	}
	
	public int batchExecute(final String hql, final Object... values){
		try{
			return getBaseDao().batchExecute(hql, values);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("ִ��HQL������ʧ�ܣ�");
		}
	}

	public int batchExecute(final String hql, final Map<String, ?> values){
		try{
			return getBaseDao().batchExecute(hql, values);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("ִ��HQL������ʧ�ܣ�");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<T> find(final Criterion... criterions){
		try{
			return getBaseDao().find(criterions);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("ͨ��������ѯ���ݲ���ʧ�ܣ�");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public T findUnique(final Criterion... criterions){
		try{
			return getBaseDao().findUnique(criterions);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("ͨ��������ѯΨһ��¼����ʧ�ܣ�");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Criteria createCriteria(final Criterion... criterions){
		try{
			return getBaseDao().createCriteria(criterions);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("����Criteria����ʧ�ܣ�");
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public String getIdName(){
		try{
			return getBaseDao().getIdName();
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("��ѯ�������Ʋ���ʧ�ܣ�");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public T getAndInitEntity(final PK id){
		try{
			return getBaseDao().getAndInitEntity(id);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("��id��ѯʵ�����ʧ�ܣ�");
		}
	}
	
	public void deleteByIds(List<PK> ids){
		try{
			Assert.notNull(ids,"ɾ���ļ��ϲ���Ϊ�գ�");
			for(PK id : ids){
				getBaseDao().delete(id);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			throw new ServiceException("����ɾ��ʵ�����ʧ�ܣ�");
		}
	}
	
	public void updateStatusByIds(List<PK> ids, Long statu){
		try{
			Assert.notNull(ids,"���µļ��ϲ���Ϊ�գ�");
			for(PK id : ids){
				T entity=getBaseDao().getAndInitEntity(id);
				StatusModel statusModel=(StatusModel)entity;
				if(null!=statusModel){
					statusModel.setStatus(statu);
					getBaseDao().save(entity);
				}else{
					throw new Exception();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			throw new ServiceException("��������ʵ��״̬ʧ�ܣ�");
		}
	}

}
