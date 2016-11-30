<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Staff login</title>
</head>
<body>
   <h2>Staff login</h2>
   <em>${staff_error}</em>
   <form action = "staff" method = "POST">
      <table>

           <tr>
               <td><label for="txtname">Staff Username:</label></td>
               <td><input type="text" id="txtname" name="staff_username" /></td>
           </tr>
           <tr>
               <td><label for="txtpswd">Password:</label></td>
               <td><input type="password" id="txtpswd" name="staff_pswd" /></td>
           </tr>
           <tr>
               <td colspan=2>
                   <input type="submit" name="staff_login" value = "Login"/>
               </td>
           </tr>
       </table>
   </form>
   <br>
</body>
</html>