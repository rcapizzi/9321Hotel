<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<title>Search</title>
</head>
<body>
<%@ include file="Header.jsp" %>
	    <h2>Search for rooms</h2>
		Search by:
		<br> <br>
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
			<td align="left">Price(Less Than) : </td> <td align="left"><input name="price" type="text" onkeypress="return isNumberKey(event)" maxlength="4" />

		</tr>
		</table>
		<p></p>
		<input type='submit' value='Search' class="button">
		</form>
		<br>
</body>
</html>