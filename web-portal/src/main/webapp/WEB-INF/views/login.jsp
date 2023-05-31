<!DOCTYPE html>
<html lang="en">
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>

<!-- Contains all other headers like bootstrap css and script links  -->
<jsp:include page="includes/base.jsp" />

<%@ page isELIgnored="false"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
<link rel="icon" type="image/gif" sizes="16x16"
	href="${pageContext.request.contextPath}/resources/assets/icons/newMobiMIcon.png" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min1.css">
</head>
<body class="loginBg login-bg" id="body" name="body"
	oncontextmenu="return false">
	<div class="container-fluid">
		<div class="row">
			<div
				class="col-md-4 offset-md-7 offset-lg-7 col-lg-4 col-12 mt-2 order-0">
				<div class="container p-4 bg-white rounded-3 mt-3">
					<img class="img-fluid" src="${pageContext.request.contextPath}/resources/assets/logo/mobi_logo.svg" />
					<!-- 			<h1 class="c-092540 mt-2">Looks like you'r new to mobi. Welcome</h1> -->
					<h1 class="mt-2"></h1>

					<form data-parsley-validate name="loginForm" class="login-form"
						action="${pageContext.request.contextPath}/authenticateTheUser"
						method="POST" id="login" name="form1"">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<div class="form-group mt-2">
							<label class="text-dark mt-2 mb-2">UserName <sup>*</sup></label>
							<input name="username" type="text" class="form-control"
								id="username" placeholder="Username" />
						</div>

						<div class="form-group mt-2">
							<label class="text-dark mt-2 mb-2">Password</label> <input
								type="password" id="password" class="form-control"
								placeholder="Enter Password" name="password"
								onkeydown="return onclickTab(event);" />
						</div>

						<input type="hidden" align="center" id="txtCompare1"
							name="txtCompare1" placeholder="" readonly
							onclick="return test();" />

						<div class="form-group mt-2">
							<a class="float-end small text-decoration-none;" href="#"
								onclick="forgotPwd()">Forgot Password</a>
						</div>

						<div class="alert alert-e0edff c-367fee too-small mt-3"
							style="margin-top: 2.5rem !important" role="alert">To
							prevent spam and automated extraction of data from websites</div>
						<div id="verification-box" class="mt-4" disabled>

							<div
								style="font-size: small; text-align-last: center; margin-top: 10px; text-align: -webkit-center; background-color: transparent;">
								<p id="pswitch"
									class="slider-text slide-text pullee-slider-text">Slide to
									Login</p>
								<input id="swipeen" type="range" style="" value="0"
									class="pullee pullee-readyonly" placeholder="Swipe to submit"
									min="0" max="150" disabled />
							</div>
						</div>

						<div class="form-group mt-2">
							<button type="submit" name="submit" id="btnValid"
								class="btn btn-005baa fw-bold w100-per" style="display: none">Login</button>
						</div>
						<br />
						<p color="red" id="error" value=""></p>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" /> <input type="hidden"
							name="hiddenCaptcha" value="false" />
					</form>
				</div>
			</div>
		</div>
	</div>

	<form
		action="${pageContext.request.contextPath}/j_spring_security_logout"
		method="post" id="formId" name="formId">
		<input type="hidden" name="link-logout" id="link-logout" value="" />

		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

	<div class="visible-xs visible-sm extendedChecker"></div>

	<script src="${pageContext.request.contextPath}/resources/javascript/main.js"></script>
	<script src="${pageContext.request.contextPath}/resources/javascript/login.js"></script>
	<script src="${pageContext.request.contextPath}/resources/javascript/plugins/essential-plugins.js"></script>


</body>
</html>