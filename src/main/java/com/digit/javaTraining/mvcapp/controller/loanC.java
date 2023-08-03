package com.digit.javaTraining.mvcapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.mvcapp.model.loanApp;

@WebServlet("/loan")
public class loanC extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int option=Integer.parseInt(req.getParameter("option"));
		
		loanApp loanapp=new loanApp();
		
		boolean x=loanapp.getLoan(option);
		
		HttpSession session = req.getSession();
		
		if(x==true) {
			session.setAttribute("loan_id",loanapp.getLoan_id());
			session.setAttribute("loan_type",loanapp.getLoan_type());
			session.setAttribute("tenure",loanapp.getTenure());
			session.setAttribute("interest",loanapp.getInterest());
			session.setAttribute("description",loanapp.getDescription());
			
			resp.sendRedirect("loanDetails.jsp");


			

		}
		else {
			resp.sendRedirect("loanDetailsFail.html");

		}
		
	}
	
}
