package com.cndatacom.rbac.system.service.impl;
// Generated 2012-3-17 11:30:51 by Hibernate Tools 3.2.1.GA


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.exception.ServiceException;
import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ISysCityDAO;
import com.cndatacom.rbac.pojo.SysCity;
import com.cndatacom.rbac.system.service.ISysCityService;


/**
 *
 *  2012-3-17 11:30:51 
 */

@Service("sysCityService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class SysCityServiceImpl extends BaseServiceImpl< SysCity,String> implements
		ISysCityService {

	@Resource(name="sysCityHibernate")
	private ISysCityDAO sysCityDao;

	public ISysCityDAO getBaseDao() {
		return sysCityDao;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<HashMap<String, Object>> getSysCityByParentId(String parentId) {
		List<SysCity> results = sysCityDao.find("from SysCity where parent.id = ? order by sort asc,id asc",parentId);
		List<HashMap<String, Object>> subCities = new ArrayList<HashMap<String, Object>>();
		for (SysCity sysCity : results) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", sysCity.getId());
			map.put("text", sysCity.getCityName());
			map.put("leaf", sysCity.isLeaf());
			subCities.add(map);
		}
		return subCities;
	}

	public synchronized void saveSysCity(SysCity sysCity) {
		try {
			long size = sysCityDao.getChildrenSize(sysCity.getParent().getId());

			if (null == sysCity.getId()) {
				sysCity.setSort(size + 1);
			}

			sysCityDao.save(sysCity);

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}
	
	public Long getChildrenSize(String parentId) throws Exception { 
		 List<Long> list = sysCityDao.createQuery("select count(*) as total from SysCity where parent.id = ?", parentId).list();
		 return list.get(0);
	}
}
