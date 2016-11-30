<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	ArrayList<String> occs = (ArrayList) request.getAttribute("occupied");
	ArrayList<String> maints = (ArrayList) request.getAttribute("maintenance");
	ArrayList<String> rooms = new ArrayList(
			Arrays.asList("101", "102", "201", "202", "301", "302", "401", "501"));
	String hotelName = (String) request.getAttribute("hotel_name");
	pageContext.setAttribute("occs", occs);
	pageContext.setAttribute("maints", maints);
	pageContext.setAttribute("rooms", rooms);
	pageContext.setAttribute("hotel", hotelName);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Room Maintenance:</title>
</head>
<body>

<form action="availability">
<input type="submit" value="Go Back to Owner Page">
</form>
	<h2>
		Room Statuses:
		<c:out value="${hotel}" />
	</h2>
	<table>
		<tr>
			<th>Room Number</th>
			<th>Status</th>
		</tr>
		<c:forEach items="${rooms}" var="room">
			<tr>
				<td><c:out value="${room}"></c:out></td>
				<td><c:choose>
						<c:when test="${fn:contains(occs, room)}">
							<c:out value="Occupied" />
						</c:when>
						<c:when test="${fn:contains(maints, room)}">
							<c:out value="Under Maintenance" />
						</c:when>
						<c:otherwise>Available
	</c:otherwise>
					</c:choose></td>
			</tr>
		</c:forEach>
	</table>
	
	<h2> Add/Remove Maintenance Status</h2>
	<form name ="form" action="maintenance">
	<c:forEach items="${rooms}" var="room2">
	<c:if test="${!fn:contains(occs, room2)}">
	<input type="checkbox" name="roomRepair" id="roomRepair" VALUE=<c:out value="${room2}"/>><c:out value="${room2}"/><BR>
	</c:if>
	</c:forEach>
	<input type="hidden" name="hidden" value=<c:out value='${hotel}'/>>
  <input type="submit" value="Submit">
</form>	
	
	
  
	
</body>
</html>