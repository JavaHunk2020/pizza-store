package com.domino;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.query.SQLQuery;
import com.utils.SQLConnectionUtils;

///PizzaServlet is servlet ??? yes

@WebServlet("/usignup")
public class USignupServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String sid=req.getParameter("sid");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String salutation=req.getParameter("salutation");
		try {
			Connection connection = SQLConnectionUtils.getConn();
			//Compiling query and assigning into PreparedStatement object
			PreparedStatement pstmt=connection.prepareStatement(SQLQuery.UPDATE_SIGNUP_BY_SID);
			//setting the values inside PreparedStatement object
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			pstmt.setString(3,email);
			pstmt.setString(4,name);
			pstmt.setString(5,salutation);
			pstmt.setInt(6,Integer.parseInt(sid));
			//Fire the query
			pstmt.executeUpdate();
			
		}catch (Exception e) {
				e.printStackTrace();
		}
		//http://localhost:9999/pizza-store
		resp.sendRedirect(req.getContextPath()+"/users");
	}
}
