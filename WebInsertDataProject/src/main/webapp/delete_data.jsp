<%@ page import="java.sql.*"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.io.IOException" %>
<% Class.forName("com.mysql.jdbc.Driver"); %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete</title>
<link rel='stylesheet' type='text/css' href='style.css'>
</head>
<body>
<%
	String id = request.getParameter("id");
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	String URL = "jdbc:mysql://localhost:3306/demo";
	String USERNAME = "root";
	String PASSWORD = "root";

	Class.forName("com.mysql.jdbc.Driver");
	con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	pst = con.prepareStatement("delete from users where id = ?");
	pst.setString(1, id);
	pst.executeUpdate();
%>
    <div class='success'>Deleted successfully !</div>
    <form action='pre_add' method='get'>
    <button type='submit' class='back_button' name='exit_button'>Back</button>
    </form>
</body>
</html>