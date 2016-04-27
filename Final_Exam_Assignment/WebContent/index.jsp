<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cricket Store Online Login Form </title>
</head>
<body>
<h1>Cricket Store Online </h1>

<p>

Please Login to continue shopping

</p>

<form  id="loginForm" action="Login" method ="POST"> 

<input type ="text" name = "username" placeholder ="Username" required > <br>
<input type ="password" name = "password" placeholder ="Password" required > <br>
<input type ="submit" value ="login"><br>

${errorMessage}

</form>
</body>
</html>