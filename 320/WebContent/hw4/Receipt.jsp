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
<script src='../jquery.min.js'></script>
<script src='../bootstrap/bootstrap.min.js'></script>
<title>Receipt : Lease Management System</title>
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
				<h2>Receipt</h2>
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

				<div class="Right" style="width: 85%;">
					<div class="col-md-offset-3">
						<table style="border-collapse: collapse;">
							<tr>
								<td colspan="4">Receipt</td>
							</tr>
							<tr>
								<td style="margin: 20px;">
									<div class="Left">
										<label class="form-label">ID :</label> <label
											class="form-label">${rentLog.id}</label>
									</div>
								</td>
								<td style="margin: 20px;">
									<div class="Right">
										<label class="form-label">Paid On :</label> <label
											class="form-label">${rentLog.payDate}</label>
									</div>
								</td>
							</tr>

							<tr>
								<td>
									<div class="Left">
										<label class="form-label">Paid For :</label> <label
											class="form-label">${rentLog.rentMonth} /
											${rentLog.rentYear}</label>
									</div>
								</td>

								<td>
									<div class="Right">
										<label class="form-label col-md-3">Amount :</label>
										<div class="col-sm-8">
											<label class="form-label col-md-4">${rentLog.rentPaid}</label>
										</div>
									</div>
								</td>
							</tr>

							<tr style="padding-top: 30px;">
								<td style="margin: 20px;">
									<div class="Right">
										<label class="form-label">Lease Holder Name :</label> <label
											class="form-label">${rentLog.rentDetail.leaseHolderName}</label>
									</div>
								</td>
							</tr>

						</table>
					</div>
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