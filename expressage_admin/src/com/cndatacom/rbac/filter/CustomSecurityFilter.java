package com.cndatacom.rbac.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * FILTER_SECURITY_INTERCEPTOR 自定义安全过滤拦截器，用于处理URL权限过滤
 * @author yab
 *
 */
public class CustomSecurityFilter extends AbstractSecurityInterceptor implements
		Filter {
	
	private FilterInvocationSecurityMetadataSource securityMetadataSource;

	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return securityMetadataSource;
	}

	public void setSecurityMetadataSource(
			FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		FilterInvocation filterInvocation = new FilterInvocation(req, resp,
				chain);

		InterceptorStatusToken statusToken = this
				.beforeInvocation(filterInvocation);

		filterInvocation.getChain().doFilter(filterInvocation.getHttpRequest(),
				filterInvocation.getResponse());

		this.afterInvocation(statusToken, null);

	}

	public void init(FilterConfig config) throws ServletException {

	}

	public Class<? extends Object> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}
}
