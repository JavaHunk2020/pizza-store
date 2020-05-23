package com.domino;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.query.SQLQuery;
import com.utils.SQLConnectionUtils;

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
		boolean flag=false;
		try {
			Connection connection = SQLConnectionUtils.getConn();
			// Compiling query and assigning into PreparedStatement object
			PreparedStatement pstmt = connection.prepareStatement(SQLQuery.SELECT_SIGNUP_USERNAME_PASSWORD);
			// setting the values inside PreparedStatement object
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			// Fire the query
			ResultSet rs=pstmt.executeQuery();
			//username,password,email,name,salutation,datecreated
			if(rs.next()) {
				flag=true;
				String email=rs.getString(3);
				String name=rs.getString(4);
				String salutation=rs.getString(5);
				//Setting values inside request scope
				req.setAttribute("email", email);
				req.setAttribute("name", name);
				req.setAttribute("salutation", salutation);
				//Expiration -30 minutes
				HttpSession session=req.getSession();
				//session.setMaxInactiveInterval(60*5);
				if("marry1000".equals(username)) {
					session.setAttribute("role","admin");
				}else {
					session.setAttribute("role","customer");
				}
				session.setAttribute("name", name);
				session.setAttribute("email", email);
				session.setAttribute("salutation", salutation);
				
				req.getRequestDispatcher("success.jsp").forward(req, resp);
			}else {
				pw.println("<h1>Sorry,  your username and password are  not correct! </h1>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
