
	<%if (request.getSession().getAttribute("current_user_id") == null) {%>
		<p>Please login in or sign up! </p>
			<form action = "login.jsp">
				<input type="submit"  value="Login" class="button"/>	
			</form>
			<form action = "signup.jsp">
				<input type="submit"  value="Sign up" class="button"/>	
			</form><br>
	<%} else {%>
		<p>Hi <%=(String)request.getSession().getAttribute("current_user")%>:</p>
		<form action = "update">
			<input type = "hidden" name = "user_id" value =<%=request.getSession().getAttribute("current_user_id")%>>
			<input type="submit"  value="Update profile" class="button"/>	
		</form>
		<form action = "shoppingcart" method = "POST">
			<input type = "hidden" name = "user_id" value =<%=request.getSession().getAttribute("current_user_id")%>>
			<input type="submit"  value="View shoppingcart" class="button"/>	
		</form><br>
      <form action="logout.jsp" method="POST">
         <input type="submit" name="logout" value="Log out" /> <%-- can be same logout, session stuff --%>
      </form>

	<%} %>
	
	