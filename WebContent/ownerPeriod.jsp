<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="businessLogic.javaClass.*"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 	ArrayList<Period> periods = (ArrayList<Period>) request.getAttribute("periods");
	pageContext.setAttribute("periods", periods);%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<title>Peak Periods</title>
</head>
<body>
<form action="availability">
<input type="submit" value="Go Back to Owner Page">
</form>
<h2>Set New Peak Period</h2>

	<form action="peakperiods">
	Period Name<input name="pName" type="text"><BR>
	Increase Percentage<input name="pIncrease" type="text" onkeypress="return isNumberKey(event)" maxlength="2" /><BR>
	Start Date<input type="date" name="pStart"><BR> 
	End Date<input type="date" name="pEnd"><BR> 
	<input type="submit" value="Set Period">
	</form>

<h2>Remove Peak Period </h2>
<form action="peakperiods">
Enter Period Name:<input name="removeName" type="text"><BR>
<input type="submit" value="Remove Period">
</form>

	
</body>
</html>