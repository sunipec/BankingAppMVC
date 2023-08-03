package com.digit.javaTraining.mvcapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.mvcapp.model.bankApp;
@WebServlet("/changePassword")
public class ChangePasswordC extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		bankApp bankapp=new bankApp();
		HttpSession session = req.getSession();
		bankapp.setPin((int)session.getAttribute("pin"));
		bankapp.setAccno((int)session.getAttribute("accno"));
			int confPassword = Integer.parseInt(req.getParameter("confPassword"));
			int oldPassword = Integer.parseInt(req.getParameter("password"));
			
			boolean x=bankapp.changePassword(confPassword,oldPassword);

			if(x==true) {
			resp.sendRedirect("passwordChangeSuccess.html");

			}
			else {
				resp.sendRedirect("passwordChangeFail.html");

			}
	}
}
