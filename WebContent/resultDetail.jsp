<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="javax.servlet.annotation.WebServlet"%>
<%@page import="javax.servlet.ServletException"%>

<%@page import = "businessLogic.jdbc.*" %>
<%@page import = "businessLogic.javaClass.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result Detail</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<%
	ArrayList<Room> rooms = (ArrayList)request.getSession().getAttribute("roomResult");
	if(rooms.size()==0){%>
	<p>Sorry, no matching data was found!</p>
	<%}else{ %>
	
	<form action ="booking" method="post">
	<table>
	<tr>
		<td>
		Book
	</td>
	<td>
	<p>Room Number</p>
	</td>
	<td>
	<p>Hotel</p>
	</td>
	<td>
	<p>Room Type</p>
	</td>
	<td>
	<p>Price</p>
	</td>
	<td>
	<p>Description</p>
	</td>

	</tr>
	<%
	for (int i =0;i<rooms.size();i++){
		int this_room = rooms.get(i).getRoomId();
	
	%>
	<tr>
		<td>
		<input type="checkbox" name="bookRoom" value="<%=this_room%>">
	</td>
	<td>
	<p><%=rooms.get(i).getRoomNo()%></p>
	</td>
	<td>
	<p><%=rooms.get(i).getHotelId()%></p>
	</td>
	<td>
	<p><%=rooms.get(i).getRoomType()%></p>
	</td>
	<td>
	<p><%=rooms.get(i).getPrice()%></p>
	</td>
	<td>
	<p><%=rooms.get(i).getRoomDescription()%></p>
	</td>

	</tr>
	<%}%>
	</table>
	<input type="submit" value="Add To Cart"> 
	<input type="hidden" name="op" value="add">
	</form>

<%}%>
</body>
</html>