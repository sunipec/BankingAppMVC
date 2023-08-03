package com.digit.javaTraining.mvcapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.digit.javaTraining.mvcapp.model.bankApp;
@WebServlet("/register")
public class RegisterC extends HttpServlet {
 @Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	 
		
		bankApp bankapp=new bankApp();
		
		bankapp.setBank_id(Integer.parseInt(req.getParameter("bank_id")));
		bankapp.setBank_name(req.getParameter("bank_name"));
		bankapp.setIfsc_code(req.getParameter("ifsc_code"));
		bankapp.setAccno(Integer.parseInt(req.getParameter("accno")));
		bankapp.setPin(Integer.parseInt(req.getParameter("pin")));
		bankapp.setCust_id(Integer.parseInt(req.getParameter("cust_id")));
		bankapp.setCustomer_name(req.getParameter("customer_name"));
		bankapp.setBalance(Integer.parseInt(req.getParameter("balance")));
		bankapp.setEmail(req.getParameter("email"));
		bankapp.setPhone(Integer.parseInt(req.getParameter("phone")));
		
		boolean x1=bankapp.register();
		
		if(x1==true) {
			resp.sendRedirect("/BankApplicationMVC/Success.html");
		}
		else {
			resp.sendRedirect("/BankApplicationMVC/Fail.html");
		}
}
}
