package com.domino.filter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter("/*")
@WebFilter(filterName="roleChecker")
public class RoleFilter implements Filter {
	
	private Map<String,String> roleAndURL=new HashMap<>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		roleAndURL.put("deleteSignup","admin");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest  httpServletRequest=(HttpServletRequest)request;
		//deleteSignup
		String resoureName=httpServletRequest.getServletPath();
		System.out.println("Accessing resoureName = "+resoureName+" at  "+LocalDateTime.now());
		//GO Inside
		if(resoureName.contains("delete")) {
			HttpSession httpSession=httpServletRequest.getSession(false);
			if(httpSession!=null) {
				String role=(String)httpSession.getAttribute("role");
			   if(role.equalsIgnoreCase("admin")) {
				   chain.doFilter(request, response);
			   }else {
				   HttpServletResponse  response2=(HttpServletResponse)response;
				   response2.sendRedirect(httpServletRequest.getContextPath()+"/users?message=\"Hey! you cannot delete it\"");
			   }
			}
		}else {
			  chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		
	}




	
}
