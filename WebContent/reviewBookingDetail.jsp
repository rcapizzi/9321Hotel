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
<title>Insert title here</title>
</head>
<body>
<H2>My Shopping Cart</H2>
    <% 
    double totalprice = 0;
   	ArrayList<Booking> list = (ArrayList)request.getSession().getAttribute("review_booking");
   	  if(list.size() == 0) {
    %>
        <p>Empty!</p>
    <%}
   	  else{%>
   	  
   	<table border =1>  
   		<form action=shoppingcart method="get">
   			<tr>
    <td>
    </td>
    <td>
    <p>No of room</p>
    </td>
    <td>
    <p>Hotel</p>
    </td>
    <td>
    <p>Check_in</p>
    </td>
    <td>
    <p>Check_out</p>
    </td>
    <td>
    <p>Extra Beds</p>
    </td>
    </tr>
    <%
    for(int i = 0;i < list.size();i++) {
    	String hotel_id =""+list.get(i).getHotel_id();
    	String numberofroom = ""+list.get(i).getNo_of_room();
    	%>
    <tr>
    <td>
    <input type="checkbox" name="del" value="<%=i%>">
    </td>
    <td>
    <p><%=numberofroom%></p>
    </td>
    <td>
    <p><%=hotel_id%></p>
    </td>
    <td>
    <p><%=list.get(i).getStartDate()%></p>
    </td>
    <td>
    <p><%=list.get(i).getEndDate()%></p>
    </td>
    <td>
    <p><%=list.get(i).getExtrabed()%></p>
    </td>
    </tr>
	<%}%>
	 
	 </form>
	
	</table>
	<%}%>
   
</body>
</html>