<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page import="java.util.*"%>
<%@ page import="businessLogic.jdbc.RoomDAO"%>
<%@ page import="businessLogic.javaClass.Room"%>
<%@ page import="java.sql.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
RoomDAO roomdao = new RoomDAO();
ArrayList<Room> roomList = new ArrayList<Room>();
try {
roomList = roomdao.randomRoom(3);
} catch (SQLException e) {
e.printStackTrace();
}
	//ArrayList<Room> roomList = (ArrayList) request.getAttribute("roomList");
	pageContext.setAttribute("rooms", roomList);
%>
<title>Welcome!</title>
</head>
<body>
<%@ include file="Header.jsp" %>
	<h2>Welcome to RAMPAGE HOTELS</h2><br>
		<form action = "search.jsp">
				<input type="submit"  value="Search for booking" class="button"/>	
			</form><br>

		<h3>Featured rooms</h3>
		
<table>
	<tr>
		<th>Preview</th>
		<th>Room Type</th>
		<th>City</th>
		<th>Price</th>
	</tr>
	
  <c:forEach items="${rooms}" var="room">
  <c:set var="roomtype" value="${room.getRoomType()}"/>	
    <tr>
      <td width="120px" height="100px"><img src="images/${fn:toLowerCase(roomtype)}.jpg" alt="Image" style="display:block;" width="100%" height="100%"></td>
      <td><c:out value="${room.getRoomType()}" /></td>
      <td><c:out value="${room.getCity()}"/></td>
      <td><fmt:formatNumber value="${room.getPrice()}" type="currency"/></td>
    </tr>
    </c:forEach>
</table>
		
		
		
</body>
</html>