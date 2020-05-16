package com.domino;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

///PizzaServlet is servlet ??? yes

@WebServlet("/pizza")
public class PizzaServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String word=req.getParameter("sweta");
		
		String message="This is odd";
		
		if(word.length()%2==0) {
			message="This is even";
		}
		
		resp.setContentType("text/html");
		
		//below line is returning reference of Body of the response
		PrintWriter pw=resp.getWriter();
		pw.println("<h1>Hello I am pizza!! and  "+message+"</h1>");
		
	}
	
}
