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
<!DOCTYPE html>
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
<title>Search Result</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<%
	ArrayList<Search> rooms = (ArrayList)request.getSession().getAttribute("roomResult");
	if(rooms.size()==0){%>
	<p>Sorry, no matching data was found!</p>
	<%}else{ %>
	
	<form action ="booking" method="post">
	<table border=1>
	<tr>
	<td>
	<p>Hotel</p>
	</td>
	<td>
	<p>Room Type</p>
	</td>
	<td>
	<p>Normal Price/Per night</p>
	</td>
	<td>
	<p>Current Price/Per night</p>
	</td>
	<td>
	<p>Number of Rooms</p>
	</td>
	<td>
	<p>Room to Book</p>
	</td>
	<td>
	<p>Extra Bed(S)</p>
	</td>
	
	</tr>
	<%
	for (int i =0;i<rooms.size();i++){
		//int this_room = rooms.get(i).getRoomId();
	
	%>
	<tr>
	<td>
	<p><%=rooms.get(i).getHotel_id()%></p>
	<input type="hidden" name="hotelid" value=<%=rooms.get(i).getHotel_id()%>>
	</td>
	
	<td>
	<p><%=rooms.get(i).getRoomtype() %></p>
	<input type="hidden" name="roomtype" value=<%=rooms.get(i).getRoomtype()%>>
	</td>
	<td>
	<p><%=rooms.get(i).getPrice() %></p>
	<input type="hidden" name="price" value=<%=rooms.get(i).getPrice()%>>
	</td>
	<td>
	<p><%=rooms.get(i).getCurrentPrice()%></p>
	</td>
	<td>
	<p><%=rooms.get(i).getNo()%></p> 
	</td>
	
	<td>
	<input type="textbox" name="number_of_room" value="0" onkeypress="return isNumberKey(event)" maxlength="2">
	</td>
	<td>
	<input type="textbox" name="extrabed" value="0" onkeypress="return isNumberKey(event)" maxlength="1">
	</td>
	</tr>
	<%}%>
	</table>
	<input type="submit" value="Add to Cart"> 
	
	</form>

	<form action='search' method='POST'>
		<table>
		<tr>
			<td align="left">Check in time : </td> <td align="left"><input type="date" name="check_in" /></td>
		</tr>
		<tr>
			<td align="left">Check out time : </td> <td align="left"><input type="date" name="check_out" /></td>
		</tr>
		<tr>
			<td align="left">City : </td>
			<td><select name ="city">
  					<option value="Sydney">Sydney</option>
  					<option value="Melbourne">Melbourne</option>
  					<option value="Adelaide">Adelaide</option>
  					<option value="Brisbane">Brisbane</option>
				</select></td>
		</tr>
		<tr>
			<td align="left">Price(Less Than) : </td> <td align="left"><input type="text" name="price" /></td>
		</tr>
		</table>
		<p></p>
		<input type='submit' value='Search' class="button">
		
		<br>
		</form>
<%}%>


</body>
</html>