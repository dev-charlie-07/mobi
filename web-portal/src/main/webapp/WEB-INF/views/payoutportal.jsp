
<%@ page import="com.mobiversa.model.PayoutModel"%>
<%@ page import="com.mobiversa.model.PaginationBean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>Dashboard | Hyper - Responsive Bootstrap 5 Admin
	Dashboard</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta
	content="A fully featured admin theme which can be used to build CRM, CMS, etc."
	name="description" />
<meta content="Coderthemes" name="author" />

<jsp:include page="/WEB-INF/views/includes/base.jsp" />

<!-- App favicon -->
<link rel="shortcut icon"
	href="https://coderthemes.com/hyper/saas/assets/images/favicon.ico">
<link rel="stylesheet"
	href="https://coderthemes.com/hyper/saas/assets/vendor/daterangepicker/daterangepicker.css">
<link
	href="https://coderthemes.com/hyper/saas/assets/vendor/datatables.net-bs5/css/dataTables.bootstrap5.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://coderthemes.com/hyper/saas/assets/vendor/datatables.net-responsive-bs5/css/responsive.bootstrap5.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://coderthemes.com/hyper/saas/assets/vendor/datatables.net-fixedcolumns-bs5/css/fixedColumns.bootstrap5.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://coderthemes.com/hyper/saas/assets/vendor/datatables.net-fixedheader-bs5/css/fixedHeader.bootstrap5.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://coderthemes.com/hyper/saas/assets/vendor/datatables.net-buttons-bs5/css/buttons.bootstrap5.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://coderthemes.com/hyper/saas/assets/vendor/datatables.net-select-bs5/css/select.bootstrap5.min.css"
	rel="stylesheet" type="text/css" />
<!-- Theme Config Js -->
<script
	src="https://coderthemes.com/hyper/saas/assets/js/hyper-config.js"></script>
<!-- App css -->
<link
	href="https://coderthemes.com/hyper/saas/assets/css/app-saas.min.css"
	rel="stylesheet" type="text/css" id="app-style" />
<!-- Icons css -->
<link href="https://coderthemes.com/hyper/saas/assets/css/icons.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" id="app-style" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/payoutsummary.css">

<%@ page isELIgnored="false"%>

