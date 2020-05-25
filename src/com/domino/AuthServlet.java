package com.domino;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.SignupDao;
import com.dao.SignupDaoImpl;
import com.dao.entity.SignupEntity;

///PizzaServlet is servlet ??? yes

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		
		resp.setContentType("text/html");
		//below line is returning reference of Body of the response
		PrintWriter pw=resp.getWriter();
		SignupDao signupDao=new SignupDaoImpl();
		SignupEntity signupEntity=signupDao.authUser(username, password);
		if(signupEntity!=null) {
			req.setAttribute("email", signupEntity.getEmail());
			req.setAttribute("name", signupEntity.getName());
			req.setAttribute("salutation", signupEntity.getSalutation());
			//Expiration -30 minutes
			HttpSession session=req.getSession();
			//session.setMaxInactiveInterval(60*5);
			if("marry1000".equals(username)) {
				session.setAttribute("role","admin");
			}else {
				session.setAttribute("role","customer");
			}
			session.setAttribute("name", signupEntity.getName());
			session.setAttribute("email", signupEntity.getEmail());
			session.setAttribute("salutation", signupEntity.getSalutation());
			req.getRequestDispatcher("success.jsp").forward(req, resp);
		}	
	else {
		pw.println("<h1>Sorry,  your username and password are  not correct! </h1>");
	}
}
}
