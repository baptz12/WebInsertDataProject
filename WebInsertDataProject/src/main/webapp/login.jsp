<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel='stylesheet' type='text/css' href='style.css'>
</head>
<body>
	<div>
		<h1 class="login_title">Login</h1>
		<form action="login_page" method="post">
			<input type="text" name="username" class="login_input" value="" size="50" placeholder="Username"required/>
			<input type="password" name="password" class="login_input" value="" size="50" placeholder="Password" required/>
			<input type="submit" class="submit_button_login" value="Submit" name="submit" required/>
		</form>
    	<form action='register' method='get'>
    		<button type='submit' class='back_button' name='exit_button'>Register</button>
    	</form>
	</div>
</body>
</html>