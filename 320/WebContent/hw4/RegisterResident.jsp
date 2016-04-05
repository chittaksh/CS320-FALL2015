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
<title>Register : Lease Management System</title>
<script>
function clear-form()
{
	document.registerform.username.value="";
    document.registerform.email.value = "";
    document.registerform.password.value= "";
    document.registerform.conpassword.value = "";
    document.registerform.conpassword.contact = "";
    document.registerform.conpassword.preferences = "";
    document.registerform.conpassword.needFrom = "";
    var ele = document.registerform.occupation;
    for(var i=0;i<ele.length;i++)
        ele[i].checked = false;
    var ele = document.registerform.apartmentType;
    for(var i=0;i<ele.length;i++)
        ele[i].checked = false;
    return false;
}
</script>
</head>
<body class='container'>

	<c:if test="${session != null}">
		<c:if test="${sessionScope.User.role == 'Manager'}">
			<c:redirect url="ManagerHome" />
		</c:if>
		<c:if test="${sessionScope.User.role == 'User'}">
			<c:redirect url="LookUpApartment" />
		</c:if>
	</c:if>

	<h1>Lease Management System</h1>
	</br>
	</br>
	<h2>Registration Page</h2>
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

	<form method="post" action="RegisterResident" class="form-horizontal"
		role="form" name="registerform">
		<div class="form-group">
			<label for="username" class="control-label col-sm-5">Username
				: </label>
			<div class="col-sm-3">
				<input type='text' name='username' class='form-control' required />
			</div>
		</div>
		<div class="form-group">
			<label for="email" class="control-label col-sm-5">Email ID :</label>
			<div class="col-sm-3">
				<input type='email' name='email' class='form-control' required />
			</div>
		</div>


		<div class="form-group">
			<label for="password" class="control-label col-sm-5">Password
				:</label>
			<div class="col-sm-3">
				<input type='password' name='password' class='form-control' required />
			</div>
		</div>

		<div class="form-group">
			<label for="conpassword" class="control-label col-sm-5">Confirm
				Password :</label>
			<div class="col-sm-3">
				<input type='password' name='conpassword' class='form-control'
					required />
			</div>
		</div>

		<div class="form-group">
			<label for="contact" class="control-label col-sm-5">Contact #
				: (number only)</label>
			<div class="col-sm-3">
				<input type='text' name='contact' class='form-control' required />
			</div>
		</div>

		<div class="form-group">
			<label for="people" class="control-label col-sm-5"># People :
				(number only)</label>
			<div class="col-sm-3">
				<input type='number' name='people' class='form-control' required />
			</div>
		</div>

		<div class="form-group">
			<label for="occupation" class="control-label col-sm-5">Occupation
				: </label>
			<div class="col-sm-3">
				<div style="text-align: left;">
					<%-- Occupations --%>
					<c:forEach items="${applicationScope.Occupation}" var="occu">
						<input type="radio" name="occupation"
							value="<c:out value="${occu}" />" required>
						<c:out value="${occu}" />
						</input>
						</br>
					</c:forEach>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label for="apartmentType" class="control-label col-sm-5">Apartment
				Type : </label>
			<div class="col-sm-3">
				<div style="text-align: left;">
					<input type="radio" name="apartmentType" value="1 BHK" required>
					1 BHK </input> </br> <input type="radio" name="apartmentType"
						value="2 BHK 1 Bath" required> 2 BHK 1 Bath </input> </br> <input
						type="radio" name="apartmentType" value="2 BHK 2 Bath" required>
					2 BHK 2 Bath </input> </br>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label for="preferences" class="control-label col-sm-5">Preferences
				: </label>
			<div class="col-sm-3">
				<textarea type='text' name='preferences' class='form-control'
					required /></textarea>
			</div>
		</div>

		<div class="form-group">
			<label for="needFrom" class="control-label col-sm-5">Need
				From Date : (MM/dd/YYYY hh:mm:ss)</label>
			<div class="col-sm-3">
				<input type='text' name='needFrom' id="needDate"
					class='form-control' required /></input>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-4 col-sm-2">
				<input type="submit" value="Submit" class="btn btn-primary"
					name="submit" />
			</div>
			<div class="col-sm-1">
				<button value="Clear" name="clear" type="reset"
					onclick="clear-form()" class="btn">Clear</button>
			</div>
		</div>
	</form>

	</br>
	</br>
	<a href='Login'>Try Login</a>
</body>
</html>