package com.digit.javaTraining.mvcapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpSession;

public class loanApp {
	// loan_id | loan_type | tenure | interest | description
	int loan_id;
	String loan_type;
	int tenure;
	float interest;
	String description;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;

	public int getLoan_id() {
		return loan_id;
	}

	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
	}

	public String getLoan_type() {
		return loan_type;
	}

	public void setLoan_type(String loan_type) {
		this.loan_type = loan_type;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public float getInterest() {
		return interest;
	}

	public void setInterest(float interest) {
		this.interest = interest;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public loanApp() {
		String url = "jdbc:mysql://localhost:3306/bankingapplication";// bankingapplication
		String user = "root";
		String pwd = "1234";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");

			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("connection created");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean getLoan(int option) {
		try {
			

			String sql = "select * from loan where loan_id=?";

			pstmt = con.prepareStatement(sql);

			
			
			pstmt.setInt(1, option);
			
			res = pstmt.executeQuery();

			if(res.next()==true) {
//				session.setAttribute("loan_id", res.getInt("loan_id"));
//				session.setAttribute("loan_type", res.getString("loan_type"));
//				session.setAttribute("tenure", res.getInt("tenure"));
//				session.setAttribute("interest", res.getFloat("interest"));
//				session.setAttribute("loan_type", res.getString("description"));
				setLoan_id(res.getInt("loan_id"));
				setLoan_type(res.getString("loan_type"));
				setTenure(res.getInt("tenure"));
				setInterest(res.getFloat("interest"));
				setDescription(res.getString("description"));
				return true;
				
//				resp.sendRedirect("/BankingApplication/loanDetails.jsp");

			} 
			else {
//				resp.sendRedirect("/BankingApplication/loanDetailsFail.html");
				return false;
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		
		
		return false;
	}
}
