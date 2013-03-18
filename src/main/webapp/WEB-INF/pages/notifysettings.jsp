<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Notification Change Page</title>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
body {background: url('C:\Users\Wiliam Hsiao\git\hacklocal\BCCA_Logo.gif') no-repeat top right fixed;}
</style>
</head>
<body>
	<h3>Username : ${username}</h3>	
	<h3>This is where you change your notification settings.</h3>
	
	<form action="setnotify">
	I want to receive notifications by email: <input type="checkbox" name="notifyflag"> <br>
	<input type="submit" value="Submit">
	</form>
	
	<form action="setemail">
	Update email: <input type="text" name="email"> <br>
	<input type="submit" value="Submit">
	</form>
	
	<%
   String strMasjidLocation = "Selimiyie Masjid Methuen";
   session.setAttribute("MasjidLocation", strMasjidLocation);
   %>
	
	<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
	
</body>
</html>