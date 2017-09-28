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
 * BaseService实现类
 * @author yab
 * 2010.11.17 加入了异常的捕捉
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
			throw new ServiceException("分页查询数据失败！");
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Page<T> findPage(final Page<T> page, final String hql,
			final Object... values){
		try{
		return getBaseDao().findPage(page, hql, values);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("按条件分页查询数据失败！");
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Page<T> findPage(final Page<T> page, final String hql,
			final Map<String, ?> values){
		try{
			return getBaseDao().findPage(page, hql, values);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("按条件分页查询数据失败！");
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Page<T> findPage(final Page<T> page, final Criterion... criterions){
		try{
			return getBaseDao().findPage(page, criterions);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("按条件分页查询数据失败！");
		}
	}
	
	public Page<T> findPageOrderBy(Page<T> page, Order[] orders, List<PropertyFilter> filters) {
		try{
			return getBaseDao().findPageOrderBy(page, orders, filters);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("按条件分页查询数据失败！");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<T> findBy(final String propertyName, final Object value,
			final MatchType matchType){
		try{
			return getBaseDao().findBy(propertyName, value, matchType);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("按属性查询数据失败！");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<T> find(List<PropertyFilter> filters){
		try{
			return getBaseDao().find(filters);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("按Filter查询查询数据失败！");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Page<T> findPage(final Page<T> page,
			final List<PropertyFilter> filters){
		try{
			return getBaseDao().findPage(page, filters);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("按Filter查询分页数据失败！");
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public boolean isPropertyUnique(final String propertyName,
			final Object newValue, final Object oldValue){
		try{
			return getBaseDao().isPropertyUnique(propertyName, newValue, oldValue);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("按属性名称，值查询数据失败！");
		}
	}
	
	public void save(final T entity){
		try{
			getBaseDao().save(entity);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("保存数据失败！");
		}
	}

	public void delete(final T entity){
		try{
			getBaseDao().delete(entity);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("按实体删除数据失败！");
		}
	}

	public void delete(final PK id){
		try{
			getBaseDao().delete(id);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("按主键删除数据失败！");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public T get(final PK id){
		try{
			return getBaseDao().get(id);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("按主键查询数据失败！");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<T> getAll(){
		try{
			return getBaseDao().getAll();
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("查询全部数据失败！");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<T> getAll(String orderBy, boolean isAsc){
		try{
			return getBaseDao().getAll(orderBy, isAsc);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("通过排序字段查询全部数据失败！");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<T> findBy(final String propertyName, final Object value){
		try{
			return getBaseDao().findBy(propertyName, value);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("通过指定字段查询全部数据失败！");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public T findUniqueBy(final String propertyName, final Object value){
		try{
			return getBaseDao().findUniqueBy(propertyName, value);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("通过指定字段查询唯一记录失败！");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<T> findByIds(List<PK> ids){
		try{
			return getBaseDao().findByIds(ids);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("通过查询list集合记录失败！");
		}
	}
	
	
//	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
//	public List<T> findByIds(List<PK> ids){
//		List<T> list = null;
//		try{
//			Assert.notNull(ids,"查找的集合不能为空！");
//			for(PK id : ids){
//				System.out.println("sfdjaksljflks:"+id);
//				list.add(getBaseDao().get(id));
//			}
//			return list;
//		}catch(Exception e){
//			e.printStackTrace();
//			logger.error(e.toString());
//			throw new ServiceException("通过查询list集合记录失败！");
//		}
//	}
	

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public <X> List<X> find(final String hql, final Object... values){
		try{
			return getBaseDao().find(hql, values);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("通过查询HQL语句查询记录失败！");
		}
	}
	
//	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
//	public <X> List<X> find(final String hql,Integer limit, final Object... values){
//		try{
//			return getBaseDao().find(hql, limit, values);
//		}catch(Exception e){
//			logger.error(e.toString());
//			throw new ServiceException("通过查询HQL语句查询记录失败！");
//		}
//	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public <X> List<X> find(final String hql, final Map<String, ?> values){
		try{
			return getBaseDao().find(hql, values);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("通过查询HQL语句查询记录失败！");
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public <X> X findUnique(final String hql, final Object... values){
		try{
			return getBaseDao().findUnique(hql,values);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("通过查询HQL语句查询唯一记录失败！");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public <X> X findUnique(final String hql, final Map<String, ?> values){
		try{
			return getBaseDao().findUnique(hql, values);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("通过查询HQL语句查询唯一记录失败！");
		}
	}
	
	public int batchExecute(final String hql, final Object... values){
		try{
			return getBaseDao().batchExecute(hql, values);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("执行HQL语句操作失败！");
		}
	}

	public int batchExecute(final String hql, final Map<String, ?> values){
		try{
			return getBaseDao().batchExecute(hql, values);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("执行HQL语句操作失败！");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<T> find(final Criterion... criterions){
		try{
			return getBaseDao().find(criterions);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("通过条件查询数据操作失败！");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public T findUnique(final Criterion... criterions){
		try{
			return getBaseDao().findUnique(criterions);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("通过条件查询唯一记录操作失败！");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Criteria createCriteria(final Criterion... criterions){
		try{
			return getBaseDao().createCriteria(criterions);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("创建Criteria操作失败！");
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public String getIdName(){
		try{
			return getBaseDao().getIdName();
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("查询主键名称操作失败！");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public T getAndInitEntity(final PK id){
		try{
			return getBaseDao().getAndInitEntity(id);
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("按id查询实体操作失败！");
		}
	}
	
	public void deleteByIds(List<PK> ids){
		try{
			Assert.notNull(ids,"删除的集合不能为空！");
			for(PK id : ids){
				getBaseDao().delete(id);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			throw new ServiceException("批量删除实体操作失败！");
		}
	}
	
	public void updateStatusByIds(List<PK> ids, Long statu){
		try{
			Assert.notNull(ids,"更新的集合不能为空！");
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
			throw new ServiceException("批量更新实体状态失败！");
		}
	}

}
