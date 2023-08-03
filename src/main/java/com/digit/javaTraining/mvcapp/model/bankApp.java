package com.digit.javaTraining.mvcapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpSession;

public class bankApp {

	int bank_id;
	String bank_name;

	String ifsc_code;
	int accno;
	int pin;
	int cust_id;
	String customer_name;
	int balance;
	String email;
	int phone;
	public Connection con;
	public PreparedStatement pstmt;
	private ResultSet res;

	public int getBank_id() {
		return bank_id;
	}

	public void setBank_id(int bank_id) {
		this.bank_id = bank_id;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getIfsc_code() {
		return ifsc_code;
	}

	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}

	public int getAccno() {
		return accno;
	}

	public void setAccno(int accno) {
		this.accno = accno;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public bankApp() {
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

	public boolean register() {
		try {

			String sql = "insert into bankApp values(?,?,?,?,?,?,?,?,?,?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, bank_id);
			pstmt.setString(2, bank_name);
			pstmt.setString(3, ifsc_code);
			pstmt.setInt(4, accno);
			pstmt.setInt(5, pin);
			pstmt.setInt(6, cust_id);
			pstmt.setString(7, customer_name);
			pstmt.setInt(8, balance);
			pstmt.setString(9, email);
			pstmt.setLong(10, phone);

			int x = pstmt.executeUpdate();

			if (x > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean login() {

		try {

			String sql = "select * from bankApp where cust_id=? and pin=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, cust_id);
			pstmt.setInt(2, pin);

			res = pstmt.executeQuery();

			if (res.next() == true) {
//				
				this.setCustomer_name(res.getString("customer_name"));
				this.setAccno(res.getInt("accno"));
				this.setPin(res.getInt("pin"));
				return true;

			} else {
//				resp.sendRedirect("/BankingApplication/loginFail.html");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	
	public boolean checkBalance() {
		try {
		String sql = "select * from bankApp where accno=?";

		pstmt = con.prepareStatement(sql);

		
		
		pstmt.setInt(1, accno);
		

		res = pstmt.executeQuery();

		if(res.next()==true) {
//			session.setAttribute("balance", res.getInt("balance"));
//			resp.sendRedirect("/BankingApplication/balance.jsp");
			setBalance(res.getInt("balance"));
			return true;
		} 
		else {
//			resp.sendRedirect("/BankingApplication/balanceFail.jsp");
			return false;
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
		
	return false;
	}
	
	public boolean changePassword(int confPassword,int oldPassword) {
		try {
			
			
			String sql = "update bankapp set pin=? where accno=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, confPassword);
			pstmt.setInt(2, accno);

			if (oldPassword==(pin)) {
				int x = pstmt.executeUpdate();
				if(x>0)
//					resp.sendRedirect("/BankingApplication/passwordChangeSuccess.html");
					return true;
			} else {

//				resp.sendRedirect("/BankingApplication/passwordChangeFail.html");
				return false;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
}
