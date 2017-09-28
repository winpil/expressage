
package com.cndatacom.common.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.cndatacom.rbac.pojo.SysUser;

/**
 * SpringSecurity 工具类
 * @author yab
 *
 */
public class SpringSecurityContextUtils {
	
	/**
	 * 取得当前登录认证信息
	 */
	public static Authentication getAuthentication() {
		SecurityContext context = SecurityContextHolder.getContext();
		
		if (null != context) {
			return context.getAuthentication();
		}
		return null;
	}
	/**
	 * 取得当前登录用户
	 */
	public static SysUser getLoginUser() {
		Authentication authentication = getAuthentication();
		if (null != authentication) {
			Object principal = authentication.getPrincipal();
			
			if (principal instanceof SysUser) {
				return (SysUser) principal;
			}
		}
		return null;
	}

	/**
	 * 取得当前用户的登录名
	 */
	public static String getLoginUserName() {
		Authentication authentication = getAuthentication();
		if (authentication != null && authentication.getPrincipal() != null) {
			return authentication.getName();
		}
		
		return "";
	}

	/**
	 * 取得当前用户登录IP
	 */
	public static String getLoginUserIpAddress() {
		Authentication authentication = getAuthentication();
		
		if (authentication != null) {
			Object details = authentication.getDetails();
			if (details instanceof WebAuthenticationDetails) {
				WebAuthenticationDetails webDetails = (WebAuthenticationDetails) details;
				return webDetails.getRemoteAddress();
			}
		}

		return "";
	}


}