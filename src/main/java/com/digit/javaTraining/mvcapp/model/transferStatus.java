package com.digit.javaTraining.mvcapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class transferStatus {


	int cust_id;
	String bank_name;
	String ifsc_code;
	int sender_accno;
	String reciever_ifsc;
	int reciever_accno;
	float amount;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res1;
	private ResultSet res2;
	private ResultSet res3;
	
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
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
	public int getSender_accno() {
		return sender_accno;
	}
	public void setSender_accno(int sender_accno) {
		this.sender_accno = sender_accno;
	}
	public String getReciever_ifsc() {
		return reciever_ifsc;
	}
	public void setReciever_ifsc(String reciever_ifsc) {
		this.reciever_ifsc = reciever_ifsc;
	}
	public int getReciever_accno() {
		return reciever_accno;
	}
	public void setReciever_accno(int reciever_accno) {
		this.reciever_accno = reciever_accno;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	public transferStatus() {
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
	
	public boolean transferAmount(int pin) {
		try {
		pstmt = con.prepareStatement("select * from  where cust_id=? and ifsc_code=? and accno=? and pin=?");

		pstmt.setInt(1, cust_id);
		pstmt.setString(2, ifsc_code);
		pstmt.setInt(3, sender_accno);
		pstmt.setInt(4, pin);

		res1 = pstmt.executeQuery();

		if (res1.next() == true) {
			pstmt = con.prepareStatement("select * from bankapp where ifsc_code=? and accno=?");
			pstmt.setString(1, reciever_ifsc);
			pstmt.setInt(2, reciever_accno);
			res2 = pstmt.executeQuery();

			if (res2.next() == true) {
				pstmt = con.prepareStatement("select balance from bankapp where accno=?");
				pstmt.setInt(1, sender_accno);
				res3 = pstmt.executeQuery();
				res3.next();
				int balance = res3.getInt(1);

				if (balance > amount) {
					pstmt = con.prepareStatement("update bankapp set balance=balance-? where accno=?");
					pstmt.setFloat(1, amount);
					pstmt.setInt(2, sender_accno);

					int x1 = pstmt.executeUpdate();

					if (x1 > 0) {
						pstmt = con.prepareStatement("update bankapp set balance=balance+? where accno=?");
						pstmt.setFloat(1, amount);
						pstmt.setInt(2, reciever_accno);

						int x2 = pstmt.executeUpdate();

						if (x2 > 0) {
							pstmt = con.prepareStatement("insert into transferStatus values(?,?,?,?,?,?,?)");
							pstmt.setInt(1, cust_id);
							pstmt.setString(2, bank_name);
							pstmt.setString(3, ifsc_code);
							pstmt.setInt(4, sender_accno);
							pstmt.setString(5, reciever_ifsc);
							pstmt.setInt(6, reciever_accno);
							pstmt.setFloat(7, amount);

							int x3 = pstmt.executeUpdate();
							if (x3 > 0) {
//								resp.sendRedirect("/BankingApplication/transferSucess.jsp");
								
								return true;

							} else {
//								String transferdetails= "TransactionDetailsError";
//										session.setAttribute("error",transferdetails);
//								resp.sendRedirect("/BankingApplication/transferFail.jsp");
								return false;

							}

						} else {
//							String transferdetails= "BalanceCreditError";
//							session.setAttribute("error",transferdetails);
//							resp.sendRedirect("/BankingApplication/transferFail.jsp");
							return false;

						}
					} else {
//						String transferdetails= "BalanceDebitError";
//						session.setAttribute("error",transferdetails);
//						resp.sendRedirect("/BankingApplication/transferFail.jsp");
						return false;
					}

				} else {
//					String transferdetails= "InsufficientBalanceError";
//					session.setAttribute("error",transferdetails);
//					resp.sendRedirect("/BankingApplication/transferFail.jsp");
					return false;
				}

			} else {
//				String transferdetails= "RecieverCredentialsError";
//				session.setAttribute("error",transferdetails);
//				resp.sendRedirect("/BankingApplication/transferFail.jsp");
				return false;
			}

		} else {
//			String transferdetails= "SenderCredentialsError";
//			session.setAttribute("error",transferdetails);
//			resp.sendRedirect("/BankingApplication/transferFail.jsp");
			return false;
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
		
		
		return false;
		
	}
	
}
