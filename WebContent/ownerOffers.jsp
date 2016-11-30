<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="businessLogic.javaClass.*"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	ArrayList<Offer> hotelOffers = (ArrayList<Offer>) request.getAttribute("hotelOffers");
pageContext.setAttribute("offers", hotelOffers);
String hotelName = (String) request.getAttribute("hotelName");
pageContext.setAttribute("hotel", hotelName);
%>
<html>
<head>
<script type="text/javascript">
function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode != 46 &&(charCode < 48 || charCode > 57)))
        return false;
    return true;
}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Special Offers</title>
</head>
<body>
<form action="availability">
<input type="submit" value="Go Back to Owner Page">
</form>

	<h1>
		Special Offers:
		<c:out value="${hotel}" />
	</h1>

	<h2>Create Offer</h2>
	<form action="offers">
		<INPUT TYPE="radio" NAME="roomType" VALUE="Single" CHECKED>Single<BR> 
		<INPUT TYPE="radio" NAME="roomType" VALUE="Twin Bed">Twin Bed<BR> 
		<INPUT TYPE="radio" NAME="roomType" VALUE="Queen">Queen<BR> 
		<INPUT TYPE="radio" NAME="roomType" VALUE="Executive">Executive<BR> 
		<INPUT TYPE="radio" NAME="roomType" VALUE="Suite">Suite<BR>
		Discount Percentage <input name="price" type="text" onkeypress="return isNumberKey(event)" maxlength="2" /><BR>
		Start Date <input type="date" name="startDate"><BR> 
		End	Date <input type="date" name="endDate"><BR> 
		<input type="hidden" name="hidden" value="<c:out value='${hotel}'/>">
		<input type="submit" value="Create Offer">
	</form>
	

	<c:if test="${hotelOffers.size() > 0}">
	<h2>Remove Offer </h2>
		<form action="deleteoffers">
			<table>
				<tr>
					<th>Room Type</th>
					<th>Discount</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Select</th>
				</tr>
				<c:forEach items="${offers}" var ="offer">
				<tr>
					<td><c:out value="${offer.getRoom_type()}"/></td>	
					<td><fmt:formatNumber value="${offer.getDiscount() * 100}" maxFractionDigits="0" />%</td>
					<td><c:out value="${offer.getStartDate()}"/></td>
					<td><c:out value="${offer.getEndDate()}"/></td>
					<td><INPUT TYPE="radio" NAME="roomType" VALUE="<c:out value="${offer.getRoom_type()}"/>" CHECKED></td>
					</tr>
				</c:forEach>	
				
			</table>
			<input type="hidden" name="hidden" value="<c:out value='${hotel}'/>">
			<input type="submit" value="Remove Offer">
		</form>
	</c:if>




</body>
</html>