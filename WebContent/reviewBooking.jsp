<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Review Booking</title>
</head>
<body>
<%
    String accountId = request.getParameter("bookingid");
System.out.print(accountId);
request.getSession().setAttribute("bookingid", accountId);
%>
<h2>Review Booking</h2>
<p>Please enter your pin number</p>

<form action=ReviewBooking method="post">
<input name="pin" value=''>
<input type="submit" value="Review"> 
</form>
</body>
</html>