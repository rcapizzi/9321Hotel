<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Email Verification</title>
</head>
<body>
	Result: <%=(String)request.getAttribute("result")  %>
	<br>
	<br>
	<form action="welcome.jsp" method = "GET">
	 <input type="submit" value="Back to homepage" />
	</form>
	
	
	
	
</body>
</html>