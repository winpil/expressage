package com.cndatacom.rbac.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.exception.ServiceException;
import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ISysAuthorityDAO;
import com.cndatacom.rbac.pojo.SysAuthority;
import com.cndatacom.rbac.system.service.ISysAuthorityService;

/**
 * 权限Servcie接口对应的实现类
 * 
 * @author yab
 * 
 */
@Service("sysAuthorityService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class SysAuthorityServiceImpl extends
		BaseServiceImpl<SysAuthority, String> implements ISysAuthorityService {

	@Resource(name = "sysAuthorityHibernate")
	private ISysAuthorityDAO sysAuthorityDao;

	public ISysAuthorityDAO getBaseDao() {
		return sysAuthorityDao;
	}
	
	public void deleteByIds(String ids) {
		try {
			StringTokenizer st = new StringTokenizer(ids, ",");

			while (st.hasMoreTokens()) {
				sysAuthorityDao.delete(st.nextToken());
			}

		} catch (Exception e) {
			logger.error(e.getMessage());

			throw new ServiceException("批量删除权限失败，请联系管理员！");
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED,readOnly=true)
	public List<SysAuthority> findByPropertyNameAndAuthorityId(String propertyName,Object propertyValue,
			String authorityId) {
		List<SysAuthority> results = null;
		try {
			// 保存查询是否存在系统权限判断的hql语句
			StringBuilder sb = new StringBuilder();
			// 保存查询的参数
			List<Object> params = new ArrayList<Object>();
			params.add(propertyValue);
			sb.append("from SysAuthority where ").append(propertyName).append(" = ? ");
			if (null != authorityId) {
				sb.append(" and authorityId != ?");
				params.add(authorityId);
			}
			results = sysAuthorityDao.find(sb.toString(),params.toArray());
		} catch (Exception e) {
			throw new ServiceException("通过权限属性" + propertyName +"与权限id查询权限信息失败！");
		}
		return results;
	}

	public List<SysAuthority> findByIds(String ids) {
		
		String hql = "select distinct o from SysAuthority join SysRole fetch SysRole.authorities o";
		
		return sysAuthorityDao.find(hql);
	}

	public List<SysAuthority> findOwnSysAuthorityByMenuId(String menuId) {
		List<SysAuthority> results = null;

		try {
			String hql = "select distinct a from SysMenu m join m.authorities a where m.id = ?";
			results = sysAuthorityDao.find(hql, menuId);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServiceException("查询菜单对应的权限失败！错误消息为：" + e.getMessage());
		}

		return results;
	}

}
