<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*, hw4.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<title>View Apartment : Lease Management System</title>
</head>
<body class='container'>
	<c:choose>
		<c:when test="${!pageContext.session['new']}">
			<c:if test="${sessionScope.User.role == 'Manager'}">
				<h1>Lease Management System</h1>
				</br>
				</br>
				<a href='Login' class='Left'>Home</a>
				<font style='float: right;'>Welcome <c:out
						value="${sessionScope.User.username}" /> ! <a href='Logout'>Logout</a></font>
				</br>
				</br>
				<h2>View Apartments</h2>
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
					<table class='table-bordered table'
						style='width: 90%; margin: auto;'>
						<caption class="bg-success MyCaption">Apartment Details</caption>
						<tr>
							<th>Apartment #</th>
							<th>Apartment Type</th>
							<th>Facility</th>
							<th>Max People</th>
							<th>Rent</th>
							<th>Deposit</th>
							<th>Vacant</th>
							<th>Change Availablity</th>
							<th>Appointments</th>
							<th>Appointment History</th>
						</tr>

						<c:forEach items="${requestScope.ApartDetails}" var="AptDetails">

							<tr>
								<td><c:out value="${AptDetails.apartmentNo}" /></td>
								<td><c:out value="${AptDetails.aptType}" /></td>
								<td><c:out value="${AptDetails.facilities}" /></td>
								<td><c:out value="${AptDetails.maxPeople}" /></td>
								<td><c:out value="${AptDetails.rent}" /></td>
								<td><c:out value="${AptDetails.deposit}" /></td>

								<c:set var="a" value="0" />
								<c:set var="b" value="0" />
								<c:forEach items="${requestScope.AppointmentDetails}"
									var="AppointDetails">
									<c:if
										test="${AppointDetails.apartmentDetail.apartmentId == AptDetails.apartmentId}">
										<c:set var="a" value="${a+1 }" />
									</c:if>
									
								</c:forEach>
								
								<c:forEach items="${requestScope.AppointmentHistory}"
                                    var="AppointHistory">
								<c:if
                                        test="${AppointHistory.apartmentDetail.apartmentId == AptDetails.apartmentId}">
                                        <c:set var="b" value="${b+1 }" />
                                    </c:if>
                                </c:forEach>

								<c:choose>
									<%-- Start : Apartment is Empty --%>
									<c:when test="${AptDetails.vacant}">
										<td>Yes</td>
										<td></td>

										<%-- Start : If Appointments then View --%>
										<td><c:choose>
												<c:when test="${a>0}">
													<a
														href='RescheduleApplication?aptID=<c:out value="${AptDetails.apartmentId}" />'>View</a>

												</c:when>
												<c:when test="${a==0}">
                                                 No Appointments.
                                                </c:when>
											</c:choose></td>
										<%-- End : If Appointments then View --%>
									</c:when>
									<%-- End : Apartment is Empty --%>

									<%-- Start : Apartment is not Empty --%>
									<c:when test="${!AptDetails.vacant}">
										<td>No</td>
										<td><a
											href='?aptID=<c:out value="${AptDetails.apartmentId}" />'>Make
												Available</a></td>
										<td></td>
									</c:when>
									<%-- End : Apartment is not Empty --%>
								</c:choose>

								<%-- Start : If Appointments then View --%>
								<td><c:choose>
										<c:when test="${b>0}">
											<a
												href='AppointmentHistory?aptID=<c:out value="${AptDetails.apartmentId}" />'>View</a>

										</c:when>
										<c:when test="${b==0}">
                                                 No History.
                                                </c:when>
									</c:choose></td>
								<%-- End : If Appointments then View --%>
							</tr>
						</c:forEach>
					</table>
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