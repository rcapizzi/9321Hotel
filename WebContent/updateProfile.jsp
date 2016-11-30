<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="businessLogic.javaClass.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Profile</title>
 
</head>
<body>
<%User user = (User)request.getAttribute("user"); 
	String email = "";
	if (user.getEmail() != null) email = user.getEmail();
	String nickname = "";
	if (user.getNickname() != null) nickname = user.getNickname();
	String firstname = "";
	if (user.getFirstname() != null) firstname = user.getFirstname();
	String lastname = "";
	if (user.getLastname() != null) lastname = user.getLastname();
	String address = "";
	if (user.getAddress() != null) address = user.getAddress();
	String creditNum = "";
	if (user.getCreditCardNum() != null) creditNum = user.getCreditCardNum();
	String cvv = "";
	if (user.getCreditCardCvv() != null) cvv = user.getCreditCardCvv();
	

%>
<%@ include file="Header.jsp" %>
<form id = "form" action = "submitupdateprofile" onsubmit="return check(this)" method = "POST">
	<input type="hidden" name="user_id" value = "<%=user.getUserId()%>"><br>
	<input type="hidden" name="username" value = "<%=user.getUsername()%>"><br>
	Username:  <%=user.getUsername() %><br>
	Email: <input type="text" name="email" value = "<%=email %>" ><br>
	Nickname: <input type="text" name="nickname" value = "<%=nickname %>"><br>
	Firstname: <input type="text" name="firstname" value = "<%=firstname %>"><br>
	Lastname: <input type="text" name="lastname" value = "<%=lastname %>"><br>
	Address: <input type="text" name="address" value = "<%=address %>"><br><br>
	<p> Paymrnt Detail:</p><br>
	Credit Card Type: 
	<select name="credit_card_type">
		<option value=""> Select </option>
		<option value="mastercard" <%if("mastercard".equals(user.getCreditCardType())){%>selected<%}%>>Mastercard</option>
		<option value="visa" <%if("visa".equals(user.getCreditCardType())){%>selected<%}%>>VISA</option>
		<option value="amex" <%if("amex".equals(user.getCreditCardType())){%>selected<%}%>>American Express</option>
	</select><br>
	Credit card number: <input type="text" name="credit_card_number" value = "<%=creditNum%>"><br>
	Expire Year:
	<select name="credit_card_exp_year">
		<option value=""> Select </option>
		<option value="16" <%if("16".equals(user.getCreditCardExpYear())){%>selected<%}%>>2016</option>
		<option value="17" <%if("17".equals(user.getCreditCardExpYear())){%>selected<%}%>>2017</option>
		<option value="18" <%if("18".equals(user.getCreditCardExpYear())){%>selected<%}%>>2018</option>
		<option value="19" <%if("19".equals(user.getCreditCardExpYear())){%>selected<%}%>>2019</option>
		<option value="20" <%if("20".equals(user.getCreditCardExpYear())){%>selected<%}%>>2020</option>
	</select><br>
	Expire month:
	<select name="credit_card_exp_month">
		<option value=""> Select </option>
		<option value="01" <%if("01".equals(user.getCreditCardExpMonth())){%>selected<%}%>>Jan</option>
		<option value="02" <%if("02".equals(user.getCreditCardExpMonth())){%>selected<%}%>>Feb</option>
		<option value="02" <%if("03".equals(user.getCreditCardExpMonth())){%>selected<%}%>>Mar</option>
		<option value="04" <%if("04".equals(user.getCreditCardExpMonth())){%>selected<%}%>>Apr</option>
		<option value="05" <%if("05".equals(user.getCreditCardExpMonth())){%>selected<%}%>>May</option>
		<option value="06" <%if("06".equals(user.getCreditCardExpMonth())){%>selected<%}%>>Jun</option>
		<option value="07" <%if("07".equals(user.getCreditCardExpMonth())){%>selected<%}%>>Jul</option>
		<option value="08" <%if("08".equals(user.getCreditCardExpMonth())){%>selected<%}%>>Aug</option>
		<option value="09" <%if("09".equals(user.getCreditCardExpMonth())){%>selected<%}%>>Sep</option>
		<option value="10" <%if("10".equals(user.getCreditCardExpMonth())){%>selected<%}%>>Oct</option>
		<option value="11" <%if("11".equals(user.getCreditCardExpMonth())){%>selected<%}%>>Nov</option>
		<option value="12" <%if("12".equals(user.getCreditCardExpMonth())){%>selected<%}%>>Dec</option>
	</select><br>
	CVV: <input type="text" name="credit_card_cvv" value = "<%=cvv%>"><br>
	
	<input type="submit" value="Submit Changes" />
	
	
	
</form>
<script type="text/javascript">  
  
	function check(form)  
	{  
	    if(form.email.value=="")  
	    {  
	        alert("Email cannot be empty!");  
	        return false;  
	    }  
	    if(form.nickname.value=="")  
	    {  
	        alert("Nickname cannot be empty!");  
	        return false;  
	    }  
	    if(form.firstname.value=="")  
	    {  
	        alert("Firstname cannot be empty!");  
	        return false;  
	    } 
	    if(form.lastname.value=="")  
	    {  
	        alert("Lastname cannot be empty!");  
	        return false;  
	    } 
	    if(form.address.value=="")  
	    {  
	        alert("Address cannot be empty!");  
	        return false;  
	    } 
	    if(form.credit_card_type.value=="")  
	    {  
	        alert("Credit card type cannot be empty!");  
	        return false;  
	    } 
	    if(form.credit_card_number.value=="")  
	    {  
	        alert("Credit card number cannot be empty!");  
	        return false;  
	    } 
	    if(form.credit_card_exp_year.value=="")  
	    {  
	        alert("Credit card expire year cannot be empty!");  
	        return false;  
	    } 
	    if(form.credit_card_exp_month.value=="")  
	    {  
	        alert("Credit card expire month cannot be empty!");  
	        return false;  
	    } 
	    if(form.credit_card_cvv.value=="")  
	    {  
	        alert("Credit card CVV month cannot be empty!");  
	        return false;  
	    } 
	    return true;  
	}    
</script> 

</body>
</html>