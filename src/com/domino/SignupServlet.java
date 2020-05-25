package com.domino;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.SignupDao;
import com.dao.SignupDaoImpl;
import com.dao.query.SQLQuery;
import com.utils.SQLConnectionUtils;

///PizzaServlet is servlet ??? yes

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String salutation=req.getParameter("salutation");
		
		SignupDao signupDao=new SignupDaoImpl();
		int count=signupDao.signup(username, password, email, name, salutation);
		
		resp.setContentType("text/html");
		//below line is returning reference of Body of the response
		PrintWriter pw=resp.getWriter();
		if(count>0) {
			req.getRequestDispatcher("rsuccess.jsp").forward(req, resp);
		}else {
			pw.println("<h1>Sorry,  There is some problem in registration!! </h1>");
		}
	}
}
