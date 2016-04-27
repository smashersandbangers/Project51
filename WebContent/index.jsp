<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="stylesheet.css" />
<title>Project 51</title>
</head>
<body>

<div id=wrapper>
	<h1 class=center>Classified</h1>
	
	<form id="loginForm" action="Login" method="POST">
		<h2>Log In</h2>
		<input type="text" name="username" placeholder="Username" required><br>
		<input type="password" name="password" placeholder ="Password" required><br>
		<input type="submit" value="Login"><br>
		${errorMessage}
	</form>
	<p>PLM Test</p>
</div>

</body>
</html>