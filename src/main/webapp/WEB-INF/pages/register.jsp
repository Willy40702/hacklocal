<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Register Page</title>
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
<body onload='document.f.j_username.focus();'>
	<h3>Register with Username and Password (Authentication with Database)</h3>

	<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST'>

		<table>
			<tr>
				<td>User name:</td>
				<td><input type='text' name='username' value=''>
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='text' name='password' />
				</td>
			</tr>
			<tr>
				<td>AgentID:</td>
				<td><input type='text' name='id' />
				</td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="submit" />
				</td>
			</tr>

		</table>

	</form>
	<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
</body>
</html>