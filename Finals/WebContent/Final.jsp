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

	<h1>Home Page</h1>

	<%--  Start : Error on Form --%>
	<c:if test="${errorMessage != null}">
		<div class="alert alert-danger" role="alert">
			<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			<span class="sr-only">Error:</span>
			<c:out value="${errorMessage}" />
		</div>
	</c:if>
	<%-- End : Error on Form --%>

	<a href="AddTimeSlot" style="margin: 10px">Add A Time Slot</a> |
	<a href="AddMeeting" style="margin: 10px">Add Meeting</a>
	</br>
	<table style="width: 90%; margin: auto; margin-bottom: 50px;"
		class="table-bordered table">
		<caption class="bg-success MyCaption">Meeting Calender</caption>
		<tr>
			<th></th>
			<c:forEach items="${requestScope.Days}" var="day">
				<th>${day.dayName}</th>
			</c:forEach>
		</tr>

		<c:forEach items="${requestScope.TimeSlots}" var="time">
			<tr>
				<th>${time.timeSlot}</th>
				<c:forEach items="${requestScope.Days}" var="day">
					<td><c:forEach items="${requestScope.Meetings}" var="meeting">
							<c:if
								test="${day.id == meeting.dayID && time.id == meeting.timeID}">                
                        ${meeting.notes}
                        <a href="EditMeeting?meetID=${meeting.id}">Edit</a>
							</c:if>
						</c:forEach></td>
				</c:forEach>
			</tr>
		</c:forEach>

	</table>

</body>
</html>