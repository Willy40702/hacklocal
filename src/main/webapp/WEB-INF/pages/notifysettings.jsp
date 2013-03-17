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
</style>
</head>
<body>
	<h3>Username : ${username}</h3>	
	<h3>This is where you change your notification settings.</h3>
	<c:if test="${not empty success}">
		<div class="errorblock">
			Your changes were saved.
		</div>
	</c:if>

	<form action="setemail" method="GET">
	Update email: <input type="text" name="email"> <br>
	Leave field empty if you wish to remove your email.<br>
	<input type="submit" value="Submit">
	</form>
	<br>
	<form action="setnotify" method="GET">
	I want to receive notifications by email: <input type="checkbox" name="notifyflag"> <br>
	Leave box unchecked if you wish to unsubscribe from email notifications.<br>
	<input type="submit" value="Submit">
	<br>
	<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
	
</body>
</html>