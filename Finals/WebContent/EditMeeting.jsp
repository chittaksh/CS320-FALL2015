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

	<h1>Edit Meeting</h1>
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

	<form method="post" action="EditMeeting" class="form-horizontal"
		role="form">

		<div class="col-sm-12">
		<c:forEach items="${requestScope.Meetings}" var="meeting">
			<c:if test="${meeting.id==requestScope.meetID}">

				<input type="hidden" name="meetID" value="${meeting.id}" />
				<div class="form-group">
					<label for="day" class="control-label col-sm-3">Day : </label>
					<div class="col-sm-3">
						<select name="day" class="form-control">
							<c:forEach items="${requestScope.Days}" var="day">
								<option value="${day.id}"
									${day.id==meeting.dayID ? 'selected':''}>${day.dayName}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="time" class="control-label col-sm-3">Time : </label>
					<div class="col-sm-3">
						<select name="time" class="form-control">
							<c:forEach items="${requestScope.TimeSlots}" var="time">
								<option value="${time.id}"
									${time.id==meeting.timeID ? 'selected':''}>${time.timeSlot}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="note" class="control-label col-sm-3">Note </label>
					<div class="col-sm-3">
						<input type="text" name="note" id="note" class="form-control"
							value="${meeting.notes}" required />
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-1">
						<input type="submit" value="Edit" class="btn btn-primary"
							name="Edit" />
					</div>
					<div class="col-sm-2">
						<input type="submit" value="Delete" class="btn btn-primary"
							name="Delete" />
					</div>
					<div class="col-sm-1">
						<button value="Clear" type="reset" class="btn">Clear</button>
					</div>
				</div>
			</c:if>
		</c:forEach>
		</div>
	</form>


</body>
</html>