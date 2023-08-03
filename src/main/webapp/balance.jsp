<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Balance Page </title>
</head>
<body style="font-family: Arial, sans-serif; background-color: #DBF9FC; ">
<h3 style="color: green;">

	<%
	session =request.getSession();
	out.println("balance = "+session.getAttribute("accno"));
	%>
	
	
</h3>
<a href="homePage.jsp" style="color: blue;">Click here to redirect</a>
</body>
</html>