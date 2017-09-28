package com.cndatacom.rbac.system.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.exception.ServiceException;
import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.common.utils.SpringSecurityContextUtils;
import com.cndatacom.rbac.dao.ISysUserDAO;
import com.cndatacom.rbac.pojo.SysRole;
import com.cndatacom.rbac.pojo.SysUser;
import com.cndatacom.rbac.system.service.ISysUserService;

/**
 * 系统用户 Service层接口对应的实现类
 * @author yab
 *
 */
@Service("tsysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser,String> implements
		ISysUserService {
	
	@Resource(name="tsysUserHibernate")
	private ISysUserDAO sysUserDao;

	public ISysUserDAO getBaseDao() {
		return sysUserDao;
	}
	
	public void deleteByIds(String ids) {
		try{
			StringTokenizer st = new StringTokenizer(ids,",");
			
			while(st.hasMoreTokens()){
				
				String id = st.nextToken();
				
				if(isSuperuser(id)){
					throw new ServiceException("存在超级系统管理员，不能删除！");
				}
				
				if(isMyself(id)){
					throw new ServiceException("存在当前登录用户，不能删除！");
				}
				
				sysUserDao.delete(id);
			}
			
		}catch(Exception e){
			logger.error(e.getMessage());
			
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void grantSysUserRoles(SysUser sysUser, String[] roleIds) {
		try {
			
			if(isSuperuser(sysUser.getUserId())){
				throw new ServiceException("用户【" + sysUser.getUsername() + "】，不能改变权限！");
			}
			
			Set<SysRole> sets = new HashSet<SysRole>();
			
			if(roleIds != null && roleIds.length > 0){
				
				for(int i = 0; i < roleIds.length; i++){
					
					SysRole sysRole = new SysRole();
					
					sysRole.setRoleId(roleIds[i]);

					sets.add(sysRole);
				}
				
			}
			
			sysUser.setRoles(sets);
			
			sysUserDao.save(sysUser);
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		SysUser sysUser = sysUserDao.findUniqueBy("username", username.trim());
		
		if(null == sysUser) {
			throw new UsernameNotFoundException("用户" + username + " 不存在！");
		}
		
		return sysUser;
	}

	public SysUser findUniqueByUsername(String username) {
		return sysUserDao.findUniqueBy("username", username.trim());
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED,readOnly=true)
	public List<SysUser> findSysUserByRoleIds(String ids){
		
		List<SysUser> results = null;
		
		try{
			List<String> longIds = new ArrayList<String>();
			
			//将ids用，进行分隔
			StringTokenizer st = new StringTokenizer(ids,",");
			
			while(st.hasMoreTokens()){
				longIds.add(st.nextToken());
			}
			
			String hql = "select distinct o from SecuritySysUser o join o.roles r where r.roleId in(:longIds)";
			
			results = (List<SysUser>)(sysUserDao.createQuery(hql).setParameterList("longIds", longIds).list());
		}catch(Exception e){
			throw new ServiceException("通过角色ids集合查询对应的用户失败！");
		}
		
		return results;
	}
	
	//判断是否为超级系统管理员
	private boolean isSuperuser(String id){
		return "1".endsWith(id);
	}
	
	//判断是否是自己
	private boolean isMyself(String id){
		SysUser sysUser = null;
		try{
			sysUser = SpringSecurityContextUtils.getLoginUser();
		}catch(Exception e){
		}
		
		if(null == sysUser){
			return false;
		}
		
		return id == sysUser.getUserId();
	}

	public List<SysUser> findSysUserBySpInfoId(String spinfId) {
		List<SysUser> results = null;
		
		try{
			String hql = "select distinct u from SysGroup g join g.spInfo s join g.users u where s.id = ?";
			
			results = sysUserDao.find(hql,spinfId);
		}catch(Exception e){
			throw new ServiceException("通过资质ID查询对应组织架构的用户失败！");
		}
		
		return results;
	}
	
	public List<SysUser> chkUsername(String username){
		return sysUserDao.chkUsername(username);
	}

	public List<Object[]> getAllUserEmail() {
		return sysUserDao.getAllUserEmail();
	}

	public SysUser getByEmail(String email) {
		List<SysUser> list = sysUserDao.getByEmail(email);
		if (list.size() == 0) {
			return null;
		}
		else if (list.size() > 1) {
			logger.warn("不用的用户有相同的email地址");
			return list.get(0);
		}
		else {
			return list.get(0);
		}
	}

}
