<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel='stylesheet' type='text/css' href='style.css'>
</head>
<body>
	<div class="register_page">
	<h1 class="register_title">Register</h1>
		<form action="register_page" method="post">
			<input type="text" name="username" class="register_input" value="" size="50" placeholder="Username" required/>
			<input type="text" name="email" class="register_input" value="" size="50" placeholder="Email"required/>
			<input type="password" name="password1" class="register_input" value="" size="50" placeholder="Password" required/>
			<input type="password" name="password2" class="register_input" value="" size="50" placeholder="Re-type Password" required/>
			<input type="submit" class="submit_button_register" value="Submit" name="submit" required/>
		</form>
		<form action='login' method='get'>
    		<button type='submit' class='back_button' name='exit_button'>Back</button>
    	</form>
	</div>
</body>
</html>
