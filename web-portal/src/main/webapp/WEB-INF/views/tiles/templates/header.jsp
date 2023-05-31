<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!doctype html>
<html lang="en">

<head>
<%@ page isELIgnored="false"%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Products</title>
<link rel="icon" type="image/x-icon"
	href="../resources/images/favicon/favicon.ico">

<link rel="manifest" href="/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage"
	content="${pageContext.request.contextPath}/resources/images/favicon/ms-icon-144x144.png">
<meta name="theme-color" content="#f1f5fa">

<jsp:include page="/WEB-INF/views/includes/base.jsp" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/payoutsummary.css">
</head>


<body>
	<div class="leftside-menu">
		<!-- Brand Logo Light -->
		<a href="#" class="logo logo-light"> <span
			class="logo-lg"> <img class="mt-2 mb-2"
				src="${pageContext.request.contextPath}/resources/assets/logo/mobi.png"
				style="width: 60px; height: 20px; margin-left: -150px;" alt="logo">
		</span> <span class="logo-sm"> <img
				src="${pageContext.request.contextPath}/resources/assets/logo/mobi.png" style="width: 60px; height: 20px; margin-left: -150px;"
				alt="small logo">
		</span>
		</a>
		<!-- Sidebar Hover Menu Toggle Button -->
		<div class="button-sm-hover" data-bs-toggle="tooltip"
			data-bs-placement="right" title="Show Full Sidebar">
			<i class="ri-checkbox-blank-circle-line align-middle"></i>
		</div>
		<!--- Sidemenu -->
		<ul class="side-nav  ">
			<div class="border"></div>
			<li class="side-nav-title" style="color: white; font-size: 15px;">MAIN
				MENU</li>
			<li class="side-nav-item   "><a data-bs-toggle="collapse "
				href=" " aria-expanded="false" aria-controls="sidebarDashboards"
				class="side-nav-link   mobi-active"> <img class="Mobi-summary"
					src="${pageContext.request.contextPath}/resources/assets/icons/summary.png"></img>
					<!-- <span class="badge bg-success float-end">5</span> --> <span
					class="p-2 text-white "> Summary</span>
			</a> <!-- <div class="collapse" id="sidebarDashboards"><ul class="side-nav-second-level"><li><a href="dashboard-analytics.html">Analytics</a></li><li><a href="index.html">Ecommerce</a></li><li><a href="dashboard-projects.html">Projects</a></li><li><a href="dashboard-crm.html">CRM</a></li><li><a href="dashboard-wallet.html">E-Wallet</a></li></ul></div> -->
			</li>

			<!-- <li class="side-nav-item"><a data-bs-toggle="collapse" href="#sidebarCrm" aria-expanded="false" aria-controls="sidebarCrm" class="side-nav-link"><i class="uil uil-tachometer-fast"></i><span class="badge bg-danger text-white float-end">New</span><span> CRM </span></a><div class="collapse" id="sidebarCrm"><ul class="side-nav-second-level"><li><a href="crm-projects.html">Projects</a></li><li><a href="crm-orders-list.html">Orders List</a></li><li><a href="crm-clients.html">Clients</a></li><li><a href="crm-management.html">Management</a></li></ul></div></li> -->
		</ul>
		<!--- End Sidemenu -->
		<div class="clearfix"></div>
	</div>
</body>

<script
	src="${pageContext.request.contextPath}/resources/javascript/livetime.js"></script>


<script>
	$(document)
			.ready(
					function() {
						const currentURL = "${ requestScope['javax.servlet.forward.request_uri']}"

						switch (currentURL) {
						case "${pageContext.request.contextPath}/Product/productList":
							const link = document
									.getElementById("link-products")
							link.className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/Quotation/list":
							document.getElementById("link-quotation").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/Quotation/QuotationView":
							document.getElementById("link-quotation").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/MyAction/list":
							document.getElementById("link-myAction").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/Order/list":
							document.getElementById("link-orders").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/Order/OrderViewPaydee":
							document.getElementById("link-orders").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/Order/OrderView":
							document.getElementById("link-orders").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/TPA/list":
							document.getElementById("link-tpaFile").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/merchantReg/list":
							document
									.getElementById("link-merchantRegistration").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/merchantReg/registrationView":
							document
									.getElementById("link-merchantRegistration").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/Signature":
							document.getElementById("link-signature").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/MerchantOrder":
							document.getElementById("link-merchantOrder").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/Approvals":
							document.getElementById("link-userApproval").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/merchantData/list":
							document.getElementById("link-merchant-old-data").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/MerchantData/OrderView":
							document.getElementById("link-merchant-order-data").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/Experian/Search":
							document.getElementById("link-experian-search").className = "nav-link link-active"
							break;
						default:
							break;
						}
					});
</script>

</html>