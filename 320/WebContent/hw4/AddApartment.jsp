<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*, hw4.*"%>
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
<title>Add Apartment : Lease Management System</title>
<script>
function clear-form()
{
    document.apartmentform.apartmentid.value = "";
    document.apartmentform.facilities.value= "";
    document.apartmentform.maxpeople.value="";
    document.apartmentform.rent.value="";
    document.apartmentform.deposits.value="";
    var ele = document.registerform.apartmentType;
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
				<h1>Lease Management System</h1>
				</br>
				</br>
				<a href='Login' class='Left'>Home</a>
				<font class='Right'>Welcome <c:out value="${Username}" /> !
					<a href='Logout'>Logout</a></font>
				</br>
				</br>
				<h2>Add Apartment</h2>
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
					<form method="post" action="AddApartment" class="form-horizontal"
						role="form" name="apartmentform">
						<div class="form-group">
							<label for="apartmentid" class="control-label col-sm-5">Apartment
								#: (number only)</label>
							<div class="col-sm-3">
								<input type="number" name="apartmentid" id="email"
									class="form-control" required />
							</div>
						</div>
						<div class="form-group">
							<label for="apartmentType" class="control-label col-sm-5">Apartment
								Type : </label>
							<div class="col-sm-3">
								<div style="text-align: left;">
									<input type="radio" name="apartmentType" value="1 BHK" required>
									1 BHK </input> </br> <input type="radio" name="apartmentType"
										value="2 BHK 1 Bath" required> 2 BHK 1 Bath </input> </br> <input
										type="radio" name="apartmentType" value="2 BHK 2 Bath"
										required> 2 BHK 2 Bath </input> </br>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="facilities" class="control-label col-sm-5">Facilities
								: </label>
							<div class="col-sm-3">
								<textarea rows='5' name='facilities' class='form-control'></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="maxpeople" class="control-label col-sm-5">Maximum
								People : (number only)</label>
							<div class="col-sm-3">
								<input type="number" name="maxpeople" class="form-control"
									required />
							</div>
						</div>
						<div class="form-group">
							<label for="rent" class="control-label col-sm-5">Rent :
								(number only) </label>
							<div class="col-sm-3">
								<input type="number" name="rent" class="form-control" required />
							</div>
						</div>
						<div class="form-group">
							<label for="deposits" class="control-label col-sm-5">Deposits
								: (number only)</label>
							<div class="col-sm-3">
								<input type="number" name="deposits" class="form-control"
									required />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-4">
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