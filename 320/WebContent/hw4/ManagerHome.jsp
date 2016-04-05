<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*, hw4.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>Manager Home : Lease Management System</title>
</head>
<body class='container'>
	<c:choose>
		<c:when test="${!pageContext.session['new']}">
			<c:if test="${sessionScope.User.role == 'Manager'}">
				<h1>Lease Management System</h1>
				</br>
				</br>

				<font style='float: right;'>Welcome <c:out
						value="${sessionScope.User.username}" /> ! <a href='Logout'>Logout</a></font>
				</br>
				</br>
				<h2>Dash Board</h2>
				</br>
				</br>

				<%--  Start : Error on Form --%>
				<c:if test="${errorMessage != null}">
					<div class="alert alert-danger" role="alert">
						<span class="glyphicon glyphicon-exclamation-sign"
							aria-hidden="true"></span> <span class="sr-only">Error:</span>
						<c:out value="${errorMessage}" />
					</div>
				</c:if>
				<%-- End : Error on Form --%>

                <div class="Left"
                    style="width: 15%; display: block; min-height: 450px;">
                    <ul style='width: 100%; list-style-type: none;'>
                        <li><a href='AddApartment'>Add Apartment</a></li>
                        <li><a href='ViewApartment'>View Apartment</a></li>
                        <li><a href='RentHistory'>Rent History</a></li>
                        <li><a href='PayRent'>Pay Rent</a></li>
                        <li><a href='Reports'>Reports</a>
                    </ul>
                </div>

				<div class="Right" style="width: 85%">
				
					<%-- Start : Apartments Table --%>
					<c:set var="i" value="0" />
                    <c:set var="j" value="0" />
                    <c:forEach items="${requestScope.ApartmentDetails}"
                        var="AptDetails">
                        <c:if test="${AptDetails.vacant}">
                            <c:set var="i" value="${i+1}" />
                        </c:if>
                        <c:if test="${!AptDetails.vacant}">
                            <c:set var="j" value="${j+1}" />
                        </c:if>
                    </c:forEach>
                    
					<table style="width: 90%; margin: auto; margin-bottom: 50px;"
						class="table-bordered table">
						<caption class="bg-success MyCaption">Apartments</caption>
						<tr>
							<th>Vacant Apartments</th>
							<th>Leased Apartments</th>
							<th>Total Apartments</th>
						</tr>
						<tr>
							<td><c:out value="${i}" /></td>
							<td><c:out value="${j}" /></td>
							<td><c:out value="${fn:length(ApartmentDetails)}" /></td>
						</tr>
					</table>
					<%-- End : Apartments Table --%>

					<%-- Start : Appointment Request Table --%>
					<c:set var="p" value="0" />
                    <c:set var="q" value="0" />
                    <c:set var="r" value="0" />
                    <c:set var="s" value="0" />
                    <c:forEach items="${requestScope.AppointmentDetails}"
                        var="AppointDetails">
                        <c:choose>
                            <c:when test="${AppointDetails.appointmentStatus == 'Requested'}">
                                <c:set var="p" value="${p+1}" />
                            </c:when>
                            <c:when test="${AppointDetails.appointmentStatus == 'Waiting'}">
                                <c:set var="q" value="${q+1}" />
                            </c:when>
                            <c:when test="${AppointDetails.appointmentStatus == 'Accepted'}">
                                <c:set var="r" value="${r+1}" />
                            </c:when>
                            <c:when test="${AppointDetails.appointmentStatus == 'Denied'}">
                                <c:set var="s" value="${s+1}" />
                            </c:when>
                        </c:choose>
                    </c:forEach>
                    
					<table style="width: 40%; margin-left: 5%;"
						class="table-bordered table Left">
						<caption class="bg-success MyCaption">Applications</caption>
						<tr>
							<th>Appointment Request Pending</th>
							<td><c:out value="${p}" /></td>
						<tr>
						<tr>
							<th>Appointments Waiting</th>
							<td><c:out value="${q}" /></td>
						<tr>
						<tr>
							<th>Appointments Accepted</th>
							<td><c:out value="${r}" /></td>
						<tr>
						<tr>
							<th>Appointments Denied</th>
							<td><c:out value="${s}" /></td>
						<tr>
					</table>
					<%-- End : Appointment Request Table --%>

					<%-- Start : Today's Appointments --%>
					<c:set var="z" value="0" />
					<fmt:formatDate value="<%=new java.util.Date()%>" var="todayDate"
						type="date" pattern="MM/dd/yyyy" />
					<table style="width: 40%; margin-right: 5%"
						class="table-bordered table Right">
						<caption class="bg-success MyCaption">Today's
							Appointments</caption>
						<tr>
							<th>Apt. #</th>
							<th>Username</th>
							<th>Time</th>
						</tr>

						<c:forEach items="${requestScope.AppointmentDetails}"
							var="AppointDetails">
							<fmt:formatDate value="${AppointDetails.appointmentDate}"
								var="AppointDate" type="date" pattern="MM/dd/yyyy" />
							<c:if
								test="${AppointDate == todayDate && AppointDetails.appointmentStatus == 'Accepted'}">
								<tr>
									<td><c:out
											value="${AppointDetails.apartmentDetail.apartmentNo}" /></td>
									<td><c:out value="${AppointDetails.userDetail.username}" /></td>
									<td><fmt:formatDate type="time" pattern="hh:mm:ss a"
											value="${AppointDetails.appointmentDate}" /></td>
								</tr>
							</c:if>
						</c:forEach>

					</table>
					<%-- End Today's Appointments --%>
				</div>


			</c:if>
			<c:if test="${sessionScope.User.role != 'Manager'}">
				<c:redirect url="Login" />
			</c:if>
		</c:when>
		<c:when test="${pageContext.session['new']}">
			<c:redirect url="Login" />
		</c:when>
	</c:choose>

</body>
</html>