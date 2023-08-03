package com.digit.javaTraining.mvcapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.mvcapp.model.bankApp;

@WebServlet("/checkbalance")
public class CheckBalanceC extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		bankApp bankapp=new bankApp();
		HttpSession session1=req.getSession();
		
		bankapp.setAccno((int)session1.getAttribute("accno"));
		
		boolean x=bankapp.checkBalance();
		
		if(x==true) {
			session1.setAttribute("balance", bankapp.getBalance());
			resp.sendRedirect("balance.jsp");
		}
		else {
			resp.sendRedirect("balanceFail.jsp");

		}
	}
}
