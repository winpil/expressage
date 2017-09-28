package com.cndatacom.rbac.access;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * 自定义访问决策Manager，会在自定义安全过滤拦截器中使用
 * @author yab
 *
 */
@Component("customAccessDecisionManager")
public class CustomAccessDecisionManager implements AccessDecisionManager {
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {

		Iterator<ConfigAttribute> iter = configAttributes.iterator();
		
		while (iter.hasNext()) {
			ConfigAttribute configAttribute = iter.next();

			String needRole = ((SecurityConfig) configAttribute).getAttribute();

			for (GrantedAuthority grantedAuthority : authentication
					.getAuthorities()) {

				String ownRole = grantedAuthority.getAuthority();
				
				if (needRole.equals(ownRole)) {
					return;
				}
			}
		}

		throw new AccessDeniedException("对不起，你没有权限访问该资源！");
	}

	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
}
