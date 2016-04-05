<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*, model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel='stylesheet' type='text/css' href='MyStyle.css' />
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css' />
<script
	src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js'></script>
<script
	src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>
<title>Final - Exam</title>
</head>
<body class="container">

	<h1>Add Time Slot</h1>
	</br>
	</br>
	<a href='Final' class='Left'>Home</a>

	<%--  Start : Error on Form --%>
	<c:if test="${errorMessage != null}">
		<div class="alert alert-danger" role="alert">
			<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			<span class="sr-only">Error:</span>
			<c:out value="${errorMessage}" />
		</div>
	</c:if>
	<%-- End : Error on Form --%>

	<form method="post" action="AddTimeSlot" class="form-horizontal"
		role="form">

		<div class="col-sm-12">
			<div class="form-group">
				<label for="time" class="control-label col-sm-3">Timespan :
				</label>
				<div class="col-sm-3">
					<input type="text" name="time" id="time" class="form-control"
						required />
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-2">
					<input type="submit" value="Submit" class="btn btn-primary"
						name="submit" />
				</div>
				<div class="col-sm-1">
					<button value="Clear" type="reset" class="btn">Clear</button>
				</div>
			</div>
		</div>
	</form>

</body>
</html>