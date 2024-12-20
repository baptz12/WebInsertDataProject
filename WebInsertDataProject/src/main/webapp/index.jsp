<%@ page import="java.sql.*"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.io.IOException" %>
<% Class.forName("com.mysql.jdbc.Driver"); %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	if (session.getAttribute("username") == null) {
	  response.sendRedirect("login.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert Data into DB</title>
</head>
<link rel='stylesheet' type='text/css' href='style.css'>
<body>
	<h1 class="title">Insert Data into a Database</h1>
	<form action ="add" method="post">
	<table border="0" class="input_table">
		<tbody>
			<tr>
				<td><input type="text" name="prenom" class="add_input" value="" size="50" placeholder="Prénom"/></td> 
			</tr>
			<tr>
				<td><input type="text" name="nom" class="add_input" value="" size="50" placeholder="Nom"/></td> 
			</tr>
		</tbody>
	</table>
	<input type="reset" value="Clear" name="clear"/>
	<input type="submit"  class="submit_button" value="Submit" name="submit"/>
	<button type="submit" class="db_button" name="db_button">See what's in the Database</button>
	</form>
	<form action ="logout" method="get">
		<button type="submit" class="logout_button" name="register_button">Logout</button>
	</form>
</body>
</html>
