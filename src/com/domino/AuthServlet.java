package com.domino;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		if("jack".equals(username) && "jill".equals(password)) {
			req.getRequestDispatcher("success.jsp").forward(req, resp);
		}else {
			pw.println("<h1>Sorry,  your username and password are  not correct! </h1>");
		}
	}
}
