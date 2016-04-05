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
<script>
function submitForm(id){
	$.post("PayRent",
		    {
		   rentDetailID: id,
		        rentAmt: $("#rentAmt"+id).val(),
		        rentMonth: $("#rentMonth"+id+" option:selected").val(),	        
	            rentYear: $("#rentYear"+id+" option:selected").val()
		    }).success(function(data){window.location.href = "http://localhost:8080/320/hw4/Receipt?ID="+data});
}
</script>
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
				<h2>Pay Rent</h2>
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

				<%--  Start : Response on Form --%>
				<c:if test="${responseMessage != null}">
					<div class="alert alert-danger" role="alert">
						<span class="glyphicon glyphicon-exclamation-sign"
							aria-hidden="true"></span> <span class="sr-only">Error:</span>
						<c:out value="${responseMessage}" />
					</div>
				</c:if>
				<%-- End : Response on Form --%>

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
					<form method='post' action='PayRent'>
						<table class='table-bordered table'
							style='width: 40%; margin: auto; margin-bottom: 50px;'>
							<caption class="bg-success MyCaption">Rent Payments</caption>
							<tr>
								<th style="min-width: 5px;">Apt #</th>
								<th style="min-width: 120px;">Username</th>
								<th style="min-width: 120px;">Name On Lease</th>
								<th style="min-width: 30px;">Rent</th>
								<th style="min-width: 100px;">Rent Amount</th>
								<th style="min-width: 100px;">Month</th>
								<th style="min-width: 100px;">Year</th>
								<th style="min-width: 30px;">Operation</th>
							</tr>
							<c:forEach items="${requestScope.RentedAparts}" var="RentedApart">

								<tr>
									<td><c:out
											value="${RentedApart.apartmentDetail.apartmentNo}" /></td>
									<td><c:out value="${RentedApart.userDetail.username}" /></td>
									<td><c:out value="${RentedApart.leaseHolderName}" /></td>
									<td><c:out value="${RentedApart.rent}" /></td>
									<td><input type="number" id="rentAmt${RentedApart.id}"
										class="form-control" /></td>
									<td><select class="form-control"
										id="rentMonth${RentedApart.id}">
											<c:forEach items="${applicationScope.Months}" var="month">
												<option value="${month}">
													<c:out value="${month}" />
												</option>
											</c:forEach>
									</select></td>
									<td><select class="form-control"
										id="rentYear${RentedApart.id}">
											<option value="2015">2015</option>
											<option value="2016">2016</option>
											<option value="2017">2017</option>
											<option value="2018">2018</option>
											<option value="2019">2019</option>
											<option value="2020">2020</option>
									</select></td>
									<td><input type="button" class="btn btn-primary"
										id="submit" value="Pay"
										onclick="javascript: submitForm(${RentedApart.id})" /></td>
								</tr>

							</c:forEach>
						</table>
					</form>
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