</head>
<body>
	<!-- Begin page  -->
	<div class="wrapper">
		<!-- ========== Topbar Start ========== -->
		<div class="navbar-custom">
			<div class="d-flex justify-content-between align-items-center ms-2"
				id="topbar-container">
				<div class=" d-flex flex-row">
					<button class="d-md-none d-sm-block button-toggle-menu">
						<i class="fa fa-bars"></i>
					</button>
					<!-- Horizontal Menu Toggle Button -->
					<button class="navbar-toggle" data-bs-toggle="collapse"
						data-bs-target="#topnav-menu-content">
						<div class="lines">
							<span></span> <span></span> <span></span>
						</div>
					</button>
					<div class="d-flex flex-column align-items-start mt-2">
						<div
							style="font-family: Poppins, sans-serif; font-weight: 600; color: #2D2D2D;"
							class="mb-1 pop-color-grey">Payout Summary</div>
						<div class="day small fw-bold">
							<div style="font-family: Poppins, sans-serif; font-weight: 600;"
								id="display-time"></div>
						</div>
					</div>

				</div>
				<div>

					<div class="dropdown mt-2">
						<a class="nav-link dropdown-toggle" href="#" id="accountDropdown"
							role="button" data-bs-toggle="dropdown" aria-expanded="false">
							<img
							src="${pageContext.request.contextPath}/resources/assets/icons/personroundicon.png"
							id="user-icon" alt="User icon"> Account
						</a>
						<ul class="dropdown-menu dropdown-menu-end "
							aria-labelledby="exportDropdown">
							<li><a class="dropdown-item"
								href="${pageContext.request.contextPath}/forgotPassword">Change
									Password</a></li>
							<li><a class="dropdown-item"
								href="${pageContext.request.contextPath}/logout">Logout</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Sorry, No Records Found</h5>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<p>Please try again later.</p>
				</div>
			</div>
		</div>
	</div>

	<div class="content-page">
		<div class="content">
			<!-- Start Content-->
			<div class="container-fluid">
				<div class="row">
					<!-- <div class="col-12"><div class="page-title-box"><div class="page-title-right"><form class="d-flex"><div class="input-group"><input type="text" class="form-control form-control-light" id="dash-daterange"><span class="input-group-text bg-primary border-primary text-white"><i class="mdi mdi-calendar-range font-13"></i></span></div><a href="javascript: void(0);" class="btn btn-primary ms-2"><i class="mdi mdi-autorenew"></i></a><a href="javascript: void(0);" class="btn btn-primary ms-1"><i class="mdi mdi-filter-variant"></i></a></form></div><h4 class="page-title">Dashboard</h4></div></div></div> -->
					<div class="row">
						<div class="col-xl-7 col-lg-7 mt-2">
							<div class="row">
								<div class="col-sm-12">
									<div class="card widget-flat"
										style="border-radius: 20px 20px 10px 10px">
										<div class="card-head border border-info p-2 text-center"
											style="border-radius: 20px 20px 0px 0px; font-weight: 500; font-size: 18px; color: #005baa;">
											Find Transactions by Date</div>
										<div class="card-body d-flex flex-column align-items-center">
											<div
												class="d-flex justify-content-between align-items-center">
												<div class="input-group text-left m-2">
													<input type="hidden" name="date11" id="date11"
														<c:out value="${fromDate}"/>>
													
													<input placeholder="From" onfocus="(this.type = 'date')"
														id="fromDate"
														onchange="loadDate(document.getElementById('toDate'),document.getElementById('date12'))"
														class="rounded-5 p-1 ps-2 ms-2 me-2 border pop" required
														aria-required="true" >
														 <input type="hidden"
														name="date12" id="date12" <c:out value="${toDate}"/>>
														<input placeholder="To" onfocus="(this.type = 'date')"
														id="toDate"
														onchange="loadDate(document.getElementById('toDate'),document.getElementById('date12'))"
														class="rounded-5 p-1 ps-2 ms-2 me-2 border pop" required
														aria-required="true" />
													
													<div>
														<select class="form-select rounded-5" id="export-dropdown"
															aria-label="Default select example">
															<option value="choose"selected>Export Type</option>
															<option value="pdf-select">PDF</option>
															<option value="excel-select">Excel</option>
														</select>
													</div>
												</div>
											</div>
											<div
												class="d-flex flex-row jusify-content-center align-items-center mt-3 p-2">
												<button
													class="btn border rounded-pill d-flex align-items-center "
													onclick="return performExport();">
													<span style="color: #005baa">Export</span> <i
														class="bi-export-arrow-down ms-1"></i>
												</button>

												<button
													class="btn border rounded-pill d-flex align-items-center ms-3"
													style="background-color: #005baa; color: #ffffff"
													onclick="return performSearchByDate();">
													<span>Search </span> <i class=" bi-search-icon ms-1"></i>
												</button>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xl-5 col-lg-5 mt-2">
							<div class="row">
								<div class="col-sm-12">
									<div class="card widget-flat"
										style="border-radius: 20px 20px 10px 10px">
										<div class="card-head border border-info p-2 text-center"
											style="border-radius: 20px 20px 0px 0px; font-weight: 500; font-size: 18px; color: #005baa;">
											Find Transactions by Date</div>

										<div
											class="card-body d-flex flex-column mt-2 align-items-center">
											<div
												class="d-flex justify-content-between align-items-center">
												<div class="col-6 col-sm-6">

													<select class="form-select rounded-5"
														aria-label="Default select example"
														onchange="updatePlaceholder()" id="my-dropdown">
														<option value="">choose</option>
														<option value="payoutNo" selected="selected">Payout
															Number</option>
														<option value="accNo">Account Number</option> 
													</select>
												</div>
												<div class="col-6 col-sm-6">
													<input type="email" id="searchValue" name="searchValue"
														class="form-control border rounded-5 ms-2"
														id="exampleInputEmail1" aria-describedby="emailHelp"
														placeholder="Enter the Number" />
												</div>

											</div>
											<div
												class="d-flex flex-row jusify-content-center align-items-center mt-4 p-2">


												<button
													class="btn border rounded-pill d-flex align-items-center"
													style="background-color: #005baa; color: #ffffff"
													onclick="return performSearchByNumber();">
													<span>Search </span> <i class=" bi-search-icon ms-1"></i>
												</button>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>


					</div>



					<div class="row">
						<div class="col-12">
							<div
								class="table-responsive border-top border-bottom border-end border-start rounded-2">
								<table class="table">
									<thead>
										<tr>
											<th class="pop-color" scope="col">Merchant Name</th>
											<th class="pop-color" scope="col">Customer Name</th>
											<th class="pop-color" scope="col">BRN/IC</th>
											<th class="pop-color" scope="col">Account No</th>
											<th class="pop-color" scope="col">Bank Name</th>
											<th class="pop-color" scope="col">Status</th>
											<th class="pop-color" scope="col">Amount</th>
											<th class="pop-color" scope="col">Transactions ID</th>
											<th class="pop-color" scope="col">Date</th>
											<th class="pop-color" scope="col">Time</th>
											<th class="pop-color" scope="col">Paid/Decline Time</th>
											<th class="pop-color" scope="col">Paid/Decline Date</th>
											<th class="pop-color" scope="col">Sub Merchant MID</th>
											<th class="pop-color" scope="col">Sub Merchant Name</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${payoutList}" var="dto">
											<tr>
												<td>${dto.createdby}</td>
												<td>${dto.payeename}</td>
												<td>${dto.payeebrn}</td>
												<td>${dto.payeeaccnumber}</td>
												<td>${dto.payeebankname}</td>
												<c:choose>
													<c:when test="${dto.payoutstatus == 'Processing'}">
														<td class="align-items-center"><img
															src="${pageContext.request.contextPath}/resources/assets/gif/processing_gif.gif"
															class="fa fa-question-circle text-muted"
															style="max-width: 6.5rem" alt="Processing"></td>
													</c:when>
													<c:when test="${dto.payoutstatus == 'Paid'}">
														<td class="align-items-center"><i
															class="fa fa-check-circle text-success">Paid</i>
													</c:when>
													<c:when test="${dto.payoutstatus == 'Declined'}">
														<td class="align-items-center"><i
															class="fa fa-times-circle text-danger">Decline</i>
													</c:when>
													<c:otherwise>
														<td><i class="fa fa-question-circle text-muted">Unknown</i></td>
													</c:otherwise>
												</c:choose>

												<td>${dto.payoutamount}</td>
												<td>${dto.invoiceidproof}</td>
												<td>${dto.payoutdate}</td>
												<td>${dto.createddate}</td>
												<td>${dto.paidTime}</td>
												<td>${dto.paidDate}</td>
												<td>${dto.subMerchantMID}</td>
												<td>${dto.mmId}</td>

											</tr>
										</c:forEach>

									</tbody>
								</table>
							</div>

							<div class="border mt-2">
								<div id="pagination"></div>
								<input type="hidden" id="pgnum"> <input type="hidden"
									id="pgDate"> <input type="hidden" id="pgDate1">
								<input type="hidden" id="TXNType1">
							</div>

						</div>
						<!-- end col-->
					</div>
					<!-- end row-->

				</div>
				<!-- container -->
			</div>
			<!-- content -->
			<!-- <footer class="footer"><div class="container-fluid"><div class="row"><div class="col-md-6"><script>document.write(new Date().getFullYear())</script> © Hyper - Coderthemes.com
                            </div><div class="col-md-6"><div class="text-md-end footer-links d-none d-md-block"><a href="javascript: void(0);">About</a><a href="javascript: void(0);">Support</a><a href="javascript: void(0);">Contact Us</a></div></div></div></div></footer> -->
		</div>
		<!-- ============================================================== -->
		<!-- End Page content -->
		<!-- ============================================================== -->
	</div>

	<script lang="JavaScript">
	
	
	var currentDate = new Date();
	var minDate = new Date();
	minDate.setMonth(currentDate.getMonth() - 3);
	var maxDate = new Date();
	maxDate.setDate(currentDate.getDate());
	// Format the minimum and maximum dates as strings in the format expected by the min and max attributes of the input element
	var minDateString = minDate.toISOString().slice(0,10); 
	var maxDateString = maxDate.toISOString().slice(0,10);
	
	// Set the min and max attributes of the input element
	
	document.getElementById("fromDate").setAttribute("min", minDateString); 
	document.getElementById("fromDate").setAttribute("max", maxDateString); 
	document.getElementById("toDate").setAttribute("min", minDateString); 
	document.getElementById("toDate").setAttribute("max", maxDateString);
	
	
	
	/* 	var fromDateServer = document.getElementById("pgDate").value = "${paginationBean.dateFromBackend}";
		var from1DateServer = document.getElementById("pgDate1").value = "${paginationBean.date1FromBackend}";
		 var currentPage1 = document.getElementById("pgnum").value = "${paginationBean.currPage}"; 
		 alert(currentPage1);  */

		function updatePlaceholder() {
			var dropdown = document.getElementById("my-dropdown");
			var input = document.getElementById("searchValue");
			if (dropdown.value === "choose") {
				input.placeholder = "Enter Number";
			} else if (dropdown.value === "payoutNo") {
				input.placeholder = "Enter Payout Number";
			} else if (dropdown.value === "accNo") {
				input.placeholder = "Enter Account Number";
			}
		}
		function performSearchByNumber() {
			var dropdown = document.getElementById("my-dropdown");
			var input = document.getElementById("searchValue").value;
			var pgnum = document.getElementById("pgnum").value;
			if (input == null || input == "") {
				alert("Please Enter the Number")
			} else {

				if (dropdown.value === "payoutNo") {
					document.location.href = '${pageContext.request.contextPath}/payoutSummary/searchByNumber?poNumber='
							+ input + '&searchType=payoutNo';
							
				} else if (dropdown.value === "accNo") {
					document.location.href = '${pageContext.request.contextPath}/payoutSummary/searchByNumber?poNumber='
							+ input + '&searchType=accNo';
							
				}
			}
		}
		function performExport() {
			
			var dropdown = document.getElementById("export-dropdown");
			alert(dropdown.value);
			var fromdateString = document.getElementById("fromDate").value;
			var todateString = document.getElementById("toDate").value;
			var pgnum = document.getElementById("pgnum").value;
			if (fromdateString == null || fromdateString == ''
					|| todateString == null || todateString == '') {
				alert("Please Select date(s)");
			} else {
				
				if (dropdown.value =="choose") {
					alert("Select Either PDF/Excel");
				} else if (dropdown.value === "excel-select") {
					alert("Hi inside else if");
					document.location.href = '${pageContext.request.contextPath}/payoutSummary/exportPayout?fromdate='
							+ fromdateString
							+ '&todate='
							+ todateString
							+ '&type=excel'
							+ '&pagenum=' + pgnum;
					form.submit;
				} else if (dropdown.value === "pdf-select") {
					document.location.href = '${pageContext.request.contextPath}/payoutSummary/exportPayout?fromdate='
							+ fromdateString
							+ '&todate='
							+ todateString
							+ '&type=pdf'
							+ '&pagenum=' + pgnum;
				}
			}
		}
	 	function performSearchByDate() {
			var fromdateString = document.getElementById("fromDate").value;

			var todateString = document.getElementById("toDate").value;
			var pgnum = document.getElementById("pgnum").value;

			if (fromdateString == null || fromdateString == ''
					|| todateString == null || todateString == '') {
				alert("Please Select date(s)");
			} else {
				document.getElementById("date11").value = fromdateString;
				document.getElementById("date12").value = todateString;
				/* alert("Please Select date(s)"); */
				document.location.href = '${pageContext.request.contextPath}/payoutSummary/searchPayout?fromdate='
						+ fromdateString
						+ '&todate='
						+ todateString
						+ '&pagenum=' + pgnum;
				form.submit;
			}
		} 
	 	
	 	function loadDate(inputtxt, outputtxt) {

	 		/* alert(inputtxt.value); */
			// alert("test data123");
			var field = inputtxt.value;
			//var field1 = outputtxt.value;
			//alert(field+" : "+outputtxt.value);
			//document.getElementById("date11").value=field;
			outputtxt.value = field;
			//alert(outputtxt.value);
			// alert(document.getElementById("date11").value);
		}
		/* function performSearchByDate() {
		    var fromdateString = document.getElementById("fromDate").value;
		    var todateString = document.getElementById("toDate").value;
		    var pgnum = document.getElementById("pgnum").value;

		    var dateRegex = /^\d{4}\-\d{2}\-\d{2}$/; // Regex for YYYY-MM-DD format
		    if (!dateRegex.test(fromdateString) || !dateRegex.test(todateString)) {
		        alert("Please select valid dates");
		        return false;
		    }

		    var encodedFromDate = encodeURIComponent(fromdateString);
		    var encodedToDate = encodeURIComponent(todateString);

		    document.location.href = '${pageContext.request.contextPath}/payoutSummary/searchPayout?fromdate='
		        + encodedFromDate
		        + '&todate='
		        + encodedToDate
		        + '&pagenum=' + pgnum;

		    return false;
		} */



		function pgSearchByDate() {
			var fromdateString = document.getElementById("pgDate").value;
			var todateString = document.getElementById("pgDate1").value;
			var pgnum = document.getElementById("pgnum").value;

			
			if (fromdateString == null || fromdateString == ''
					|| todateString == null || todateString == '') {
				/* alert("Please Select date(s)"); */
			} else {
				document.location.href = '${pageContext.request.contextPath}/payoutSummary/searchPayout?fromdate='
						+ fromdateString
						+ '&todate='
						+ todateString
						+ '&pagenum=' + pgnum;
				form.submit;
			}
		}
		function pgSearchByDate1() {
			var fromdateString = document.getElementById("pgDate").value;
			var todateString = document.getElementById("pgDate1").value;
			 var pgnum = document.getElementById("pgnum").value; 
			 var dropdown = document.getElementById("my-dropdown");

		;
			if (fromdateString == null || fromdateString == ''
					|| todateString == null || todateString == '') {
				/* alert("Please Select date(s)"); */
			} else {
				document.location.href = '${pageContext.request.contextPath}/payoutSummary/searchPayout?fromdate='
						+ fromdateString
						+ '&todate='
						+ todateString
						+ '&pagenum=' + pgnum;
				form.submit;
			}
		}
	</script>



	<div id="pagination"></div>
	<input type="hidden" id="pgnum">
	<input type="hidden" id="pgDate">
	<input type="hidden" id="pgDate1">
	<!--  <input type="hidden" id="TXNType1" > -->
	<script>

         var fromDateServer = document.getElementById("pgDate").value="${paginationBean.dateFromBackend}";
         var from1DateServer = document.getElementById("pgDate1").value="${paginationBean.date1FromBackend}";
      	var payoutListSize = "${payoutListSize}";
     
         if (payoutListSize == 0) {
           // Get the modal
           var modal = document.getElementById("myModal");

           // Display the modal
           modal.style.display = "block";

           // When the user clicks on the close button, close the modal
           var closeBtn = document.getElementsByClassName("close")[0];
           closeBtn.onclick = function() {
             modal.style.display = "none";
           }
         }

         
      </script>
	<script>
      function updateDate() {
    		const dateElem = document.getElementById("display-time");

    		const options = {
    			weekday: "long",
    			year: "numeric",
    			day: "numeric",
    			month: "short",

    		};

    		const date = new Date();
    		const formattedDate = date.toLocaleDateString(undefined, options);

    		dateElem.textContent = formattedDate.replace(",", "");

    	}

    	updateDate();
    	setInterval(updateDate, 1000);
         /* * * * * * * * * * * * * * * * *
          * Pagination
          * javascript page navigation
          * * * * * * * * * * * * * * * * */
         
          
          function dynamic(pgNo){
         	/* alert("Page Number:"+pgNo); */
         	document.getElementById("pgnum").value=pgNo;
         	pgSearchByDate();
         	
         }
         
          function previous(pgNo){
         		/* alert("Page Number:"+pgNo); */
         		pgNo--;
         		document.getElementById("pgnum").value=pgNo;
         		pgSearchByDate();
         		
         	}
          
          function next(pgNo){
         		/* alert("Page Number:"+pgNo); */
         		pgNo++;
         		document.getElementById("pgnum").value=pgNo;
         		pgSearchByDate();
         	}
          
          
         var Pagination = {
         
             code: '',
         
             // --------------------
             // Utility
             // --------------------
         
             // converting initialize data
             Extend: function(data) {
                 data = data || {};
                // Pagination.size = data.size || 300; 
                 //console.log(Pagination.size);
               // Pagination.size = Math.ceil(${paginationBean.fullCount}/10) ||100;
               
                Pagination.size = ((${paginationBean.currPage})+4) ||100;
                 /* Pagination.page = data.page || 1; */
                 Pagination.page = ${paginationBean.currPage} || 1;
                 Pagination.step = ((data.step)-4) || 3;
             },
         
             // add pages by number (from [s] to [f])
             Add: function(s, f) {
                 for (var i = s; i < f; i++) {
                     Pagination.code += '<a onclick="dynamic('+i+')">' + i + '</a>';
                 }
             },
         
             // add last page with separator
           /*   Last: function() {
                 Pagination.code += '<i>...</i>';
             },
              */
         	 Last: function() {
                 Pagination.code += '<a onclick="dynamic(((Pagination.page)+1))">'+ ((Pagination.page)+1)+ '</a>'+'<a onclick="dynamic(((Pagination.page)+2))">'+ ((Pagination.page)+2)+ '</a>'+'<a onclick="dynamic(((Pagination.page)+3))">'+ ((Pagination.page)+3)+ '</a>'+'<i>...</i>';
             }, 
         
             // add first page with separator
             First: function() {
             	if(Pagination.page==1){
             		 Pagination.code += '<i>...</i>'+'<a onclick="dynamic(Pagination.page)">'+Pagination.page+'</a>';
         			 
         		 }
             	else{
                 Pagination.code += '<a>1</a>'+'<i>...</i>'+'<a onclick="dynamic(((Pagination.page)-1))">'+((Pagination.page)-1)+'</a>'+'<a onclick="dynamic(Pagination.page)">'+Pagination.page+'</a>';
             	}
             },
         
         
         
             // --------------------
             // Handlers
             // --------------------
         
             // change page
             Click: function() {
                 Pagination.page = +this.innerHTML;
                 Pagination.Start();
                 dynamic(page);
             },
         
             // previous page
             Prev: function() { 
             		
                 Pagination.page--;
                 if (Pagination.page < 1) {
                     Pagination.page = 1;
                 }
                 dynamic(page);
                 Pagination.Start();
                
              }, 
             
             
         
             // next page
             
             
             Next: function() {
                 Pagination.page++;
                 if (Pagination.page > Pagination.size) {
                     Pagination.page = Pagination.size;
                 }
                 dynamic(page);
                 Pagination.Start();
                
              }, 
             
              
         
             // --------------------
             // Script
             // --------------------
         
             // binding pages
             Bind: function() {
                 var a = Pagination.e.getElementsByTagName('a');
                 for (var i = 0; i < a.length; i++) {
                     if (+a[i].innerHTML === Pagination.page) a[i].className = 'current';
                     a[i].addEventListener('click', Pagination.Click, false);
                 }
             },
         
             // write pagination
             Finish: function() {
                 Pagination.e.innerHTML = Pagination.code;
                 Pagination.code = '';
                 Pagination.Bind();
             },
         
             // find pagination type
             Start: function() {
                 if (Pagination.size < Pagination.step * 2 + 6) {
                     Pagination.Add(1, Pagination.size + 1);
                 }
                 else if (Pagination.page < Pagination.step * 2 + 1) {
                     Pagination.Add(1, Pagination.step * 2 + 4);
                     Pagination.Last();
                 }
                 else if (Pagination.page > Pagination.size - Pagination.step * 2) {
                     Pagination.First();
                     Pagination.Add(Pagination.size - Pagination.step * 2 - 2, Pagination.size + 1);
                 }
                 else {
                     Pagination.First();
                     Pagination.Add(Pagination.page - Pagination.step, Pagination.page + Pagination.step + 1);
                     Pagination.Last();
                 }
                 Pagination.Finish();
             },
         
         
         
             // --------------------
             // Initialization
             // --------------------
         
             // binding buttons
             Buttons: function(e) {
                 var nav = e.getElementsByTagName('a');
                 nav[0].addEventListener('click', Pagination.Prev, false);
                 nav[1].addEventListener('click', Pagination.Next, false);
             },
         
             // create skeleton
             Create: function(e) {
         
                 var html = [
                     '<a onclick="previous(${paginationBean.currPage})" style="width:5.5rem;font-weight:bold;font-size:14px;height:1.3rem;padding-bottom:0px;">&#60;&#60; Previous</a>', // previous button
                     '<span></span>',  // pagination container
                     '<a onclick="next(${paginationBean.currPage})" style="width:3.5rem;font-weight:bold;font-size:14px;height:1.3rem;padding-bottom:0px;" id="nxt">Next &#62;&#62;</a>'  // next button
                 ];
         
                 e.innerHTML = html.join('');
                 Pagination.e = e.getElementsByTagName('span')[0];
                 Pagination.Buttons(e);
             },
         
             // init
             Init: function(e, data) {
                 Pagination.Extend(data);
                 Pagination.Create(e);
                 Pagination.Start();
             }
         };
         
         
         
         /* * * * * * * * * * * * * * * * *
         * Initialization
         * * * * * * * * * * * * * * * * */
         
         var init = function() {
             Pagination.Init(document.getElementById('pagination'), {
                 size: 100, // pages size
                 page: 1,  // selected page
                 step: 3   // pages before and after current
             });
         };
         
         document.addEventListener('DOMContentLoaded', init, false);
      </script>

	<%-- <script
		src="${pageContext.request.contextPath}/resources/javascript/payoutSummary.js"></script> --%>
	<!-- Vendor js -->
	<script
		src="https://coderthemes.com/hyper/saas/assets/js/vendor.min.js"></script>
	<!-- Code Highlight js -->
	<!-- <script src="https://coderthemes.com/hyper/saas/assets/vendor/highlightjs/highlight.pack.min.js"></script><script src="https://coderthemes.com/hyper/saas/assets/vendor/clipboard/clipboard.min.js"></script><script src="https://coderthemes.com/hyper/saas/assets/js/hyper-syntax.js"></script>
  
      -->
	<!-- Dashboard App js -->
	<!-- <script src="https://coderthemes.com/hyper/saas/assets/js/pages/demo.dashboard.js"></script> -->
	<script
		src="https://coderthemes.com/hyper/saas/assets/vendor/daterangepicker/moment.min.js"></script>
	<script
		src="https://coderthemes.com/hyper/saas/assets/vendor/daterangepicker/daterangepicker.js"></script>
	<script
		src="https://coderthemes.com/hyper/saas/assets/vendor/datatables.net/js/jquery.dataTables.min.js"></script>
	<script
		src="https://coderthemes.com/hyper/saas/assets/vendor/datatables.net-bs5/js/dataTables.bootstrap5.min.js"></script>
	<script
		src="https://coderthemes.com/hyper/saas/assets/vendor/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
	<script
		src="https://coderthemes.com/hyper/saas/assets/vendor/datatables.net-responsive-bs5/js/responsive.bootstrap5.min.js"></script>
	<script
		src="https://coderthemes.com/hyper/saas/assets/vendor/datatables.net-fixedcolumns-bs5/js/fixedColumns.bootstrap5.min.js"></script>
	<script
		src="https://coderthemes.com/hyper/saas/assets/vendor/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
	<script
		src="https://coderthemes.com/hyper/saas/assets/vendor/datatables.net-buttons-bs5/js/buttons.bootstrap5.min.js"></script>
	<script
		src="https://coderthemes.com/hyper/saas/assets/vendor/datatables.net-buttons/js/buttons.html5.min.js"></script>
	<script
		src="https://coderthemes.com/hyper/saas/assets/vendor/datatables.net-buttons/js/buttons.flash.min.js"></script>
	<script
		src="https://coderthemes.com/hyper/saas/assets/vendor/datatables.net-buttons/js/buttons.print.min.js"></script>
	<script
		src="https://coderthemes.com/hyper/saas/assets/vendor/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
	<script
		src="https://coderthemes.com/hyper/saas/assets/vendor/datatables.net-select/js/dataTables.select.min.js"></script>
	<!-- Datatable Demo Aapp js -->
	<script
		src="https://coderthemes.com/hyper/saas/assets/js/pages/demo.datatable-init.js"></script>
	<!-- App js -->
	<script
		src="https://coderthemes.com/hyper/saas/assets/js/pages/demo.dashboard.js"></script>
	<script src="https://coderthemes.com/hyper/saas/assets/js/app.min.js"></script>
</body>
</html>