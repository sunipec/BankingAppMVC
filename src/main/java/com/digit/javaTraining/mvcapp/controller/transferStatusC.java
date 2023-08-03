package com.digit.javaTraining.mvcapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.mvcapp.model.transferStatus;

@WebServlet("/Transfer1")
public class transferStatusC extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		transferStatus ts=new transferStatus();
		HttpSession session = req.getSession();

		ts.setCust_id(Integer.parseInt(req.getParameter("cust_id")));
		ts.setBank_name( req.getParameter("bank_name"));
		ts.setIfsc_code( req.getParameter("ifsc_code"));
		ts.setSender_accno (Integer.parseInt(req.getParameter("sender_accno")));
		ts.setReciever_ifsc(req.getParameter("reciever_ifsc"));
		ts.setReciever_accno( Integer.parseInt(req.getParameter("reciever_accno")));

		ts.setAmount( Integer.parseInt(req.getParameter("amount")));
		int pin = Integer.parseInt(req.getParameter("pin"));

		boolean x=ts.transferAmount( pin);
		

		if(x==true) {

			resp.sendRedirect("/BankApplicationMVC/transferSucess.jsp");


		}
		else {
			String transferdetails= "Error";

			session.setAttribute("error",transferdetails);

			resp.sendRedirect("/BankApplicationMVC/transferSucess.jsp");
		}

	}


}
