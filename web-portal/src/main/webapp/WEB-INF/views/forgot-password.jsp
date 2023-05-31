
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:htmlEscape defaultHtmlEscape="true" /> 

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Forgot Password</title>
</head>
<body>
	<h1>Forgot Password</h1>

	<c:if test="${param.success != null}">
		<p style="color: green;">Your password has been reset
			successfully.</p>
	</c:if>

	<c:if test="${not empty error}">
		<p style="color: red;">${error}</p>
	</c:if>

	<form method="post" name="form1" data-parsley-validate
		class="form-horizontal"
		action="<c:url value='/agentProfdetails/confirmPasswordbyagent'/>"
		onsubmit="return checkPassword();">
		<h5 style="color: #005baa; line-height: 47px">
			USERNAME <br /> <b> <input type="text" value="${agentUserName}"
				readonly>
			</b>
		</h5>


		<input type="password" placeholder="Old Password" name="password"
			id="password"> <input type="password"
			id="newPassword" name="newPassword" placeholder="NewPassword">
		<input type="password" placeholder="ConfirmPassword"
			name="confirmPassword" id="confirmPassword">
		<button type="submit">Reset Password</button>
	</form>
</body>

<script lang="JavaScript">
	function checkPassword() {
		// alert("test data");
		var error = "";

		var e = document.getElementById("newPassword").value;

		var e1 = document.getElementById("confirmPassword").value;

		var e2 = document.getElementById("password").value;

		var re = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,15}$/;

		if (e2 == null || e2 == '') {
			alert("Please enter Old Password");
			return false;
		} else if (e == null || e == '') {
			alert("Please enter New Password");
			return false;

		} else if ((e.length < 8))

		{
			
			error = "The password is the minimum eight digits. \n";
			alert("The password is the wrong length:" + error);
			return false;

		}

		else if ((e.length > 15))

		{
		
			error = "The password is the maximum 15 digits. \n";
			alert("The password is the wrong length:" + error);
			return false;

		} else if (!re.test(e)) {
			alert("Error:  password   must contain alpha numeric with special Characters ");
			

			return false;

		} else if (e1 == null || e1 == '') {
			alert("Please enter Confirm Password");
			return false;
		} else if (e1 == e2) {
			alert("Old Password and New Password are same");
			return false;
		} else if (e1 != e) {
			alert("New Password and Confirm password Not Match");
			return false;
		}

		else {

			return true;
		}
	}
</script>
</html>
