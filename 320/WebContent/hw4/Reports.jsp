<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*, hw4.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Reports : Lease Management System</title>
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
				<h2>Reports</h2>
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

					<form method="post" action="Reports" style="margin-bottom: 50px;"
						class="form-horizontal col-md-offset-3" role="form">

						<div class="form-group">
							<label for="rentMonth" class="form-label col-md-2">Month
								:</label>
							<div class="col-sm-4">
								<select class="form-control" id="rentMonth" name="rentMonth">
									<c:forEach items="${applicationScope.Months}" var="month">
										<option value="${month}" ${selMonth==month ? 'selected':'' }>
											<c:out value="${month}" />
										</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="form-label col-md-2">Year :</label>
							<div class="col-sm-4">
								<select class="form-control" id="rentYear" name="rentYear">
									<option value="115" ${selYear==115 ? 'selected':'' }>2015</option>
									<option value="116" ${selYear==116 ? 'selected':'' }>2016</option>
									<option value="117" ${selYear==117 ? 'selected':'' }>2017</option>
									<option value="118" ${selYear==118 ? 'selected':'' }>2018</option>
									<option value="119" ${selYear==119 ? 'selected':'' }>2019</option>
									<option value="120" ${selYear==120 ? 'selected':'' }>2020</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<input type="submit"
								class="btn btn-primary col-md-3 col-md-offset-1" id="submit"
								value="Submit" />
						</div>
					</form>

					<%-- Start : Apartments Table --%>
					<c:set var="i" value="0" />
					<c:set var="j" value="0" />
					<c:forEach items="${requestScope.apartmentDetails}"
						var="AptDetails">
						<c:if test="${AptDetails.vacant}">
							<c:set var="i" value="${i+1}" />
						</c:if>
						<c:if test="${!AptDetails.vacant}">
							<c:set var="j" value="${j+1}" />
						</c:if>
					</c:forEach>

					<div class="col-md-5">
						<table style="margin: auto; margin-bottom: 50px;"
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
								<td><c:out value="${fn:length(apartmentDetails)}" /></td>
							</tr>
						</table>
					</div>
					<%-- End : Apartments Table --%>

					<%-- Start : Rental Details --%>
					<div class="col-md-5">
						<table class='table-bordered table'
							style='margin: auto; margin-bottom: 50px;'>
							<caption class="bg-success MyCaption">Rent Details</caption>
							<tr>
								<th style="min-width: 5px;">Apt #</th>
								<th style="min-width: 120px;">Username</th>
								<th style="min-width: 120px;">Name On Lease</th>
								<th style="min-width: 30px;">Rent</th>
								<th style="min-width: 100px;">Occupation</th>
							</tr>
							<c:forEach items="${requestScope.rentDetail}" var="RentedApart">

								<tr>
									<td><c:out
											value="${RentedApart.apartmentDetail.apartmentNo}" /></td>
									<td><c:out value="${RentedApart.userDetail.username}" /></td>
									<td><c:out value="${RentedApart.leaseHolderName}" /></td>
									<td><c:out value="${RentedApart.rent}" /></td>
									<td><c:out value="${RentedApart.userDetail.occupation}" /></td>
								</tr>

							</c:forEach>
						</table>
					</div>
					<%-- End : Rental Details --%>

					<%-- Start : Payment Details --%>
					<table class='table-bordered table'
						style='width: 40%; margin: auto; margin-bottom: 50px;'>
						<caption class="bg-success MyCaption">Rent Payments</caption>
						<tr>
							<th style="min-width: 5px;">Apt #</th>
							<th style="min-width: 120px;">Username</th>
							<th style="min-width: 120px;">Name On Lease</th>
							<th style="min-width: 30px;">Rent</th>
							<th style="min-width: 100px;">Rent Paid</th>
							<th style="min-width: 100px;">Month</th>
							<th style="min-width: 100px;">Year</th>
						</tr>

						<c:forEach items="${requestScope.rentLogs}" var="RentLog">
							<tr>
								<td><c:out value="${RentLog.apartmentDetail.apartmentNo}" /></td>
								<td><c:out value="${RentLog.userDetail.username}" /></td>
								<td><c:out value="${RentLog.rentDetail.leaseHolderName}" /></td>
								<td><c:out value="${RentLog.rentDetail.rent}" /></td>
								<td><c:out value="${RentLog.rentPaid }" /></td>
								<td><c:out value="${RentLog.rentMonth}" /></td>
								<td><c:out value="${RentLog.rentYear}" /></td>
							</tr>
						</c:forEach>
					</table>
					<%-- Start : Payment Details --%>


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