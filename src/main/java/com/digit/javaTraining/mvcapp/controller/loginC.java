package com.digit.javaTraining.mvcapp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.mvcapp.model.bankApp;

@WebServlet("/login")
public class loginC extends HttpServlet {

	protected void service(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		
		bankApp bankapp=new bankApp();
		
		bankapp.setCust_id(Integer.parseInt(req.getParameter("cust_id")));
		bankapp.setPin(Integer.parseInt(req.getParameter("pin")));
		
		boolean x=bankapp.login();
		if(x==true) {
			session.setAttribute("customer_name", bankapp.getCustomer_name());
			session.setAttribute("accno", bankapp.getAccno());

			session.setAttribute("pin", bankapp.getPin());

			resp.sendRedirect("homePage.jsp");
		}
		else {
			resp.sendRedirect("loginFail.html");
		}
	}
}
