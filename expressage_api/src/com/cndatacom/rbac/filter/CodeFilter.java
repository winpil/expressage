package com.cndatacom.rbac.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CodeFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		
		String random = req.getParameter("random");
		String username = req.getParameter("j_username");
		
		String sRand = (String)session.getAttribute("rand");
		if( null != sRand) {
			if(sRand.equalsIgnoreCase(random.trim())){
				session.removeAttribute("rand");
				chain.doFilter(request, response);
			}else{
				request.setAttribute("codeError","验证码输入不正确！");
				request.setAttribute("username",username);
				request.getRequestDispatcher("/login.jsp").forward(request,response);
			}
		}
		
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
