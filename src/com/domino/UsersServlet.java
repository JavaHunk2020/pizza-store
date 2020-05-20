package com.domino;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.entity.SignupEntity;
import com.dao.query.SQLQuery;
import com.utils.SQLConnectionUtils;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Connection connection = SQLConnectionUtils.getConn();
			//Compiling query and assigning into PreparedStatement object
			PreparedStatement pstmt=connection.prepareStatement(SQLQuery.SELECT_SIGNUPS);
			//setting the values inside PreparedStatement object
			//Fire the query
			//public static String SELECT_SIGNUPS="select sid,username,password,email,name,salutation,datecreated from signup_tbl";
			ResultSet rs= pstmt.executeQuery();
			List<SignupEntity> signupList=new  ArrayList<>();
			while(rs.next()) {
				SignupEntity entity=new SignupEntity();
				entity.setSid(rs.getInt(1));
				entity.setUsername(rs.getString(2));
				entity.setPassword(rs.getString(3));
				entity.setEmail(rs.getString(4));
				entity.setName(rs.getString(5));
				entity.setSalutation(rs.getString(6));
				entity.setDatecreated(rs.getTimestamp(7));
				signupList.add(entity);
			}	
			//Adding signupList into request scope
			req.setAttribute("signupList", signupList);
			req.getRequestDispatcher("signups.jsp").forward(req, resp);
			
		}catch (Exception e) {
				e.printStackTrace();
		}
	}
}
