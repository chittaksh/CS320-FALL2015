<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*, midterm.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel='stylesheet' type='text/css' href='../MyStyle.css' />
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css' />
<script
	src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js'></script>
<script
	src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>
<title>Mid-Term 1 Project</title>
</head>
<body class="container">

	<%-- Start : If Rebate ID != null --%>
	<c:if test="${param.RebateId != null }">
		<c:forEach items="${applicationScope.arrRebate}" var="rebat">
			<c:if test="${rebat.id == param.RebateId}">
				<c:set target="${rebat}" property="received"
					value="<%=new java.util.Date()%>" />
				<c:redirect url="Main.jsp" />
			</c:if>
		</c:forEach>
	</c:if>
	<%-- End : If Rebate ID != null --%>

	<%-- Start : Code for User Details --%>
	<form method="post" action="Main" role="form">
		<div class="form-group">
			<label for="email" class="control-label col-sm-2">Current
				User : </label>
			<div class="col-sm-3">
				<select class="form-control" name="currUser">

					<c:forEach items="${applicationScope.arrUser}" var="user">
						<option value="${user.id}"
							${user.id == SelectedUser ? 'selected="selected"' : ''}>
							<c:out value="${user.name}"></c:out>
						</option>
					</c:forEach>

				</select>
			</div>
			<div class="col-sm-2">
				<input type="submit" value="Submit" class="btn btn-primary"
					name="submit" />
			</div>
			<div class="col-sm-2">
				<a href="AddUser" class="btn">Add User </a>
			</div>
		</div>
	</form>

	<%-- End : Code for User Details --%>

	<%-- Start : Code for Rebate Details --%>

	<c:set var="a" value="0" />
	<c:set var="b" value="0" />

	<form method="post" action="Main" role="form">
		<table class="table table-bordered" style="padding-top: 25px;">
			<tr>
				<th>ID</th>
				<th>Rebate</th>
				<th>Amount</th>
				<th>Received</th>
			</tr>

			<c:forEach items="${applicationScope.arrRebate}" var="rebate">
				<c:if test="${SelectedUser == rebate.user.id}">
					<tr>
						<td>${rebate.id}</td>
						<td>${rebate.name}</td>
						<td>$${rebate.amount}</td>
						<td><c:choose>
								<c:when test="${rebate.received == null}">
									<a href='?RebateId=<c:out value="${rebate.id}"/>'>Not
										Received</a>
								</c:when>
								<c:when test="${rebate.received != null}">
									<fmt:formatDate value="${rebate.received}" pattern="MM/dd/yyyy" />
								</c:when>
							</c:choose>
					</tr>

					<c:set var="a" value="${a+rebate.amount}" />

					<c:if test="${rebate.received != null}">
						<c:set var="b" value="${b+ rebate.amount}" />
					</c:if>
				</c:if>
			</c:forEach>

			<tr>
				<td><c:out value="${fn:length(applicationScope.arrRebate)+1}" /></td>
				<td><input type="text" name="txtName" class="form-control"
					required /></td>
				<td><input type="number" name="txtValue" class="form-control"
					required /></td>
				<td><input type="submit" value="Add" class="btn btn-primary"
					name="Add" /></td>
			</tr>
		</table>
		<%-- End : Code for Rebate Details --%>

		<%-- Start : Code for Totals --%>
		Total Amount : ${a} Received Amount : ${b}
		<%-- end : Code for Totals --%>

	</form>

</body>
</html>