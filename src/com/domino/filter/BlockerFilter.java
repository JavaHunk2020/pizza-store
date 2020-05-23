package com.domino.filter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

@WebFilter(filterName="blocker")
public class BlockerFilter implements Filter {
	
	private Set<String> allowedResources=new HashSet<>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		allowedResources.add("/login.jsp");
		allowedResources.add("/signup.jsp");
		allowedResources.add("/signup");
		allowedResources.add("/auth");
		allowedResources.add("/logout");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest  httpServletRequest=(HttpServletRequest)request;
		String resoureName=httpServletRequest.getServletPath();
		System.out.println("Accessing resoureName = "+resoureName+" at  "+LocalDateTime.now());
		//GO Inside
		if(allowedResources.contains(resoureName)) {
			chain.doFilter(request, response);	
		}else {
			HttpSession httpSession=httpServletRequest.getSession(false);
			if(httpSession!=null && httpSession.getAttribute("email")!=null) {
				   chain.doFilter(request, response);
			}else {
				HttpServletResponse  response2=(HttpServletResponse)response;
				//httpServletRequest.getServletContext()  = http://localhost:9999/pizza-store
				response2.sendRedirect(httpServletRequest.getContextPath()+"/login.jsp");
			}
		}
	}

	@Override
	public void destroy() {
		
	}




	
}
