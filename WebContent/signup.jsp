<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	request.getSession().setAttribute("current_user", null);
	String error_message  = (String)request.getSession().getAttribute("error");
%>
<form action = "signup" method = "POST">
	<h2>New User</h2>
		<table>

	        <tr>
	            <td><label for="txtname">Username:</label></td>
	            <td><input type="text" id="txtname" name="new_username" /></td>
	        </tr>
	        <tr>
	            <td><label for="txtpswd">Password:</label></td>
	            <td><input type="password" id="txtpswd" name="new_password" /></td>
	        </tr>
	        <tr>
	            <td><label for="txtemail">email:</label></td>
	            <td><input type="text" id="txtemail" name="new_email" /></td>
	        </tr>
	        <tr>
	            <td colspan=2>
	                <input type="reset" value = "Reset"/>
	                <input type="submit" value = "Register New User"/>
	            </td>
	        </tr>
	    </table>
	</form>


</body>
</html>