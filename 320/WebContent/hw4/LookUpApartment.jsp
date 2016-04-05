<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*, hw4.*, model.* , java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Look Up Apartment : Lease Management System</title>
</head>
<body class='container'>

	<c:choose>
		<c:when test="${sessionScope.User != null}">

			<c:if test="${sessionScope.User.role == 'User'}">

				<h1>Lease Management System</h1>
				</br>
				</br>
				<font style='float: right;'>Welcome <c:out
						value="${sessionScope.User.username}" /> ! <a href='Logout'>Logout</a></font>
				</br>
				</br>
				<h2>Look-Up Apartment</h2>
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

				<table class='table table-bordered' style='width: 96%;'>
					<caption class="bg-success MyCaption">Apartment Details</caption>
					<tr>
						<th>Apartment ID</th>
						<th>Apartment Type</th>
						<th>Facility</th>
						<th>Max People</th>
						<th>Rent</th>
						<th class='th'>Deposit</th>
						<th>Appointment Date</th>
						<th>Operation</th>
					</tr>

					<%-- Start : Function for Printing apartments --%>

					<c:forEach items="${requestScope.ApartDetails}" var="AptDetails">
						<tr>
							<td><c:out value="${AptDetails.apartmentNo}" /></td>
							<td><c:out value="${AptDetails.aptType}" /></td>
							<td><c:out value="${AptDetails.facilities}" /></td>
							<td><c:out value="${AptDetails.maxPeople}" /></td>
							<td><c:out value="${AptDetails.rent}" /></td>
							<td><c:out value="${AptDetails.deposit}" /></td>

							<%-- Start : Code to Check for Appointments made by user. --%>

							<c:set var="a" value="true" />
							<c:set var="AppointDet" />
							<c:forEach var="AppointDetail"
								items="${requestScope.AppointDetails}">
								<c:if
									test="${AppointDetail.apartmentDetail.apartmentId == AptDetails.apartmentId}">
									<c:set var="a" value="false" />
									<c:set var="AppointDet" value="${AppointDetail}" />
								</c:if>
							</c:forEach>

							<%-- End : Code to check for Appointments made by user. --%>

							<%-- Start : Apartment is Empty --%>
							<c:if test="${AptDetails.vacant}">
								<c:choose>
									<%-- Start : If Appointment Details are not found --%>
									<c:when test="${a}">
										<td></td>
										<td><a
											href="RequestMapping?aptID=<c:out value="${AptDetails.apartmentId}"/>">Request
												for appointment</a></td>
									</c:when>
									<%-- End : If Appointment Details are not found --%>

									<%-- Start : When appointment dates are found --%>
									<c:when test="${!a}">
										<%-- Start : Conditions for Appointment Date --%>
										<c:choose>

											<c:when test="${AppointDet.appointmentDate == null}">
												<td></td>
											</c:when>

											<c:when test="${AppointDet.appointmentDate !=null }">
												<td><fmt:formatDate type="both"
														pattern="MM/dd/yyyy hh:mm:ss a"
														value="${AppointDet.appointmentDate}" /></td>
											</c:when>

										</c:choose>
										<%-- END : Conditions for Appointment Date --%>

										<%-- Start : Status Details --%>
										<c:choose>

											<c:when test="${AppointDet.appointmentStatus == 'Waiting'}">
												<td><a
													href='?appointID=<c:out value="${AppointDet.appointmentID}"/>'
													class="btn btn-primary">Accept</a> <a
													href='DenyAppointment?appointID=<c:out value="${AppointDet.appointmentID}"/>'
													class='btn'>Deny</a></td>
											</c:when>

											<c:when test="${AppointDet.appointmentStatus != 'Waiting'}">
												<td><c:out value="${AppointDet.appointmentStatus}" /></td>
											</c:when>

										</c:choose>
									</c:when>
									<%-- End : When Appointment dates are found --%>
								</c:choose>
							</c:if>
							<%-- End : Apartment is Empty --%>

						</tr>

					</c:forEach>

					<%-- End : Function for Printing apartments --%>

				</table>
			</c:if>
			<c:if test="${sessionScope.User.role != 'User'}">
				<c:redirect url="Login" />
			</c:if>
		</c:when>
		<c:when test="${sessionScope.User == null}">
			<c:redirect url="Login" />
		</c:when>
	</c:choose>
</body>
</html>