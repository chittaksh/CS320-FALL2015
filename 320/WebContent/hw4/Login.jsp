<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*, hw4.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel='stylesheet' type='text/css' href='../MyStyle.css' />
<link rel='stylesheet' href='../bootstrap/Fancy.css' />
<script
    src='../jquery.min.js'></script>
<script
    src='../bootstrap/bootstrap.min.js'></script>
<script>
function clear-form()
{
	document.loginform.email.value = "";
	document.loginform.password.value= "";
	return false;
}
</script>
<title>Login : Lease Management System</title>
</head>
<body class='container'>

	<c:choose>
		<c:when test="${sessionScope.User != null}">
			<c:if test="${sessionScope.User.role == 'Manager'}">
				<c:redirect url="ManagerHome" />
			</c:if>
			<c:if test="${sessionScope.User.role == 'User'}">
				<c:redirect url="LookUpApartment" />
			</c:if>
		</c:when>
	</c:choose>

	<h1>Lease Management System </h1>
	</br>
	</br>
	<h2>Login Page</h2>
	</br>
	</br>

	<%--  Start : Error on Form --%>
	<c:if test="${errorMessage != null}">
		<div class="alert alert-danger" role="alert">
			<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			<span class="sr-only">Error:</span>
			<c:out value="${errorMessage}" />
		</div>
	</c:if>
	<%-- End : Error on Form --%>

	<form method="post" action="Login"
		class="form-horizontal col-sm-offset-4" role="form" name="loginform">
		
		<div class="form-group">
			<label for="email" class="control-label col-sm-2">Email : </label>
			<div class="col-sm-3">
				<input type="email" name="email" id="email" class="form-control"
					required />
			</div>
		</div>
		
		<div class="form-group">
			<label for="password" class="control-label col-sm-2">Password
				: </label>
			<div class="col-sm-3">
				<input type="password" name="password" class="form-control" required />
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-2">
				<input type="submit" value="Submit" class="btn btn-primary"
					name="submit" />
			</div>
			<div class="col-sm-1">
				<button value="Clear" name="clear" type="reset"
					onclick="clear-form()" class="btn">Clear</button>
			</div>
		</div>
		
	</form>

	<a href="RegisterResident">New User? Register Here</a>
</body>
</html>