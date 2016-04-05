<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*, hw4.*, java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Rent details : Lease Management System</title>
<script>
function clear-form()
{
    document.RentForm.LeaseTerminationDate.value = "";
    document.RentForm.LeaseHolderName.value= "";
    document.RentForm.Rent.value="";
    document.RentForm.Deposit.value="";
    var ele = document.registerform.IDProof;
    for(var i=0;i<ele.length;i++)
        ele[i].checked = false;
    return false;
}
</script>
</head>
<body class='container'>
	<c:choose>
		<c:when test="${!pageContext.session['new']}">
			<c:if test="${sessionScope.User.role == 'Manager'}">
				<c:choose>
					<c:when test="${param.AppID != null}">
						<h1>Lease Management System </h1>
						</br>
						</br>
						<a href='Login' class='Left'>Home</a>
						<font style='float: right;'>Welcome <c:out
								value="${sessionScope.User.username}" /> ! <a href='Logout'>Logout</a></font>
						</br>
						</br>
						<h2>Rent Page</h2>
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
							<form method="post" action="RentOut" class="form-horizontal"
								role="form" name="RentForm">
								<input type='hidden' name='txtAppID'
									value='<c:out value="${param.AppID}" />' />
								<div class="form-group">
									<label for="LeaseTerminationDate"
										class="control-label col-sm-5">Lease Termination Date
										: (MM/dd/yyyy hh:mm:ss)</label>
									<div class="col-sm-3">
										<input type="text" name="LeaseTerminationDate" id="email"
											class="form-control" required />
									</div>
								</div>
								<div class="form-group">
									<label for="LeaseHolderName" class="control-label col-sm-5">Lease
										Holder Name : </label>
									<div class="col-sm-3">
										<input type="text" name="LeaseHolderName" class="form-control"
											required />
									</div>
								</div>
								<div class="form-group">
									<label for="IDProof" class="control-label col-sm-5">ID
										Proof : </label>
									<div class="col-sm-3" style="text-align: left;">
										<input type="radio" name="IDProof" value="Credit History" required>Credit
										Check</input></br> <input type="radio" name="IDProof" value="Passport" required>Passport</input></br>
										<input type="radio" name="IDProof" value="State ID" required>State ID</input></br>
									</div>
								</div>
								<div class="form-group">
									<label for="Rent" class="control-label col-sm-5">Rent :
									</label>
									<div class="col-sm-3">
										<input type="number" name="Rent" class="form-control" required />
									</div>
								</div>
								<div class="form-group">
									<label for="Deposite" class="control-label col-sm-5">Deposite
										: </label>
									<div class="col-sm-3">
										<input type="number" name="Deposite" class="form-control"
											required />
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-4 col-sm-2">
										<input type="submit" value="Submit" class="btn btn-primary"
											name="submit" />
									</div>
									<div class="col-sm-1">
										<button value="Clear" name="clear" type="reset"
											onclick="clear-form()" class="btn">Clear</button>
									</div>
								</div>
							</form>
						</div>
					</c:when>

					<%-- Start : When Apartment ID (AppID) is empty --%>
					<c:when test="${param.AppID == null}">
						<c:redirect url="ManagerHome" />
					</c:when>
					<%-- End : When Apartment ID (AppID) is empty --%>
				</c:choose>
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