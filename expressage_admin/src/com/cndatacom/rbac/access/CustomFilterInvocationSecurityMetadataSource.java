package com.cndatacom.rbac.access;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;
import org.springframework.stereotype.Component;

import com.cndatacom.rbac.pojo.SysAuthority;
import com.cndatacom.rbac.system.service.ISysAuthorityService;
/**
 * 自定义资源加载MetadataSource,会在自定义安全过滤拦截器中使用
 * @author yab
 *
 */
@Component("customFilterInvocationSecurityMetadataSource")
public class CustomFilterInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	
	private static Map<String, Set<ConfigAttribute>> map = new HashMap<String, Set<ConfigAttribute>>();
	
	@Resource
	private ISysAuthorityService sysAuthorityService;
	
	@PostConstruct
	public void loadResources(){
		
		List<SysAuthority> allResults = sysAuthorityService.getAll();
		
		for(SysAuthority authority : allResults){
			//初始化URL权限
			if(0L == authority.getAuthorityType().longValue()) {
			
				Set<ConfigAttribute> configs = map.get(authority.getAuthorityUrl());
				
				if(null == configs){
					configs = new HashSet<ConfigAttribute>();
				}
				
				configs.add(new SecurityConfig(authority.getPrefixedName()));
				
				map.put(authority.getAuthorityUrl(), configs);
			}
		}
	}
	
	public Collection<ConfigAttribute> getAllConfigAttributes(){
		return null;
	}

	public Collection<ConfigAttribute> getAttributes(Object obj)
			throws IllegalArgumentException {
		
		String accessURL = ((FilterInvocation)obj).getRequestUrl();
		
		Iterator<String> iter = map.keySet().iterator();
		
		while(iter.hasNext()){
			String url = iter.next();
			
			if(this.urlMatcher.pathMatchesUrl(url,accessURL)){
				return map.get(url);
			}
		}
		
		return null;
	}

	public boolean supports(Class<?> clazz){
		return true;
	}

}
