<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="businessLogic.javaClass.Offer" %>
<%@ page import="businessLogic.javaClass.Period" %>
<%@  taglib  prefix="c"   uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	HashMap<String, Integer> occs = (HashMap<String, Integer>) request.getAttribute("occupancies"); 
	pageContext.setAttribute("occupancies", occs);
	ArrayList<Offer> offers = (ArrayList<Offer>) request.getAttribute("offers");
	pageContext.setAttribute("offers", offers);
	ArrayList<Period> periods = (ArrayList<Period>) request.getAttribute("periods");
	pageContext.setAttribute("periods", periods);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Owner Page</title>
</head>
<body>

<h2> Current Room Availabilities</h2>
 <table>
	<tr>
		<th>Hotel</th>
		<th>Occupied/Maintenance</th>
		<th>Available Rooms</th>
	</tr>
  <c:forEach items="${occupancies}" var="info">
    <tr>
      <td><c:out value="${info.getKey()}" /></td>
      <td><c:out value="${info.getValue()}" /></td>
      <td><c:out value="${8 - info.getValue()}" /></td>
    </tr>
    </c:forEach>
</table>

<h2>Manage Room Maintenance</h2>
<form action="maintenance">
  <select name="hotel">
    <option value="1">Rampage-SYD-1</option>
    <option value="2">Rampage-SYD-2</option>
    <option value="3">Rampage-MEL-1</option>
    <option value="4">Rampage-MEL-2</option>
    <option value="5">Rampage-BRI-1</option>
    <option value="6">Rampage-ADE-1</option>
    <option value="7">Rampage-PER-1</option>
    <option value="8">Rampage-HOB-1</option>
  </select>
  <input type="submit" value="Submit">
</form>

<h2> Current Special Offers</h2>
<c:if test="${offers.size() < 1}">
	<h4>There are no offers at the moment.</h4>
	</c:if>
<c:if test="${offers.size() > 0}">
	<table>
		<tr>
			<th>Hotel</th>
			<th>Room Type</th>
			<th>Discount</th>
			<th>Start Date</th>
			<th>End Date</th>
		</tr>
	<c:forEach items="${offers}" var ="offer">	
		<tr>
			<td><c:out value="${offer.getHotel_name()}" /></td>
			<td><c:out value="${offer.getRoom_type()}" /></td>
			<td><fmt:formatNumber value="${offer.getDiscount() * 100}" maxFractionDigits="0" />%</td>
			<td><c:out value="${offer.getStartDate()}" /></td>
			<td><c:out value="${offer.getEndDate()}" /></td>
		</tr>
	</c:forEach>
	</table>
	</c:if>
	
	


	<h2>Manage Special Offers</h2>
<form action="offers">
  <select name="offerHotel">
    <option value="1">Rampage-SYD-1</option>
    <option value="2">Rampage-SYD-2</option>
    <option value="3">Rampage-MEL-1</option>
    <option value="4">Rampage-MEL-2</option>
    <option value="5">Rampage-BRI-1</option>
    <option value="6">Rampage-ADE-1</option>
    <option value="7">Rampage-PER-1</option>
    <option value="8">Rampage-HOB-1</option>
  </select>
  <input type="submit" value="Submit">
</form>


   
   <h2>Scheduled Peak Periods</h2>
   <c:if test="${periods.size() < 1}">
	<h4>There are no scheduled periods at the moment.</h4>
	</c:if>
	<c:if test="${periods.size() > 0}">
	<table>
		<tr>
			<th>Name</th>
			<th>Price Increase</th>
			<th>Start Date</th>
			<th>End Date</th>
		</tr>
	<c:forEach items="${periods}" var ="period">
	<tr>
	<td><c:out value="${period.getPeriodName()}" /></td>
	<td><c:out value="${period.getPriceIncrease()}" />%</td>
	<td><c:out value="${period.getStartDate()}" /></td>
	<td><c:out value="${period.getEndDate()}" /></td>
	</tr>
	</c:forEach>
	</table>		
	</c:if>
	
	<form action="ownerPeriod.jsp">
	<input type="submit" value="Manage Peak Periods" />
	</form>
	
	
	   <form action="staff" method="POST">
      <input type="submit" name="staff_logout" value="Log out" />
   </form>
   


</body>
</html>