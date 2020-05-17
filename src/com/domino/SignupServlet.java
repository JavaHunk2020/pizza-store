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
		int count=0;
		try {
			Date date=new Date();
			Timestamp timestamp=new Timestamp(date.getTime());
			Connection connection = SQLConnectionUtils.getConn();
			//Compiling query and assigning into PreparedStatement object
			PreparedStatement pstmt=connection.prepareStatement(SQLQuery.INSERT_SIGNUP);
			//setting the values inside PreparedStatement object
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			pstmt.setString(3,email);
			pstmt.setString(4,name);
			pstmt.setString(5,salutation);
			pstmt.setTimestamp(6,timestamp);
			//Fire the query
			count= pstmt.executeUpdate();
			
		}catch (Exception e) {
				e.printStackTrace();
		}
		
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
