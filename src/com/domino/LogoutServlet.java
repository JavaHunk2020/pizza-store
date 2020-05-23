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
import javax.servlet.http.HttpSession;

import com.dao.query.SQLQuery;
import com.utils.SQLConnectionUtils;

///PizzaServlet is servlet ??? yes

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		HttpSession session=req.getSession(false);
		if(session!=null) {
			//kill the session
			session.invalidate();
		}
		resp.sendRedirect(req.getContextPath()+"/login.jsp");;
	}
}
