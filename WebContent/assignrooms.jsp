<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manager Interface: Assign rooms</title>
</head>
<body>

   <h2>Booking for ${assign_user}</h2>
   <em>${assign_message}</em>

   <%-- If it's success, show nothing but the success message --%>
   <%
      if (request.getAttribute("assign_succeeded") == null) {
   %>
   <form action="assignrooms" method="POST">
      <c:forEach var="type" items="${assign_requested_types}">
         <h3>Available ${type.typeName} rooms (Customer requested
            ${type.numberRequested})</h3>
         <table>
            <tr>
               <!-- Room number, Room description, Choose N -->
               <th>Room number</th>
               <th>Room description</th>
               <th>Choose ${type.numberRequested}</th>
            </tr>
            <c:forEach var="room" items="${type.availableRooms}">
               <tr>
                  <td>${room.roomNo}</td>
                  <td>${room.roomDescription}</td>
                  <td><input type="checkbox" name="assign_rooms"
                     value='${room.roomId}' /></td>
               </tr>
            </c:forEach>
         </table>
      </c:forEach>
      <input type="hidden" name="assign_setting_rooms" /> <input
         type="submit" value="Confirm selected rooms" />
   </form>
   <%
      }
   %>

   <p>
      <a href="manage">Back to Manager Home</a>
   </p>
   <form action="staff" method="POST">
      <input type="submit" name="staff_logout" value="Log out" />
   </form>


</body>
</html>