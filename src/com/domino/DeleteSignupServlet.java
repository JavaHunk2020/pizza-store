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

@WebServlet("/deleteSignup")
public class DeleteSignupServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String sid=req.getParameter("sid");
		int count=0;
		try {
			Connection connection = SQLConnectionUtils.getConn();
			//Compiling query and assigning into PreparedStatement object
			PreparedStatement pstmt=connection.prepareStatement(SQLQuery.DELETE_SIGNUP);
			//setting the values inside PreparedStatement object
			pstmt.setInt(1,Integer.parseInt(sid));
			count= pstmt.executeUpdate();
			
		}catch (Exception e) {
				e.printStackTrace();
		}
		//Forwarding request to the servlet!!!!
		req.getRequestDispatcher("users").forward(req, resp);
	}
}
