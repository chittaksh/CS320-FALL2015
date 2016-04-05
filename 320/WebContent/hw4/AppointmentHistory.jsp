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
				<a href='Login' class='Left'>Home</a>
				<font style='float: right;'>Welcome <c:out
						value="${sessionScope.User.username}" /> ! <a href='Logout'>Logout</a></font>
				</br>
				</br>
				<h2>Rent History</h2>
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
						style='width: 40%; margin: auto; margin-bottom: 50px;'>
						<caption class="bg-success MyCaption">Apartment Details</caption>
						<%-- Start : Code to Display Apartment Details --%>
						<c:set value="${requestScope.AppointDetails[0].apartmentDetail}"
							var="AptDetails" />
						<tr>
							<td>Apartment No :</td>
							<td><c:out value="${AptDetails.apartmentNo}" /></td>
						</tr>
						<tr>
							<td>Type :</td>
							<td><c:out value="${AptDetails.aptType}" /></td>
						</tr>
						<tr>
							<td>Facilities :</td>
							<td><c:out value="${AptDetails.facilities}" /></td>
						</tr>
						<tr>
							<td>Maximum People :</td>
							<td><c:out value="${AptDetails.maxPeople}" /></td>
						</tr>
						<tr>
							<td>Rent :</td>
							<td><c:out value="${AptDetails.rent}" /></td>
						</tr>
						<tr>
							<td>Deposit :</td>
							<td><c:out value="${AptDetails.deposit}" /></td>
						</tr>
						<%-- End : Code to Display Apartment Details --%>

					</table>
				</div>

				<table class='table-bordered table'
					style='width: 80%; margin: auto;'>
					<caption class="bg-success MyCaption">Appointment Details</caption>
					<tr>
						<th style="min-width: 5px;">App. #</th>
						<th>Applicant</th>
						<th>Contact #</th>
						<th style="min-width: 5px;"># Ppl</th>
						<th>Occupation</th>
						<th style="min-width: 100px;">Preferences</th>
						<th style="min-width: 100px;">Need From</th>
						<th style="min-width: 100px;">Appointment Date</th>
						<th style="min-width: 80px;">Status</th>
						<th style="min-width: 100px;">Ruled Out Dates</th>
						<th style="min-width: 70px;">Rent Out</th>
					</tr>
					<%-- Start : Code for Appointment requests. --%>
					<c:forEach items="${requestScope.AppointDetails}"
						var="AppointDetails">
						<tr>
							<td><c:out value="${AppointDetails.appointmentID}" /></td>
							<td><c:out value="${AppointDetails.userDetail.username}" /></td>
							<td><c:out value="${AppointDetails.userDetail.contact}" /></td>
							<td><c:out value="${AppointDetails.userDetail.noOfPeople}" /></td>
							<td><c:out value="${AppointDetails.userDetail.occupation}" /></td>
							<td><c:out value="${AppointDetails.userDetail.preferences}" /></td>
							<td><fmt:formatDate type="both"
									pattern="MM/dd/yyyy hh:mm:ss a"
									value="${AppointDetails.userDetail.needFrom}" /></td>

							<%-- Start : Appointment Date --%>
							<c:choose>
								<c:when test="${AppointDetails.appointmentDate != null}">
									<td><fmt:formatDate type="both"
											pattern="MM/dd/yyyy hh:mm:ss a"
											value="${AppointDetails.appointmentDate}" /></td>
								</c:when>
								<c:when test="${AppointDetails.appointmentDate == null}">
									<td></td>
								</c:when>
							</c:choose>
							<%-- End : Appointment Date --%>

							<td><c:out value="${AppointDetails.appointmentStatus}" /></td>

							<%-- Start : Ruled Out Dates --%>
							<td><c:if test="${AppointDetails.ruledOutDates != null}">
									<c:forEach items="${AppointDetails.ruledOutDates}"
										var="RuledOut">
										<fmt:formatDate type="both" pattern="MM/dd/yyyy hh:mm:ss a"
											value="${RuledOut}" />
										</br>
									</c:forEach>
								</c:if></td>
							<%-- End : Ruled Out Dates --%>

							<%-- Start : Rent Out Link --%>
							<c:choose>
								<c:when test="${AppointDetails.appointmentStatus == 'Accepted'}">
									<td><a
										href='RentOut?AppID=<c:out value="${AppointDetails.appointmentID}" />'>Rent</a></td>
								</c:when>

								<c:when test="${AppointDetails.appointmentStatus != 'Accepted'}">
									<td></td>
								</c:when>
							</c:choose>
							<%-- End : Rent Out Link --%>
						</tr>
					</c:forEach>
					<%-- End : Code for Appointment List --%>
				</table>

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