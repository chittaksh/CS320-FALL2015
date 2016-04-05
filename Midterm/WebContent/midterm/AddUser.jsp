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

	<form method="post" action="AddUser" class="form-horizontal" style="margin:auto;"
		role="form" >
		<input type='hidden' name='txtAppID'
			value='<c:out value="${param.AppID}" />' />
		<div class="form-group">
			<label for="LeaseTerminationDate" class="control-label col-sm-5">Username :</label>
			<div class="col-sm-3">
				<input type="text" name="txtUsername" id="email"
					class="form-control" required />
			</div>
			         <div class="col-sm-2">
                <input type="submit" value="Submit" class="btn btn-primary"
                    name="submit" />
            </div>
		</div>

	</form>

</body>
</html>