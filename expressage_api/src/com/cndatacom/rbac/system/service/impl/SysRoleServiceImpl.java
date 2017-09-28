package com.cndatacom.rbac.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.exception.ServiceException;
import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.rbac.dao.ISysRoleDAO;
import com.cndatacom.rbac.pojo.SysAuthority;
import com.cndatacom.rbac.pojo.SysRole;
import com.cndatacom.rbac.system.service.ISysRoleService;

/**
 * 系统角色Servcie接口对应的实现类
 * @author yab
 *
 */
@Service("sysRoleService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole,String> implements
		ISysRoleService {
	
	@Resource(name="sysRoleHibernate")
	private ISysRoleDAO sysRoleDao;
	
	//@Resource(name="tsysUserHibernate")
	//private ISysUserDAO sysUserDao;
	
	public ISysRoleDAO getBaseDao() {
		return sysRoleDao;
	}

	public void deleteByIds(String ids) {
		try{
			StringTokenizer st = new StringTokenizer(ids,",");
			
			while(st.hasMoreTokens()){				
				
				String id=st.nextToken();
				SysRole role = sysRoleDao.getAndInitEntity(id);
				
				if("1".equals(id)){
					throw new ServiceException("角色【" + role.getRoleName() + "】不能被删除！");
				}

				sysRoleDao.delete(role);
			}
			
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			logger.error(e.getMessage());
			
			throw new ServiceException("删除角色失败，请联系管理员！");
		}
	}
	
	@Deprecated
	public void sysRoleAuthorities(SysRole sysRole, String[] authirityIds) {
		try {
			
//			if(sysRole.getRoleId().equals("1")){
//				throw new ServiceException("角色【" + sysRole.getRoleName() + "】不能改变权限！");
//			}
			
			Set<SysAuthority> sets = new HashSet<SysAuthority>();
			
			if(authirityIds != null && authirityIds.length > 0){
				
				for(int i = 0; i < authirityIds.length; i++){
					SysAuthority sysAuthority = new SysAuthority();

					sysAuthority.setAuthorityId(authirityIds[i]);

					sets.add(sysAuthority);
				}
				
			}
			sysRole.setAuthorities(sets);
			
			sysRoleDao.save(sysRole);
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED,readOnly=true)
	public List<HashMap<String, Object>> getAllRole(String roleId) {
		
		List<HashMap<String, Object>> roles = new ArrayList<HashMap<String, Object>>();
		
		List<SysRole> results = sysRoleDao.getAll();
		
		for(SysRole role : results){
			HashMap<String, Object> mapRole = new HashMap<String, Object>();
			
			mapRole.put("id", role.getRoleId());
			mapRole.put("text",role.getRoleName());
			mapRole.put("leaf",true);
			
			roles.add(mapRole);
		}
		
		return roles;
	}
	
	@Transactional(propagation = Propagation.REQUIRED,readOnly=true)
	public List<HashMap<String, Object>> getSysMenusByRoleId(String roleId) {
		
		/*List<HashMap<String, Object>> menus = new ArrayList<HashMap<String, Object>>();
		
		SysRole role = sysRoleDao.getAndInitEntity(roleId);
		
		if(null != role){
			Set<SysMenu> ownMenus = role.getMenus();
			
			for(SysMenu sysMenu : ownMenus) {
				HashMap<String, Object> mapMenu = new HashMap<String, Object>();
				
				mapMenu.put("id",sysMenu.getId());
				mapMenu.put("text",sysMenu.getMenuName());
				mapMenu.put("leaf",sysMenu.isLeaf());
				
				menus.add(mapMenu);
			}
		}
		
		return menus;*/
		return null;
	}
	
	public void editSysMenu(String roleId, String ids) {
		
		/*try{
			
			SysRole role = sysRoleDao.getAndInitEntity(roleId);
			
			Assert.notNull(role,"角色不能为空，请检查传递的参数！");
			
			if(Long.valueOf(1L).equals(role.getRoleId())){
				throw new ServiceException("角色【" + role.getRoleName() + "】不能改变其菜单！");
			}
			
			StringTokenizer st = new StringTokenizer(ids,",");
			
			Long menuId = null;
			
			SysMenu menu = null;
			
			role.getMenus().clear();
			
			while(st.hasMoreTokens()){
				
				menuId = Long.parseLong(st.nextToken());
				
				menu = sysMenuDao.getAndInitEntity(menuId);
				
				role.getMenus().add(menu);
			}
			
			sysRoleDao.save(role);
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}*/
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED,readOnly=true)
	public List<SysRole> findSysRoleByAuthorityIds(String ids){
		
		List<SysRole> results = null;
		
		try{
			
			List<String> longIds = new ArrayList<String>();
			
			//将ids用，进行分隔
			StringTokenizer st = new StringTokenizer(ids,",");
			
			while(st.hasMoreTokens()){
				longIds.add(st.nextToken());
			}
			
			String hql = "select distinct o from SysRole o join o.authorities c where c.authorityId in(:longIds)";
			
			results = (List<SysRole>)(sysRoleDao.createQuery(hql).setParameterList("longIds", longIds).list());
		}catch(Exception e){
			throw new ServiceException("通过权限ids集合查询对应的角色引用失败！");
		}
		
		return results;
	}
	
	@Transactional(propagation = Propagation.REQUIRED,readOnly=true)
	public List<SysRole> findSysRoleByMenuId(String id){
		
		List<SysRole> results = null;
		
		try{
			results = sysRoleDao.find("select distinct o from SysRole o join o.menus m where m.id = ?",id);
		}catch(Exception e){
			throw new ServiceException("通过菜单id查询对应的角色引用失败！");
		}
		
		return results;
	}

	@Transactional(propagation = Propagation.REQUIRED,readOnly=true)
	public List<SysRole> findByPropertyNameAndRoleId(String propertyName,
			Object propertyValue, String roleId) {
		List<SysRole> results = null;
		try {
			// 保存查询是否存在系统权限判断的hql语句
			StringBuilder sb = new StringBuilder();

			// 保存查询的参数
			List<Object> params = new ArrayList<Object>();

			params.add(propertyValue);

			sb.append("from SysRole where ").append(propertyName).append(" = ? ");

			if (null != roleId) {
				sb.append(" and roleId != ?");
				params.add(roleId);
			}
			
			results = sysRoleDao.find(sb.toString(),params.toArray());
			
		} catch (Exception e) {
			throw new ServiceException("通过角色属性" + propertyName +"与角色id查询角色信息失败！");
		}
		
		return results;
	}

	public void grantAuthoritiesToSysRole(SysRole sysRole, String ids) {
			try {
//				if(sysRole.getRoleId().equals("1")){
//					throw new ServiceException("角色【" + sysRole.getRoleName() + "】不能改变权限！");
//				}
				Set<SysAuthority> sets = new HashSet<SysAuthority>();
				
				StringTokenizer st = new StringTokenizer(ids,",");
				
				SysAuthority sysAuthority = null;
				
				while(st.hasMoreTokens()){
					
					String id = st.nextToken();
					
					sysAuthority = new SysAuthority();
	
					sysAuthority.setAuthorityId(id);
	
					sets.add(sysAuthority);
					
				}
			
				sysRole.setAuthorities(sets);
			
				sysRoleDao.save(sysRole);
		} catch (DataAccessException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getMessage());
		}

	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<SysRole> findSysRoleByAuthorityId(String authorityId) {
		List<SysRole> results = null;

		try {
			String hql = "select distinct o from SysRole o join o.authorities c where c.authorityId = ?";

			results = (List<SysRole>) (sysRoleDao.createQuery(hql)
					.setParameter(0, authorityId).list());
		} catch (Exception e) {
			throw new ServiceException("通过权限ids集合查询对应的角色引用失败！");
		}

		return results;
	}

}
