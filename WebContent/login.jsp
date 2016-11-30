<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to stay in our RAMPAGE hotel</title>
</head>
<body>


<%
	request.getSession().setAttribute("current_user", null);
%>


	<form action = "login" method = "POST">
	<h2>User login</h2>
   <em>${error}</em>
		<table>

	        <tr>
	            <td><label for="txtname">Username:</label></td>
	            <td><input type="text" id="txtname" name="login_username" /></td>
	        </tr>
	        <tr>
	            <td><label for="txtpswd">Password:</label></td>
	            <td><input type="password" id="txtpswd" name="login_pswd" /></td>
	        </tr>
	        <tr>
	            <td colspan=2>
	                <input type="reset" value = "Reset"/>
	                <input type="submit" value = "Login"/>
	            </td>
	        </tr>
	    </table>
	</form>
</body>
</html>