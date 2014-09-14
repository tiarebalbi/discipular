<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<spring:url value="/" var="path"></spring:url>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Discipular</title>
	
	<link rel="stylesheet" href="${path}resources/font-awesome-4.1.0/css/font-awesome.min.css" />
	<link rel="stylesheet" href="${path}resources/templates/centaurus/css/bootstrap.min.css"  />
	<link rel="stylesheet" href="${path}resources/templates/centaurus/css/nanoscroller.css" />
	<link rel="stylesheet" href="${path}resources/templates/centaurus/css/layout.css" />
	<link rel="stylesheet" href="${path}resources/templates/centaurus/css/elements.css" />
	<link rel="stylesheet" href="${path}resources/templates/centaurus/css/daterangerpicker.css"	/>
	
	<link type="image/x-icon" href="favicon.png" rel="shortcut icon" />
</head>
<body>
	<header class="navbar" id="header-navbar">
		<div class="container">
			<a href="index.html" id="logo" class="navbar-brand"> <img
				src="img/logo.png" alt="" class="normal-logo logo-white" /> <img
				src="img/logo-black.png" alt="" class="normal-logo logo-black" /> <img
				src="img/logo-small.png" alt=""
				class="small-logo hidden-xs hidden-sm hidden" />
			</a>
			<div class="clearfix">
				<button class="navbar-toggle" data-target=".navbar-ex1-collapse"
					data-toggle="collapse" type="button">
					<span class="sr-only">Toggle navigation</span> <span
						class="fa fa-bars"></span>
				</button>
				<div class="nav-no-collapse pull-right" id="header-nav">
					<ul class="nav navbar-nav pull-right">
						<li class="dropdown profile-dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
<!-- 							<img src="img/samples/scarlet-159.png" alt="" />  -->
							<span class="hidden-xs">Administrador</span> <b class="caret"></b>
						</a>
							<ul class="dropdown-menu">
								<li><a href="user-profile.html"><i class="fa fa-user"></i>Profile</a></li>
								<li><a href="#"><i class="fa fa-cog"></i>Administrador se tiver permissaÃµ</a></li>
								<li><a href="#"><i class="fa fa-power-off"></i>Logout</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
		</div>
	</header>
	<div id="page-wrapper" class="container">
		<div class="row">
			<div id="nav-col">
				<section id="col-left" class="col-left-nano">
					<div id="col-left-inner" class="col-left-nano-content">
<!-- 						<div id="user-left-box" class="clearfix hidden-sm hidden-xs"> -->
<!-- 							<img alt="" src="img/samples/scarlet-159.png" /> -->
<!-- 							<div class="user-box"> -->
<!-- 								<span class="name"> Welcome<br /> Scarlett -->
<!-- 							</div> -->
<!-- 						</div> -->
						<div class="collapse navbar-collapse navbar-ex1-collapse"
							id="sidebar-nav">
							<ul class="nav nav-pills nav-stacked">
								<li><a href="#"> <i class="fa fa-user"></i>
										<span>Clientes</span>
								</a></li>
								<li><a href="#" class="dropdown-toggle"> <i
										class="fa fa-users"></i> <span>UsuÃ¡rios</span> <i
										class="fa fa-chevron-circle-right drop-icon"></i>
								</a>
									<ul class="submenu">
										<li><a href="users.html"> User list </a></li>
										<li><a href="user-profile.html"> User profile </a></li>
									</ul></li>
								<li><a href="#" class="dropdown-toggle"> <i
										class="fa fa-usd"></i> <span>CobranÃ§as</span> <i
										class="fa fa-chevron-circle-right drop-icon"></i>
								</a>
									<ul class="submenu">
										<li><a href="faq.html"> FAQ </a></li>
										<li><a href="maps.html"> Google Maps </a></li>
										<li><a href="login.html"> Login </a></li>
										<li><a href="extra-grid.html"> Grid </a></li>
									</ul></li>
							</ul>
						</div>
					</div>
				</section>
			</div>
			<div id="content-wrapper">
			
				<tiles:insertAttribute name="conteudo" />
				
				<footer id="footer-bar" class="row">
					<p id="footer-copyright" class="col-xs-12">
						&copy; 2014 <a href="http://www.adbee.sk/" target="_blank">Adbee digital</a>. Powered by Centaurus Theme.
					</p>
				</footer>
			</div>
		</div>
	</div>

	<script src="${path}resources/templates/centaurus/js/demo-skin-changer.js"></script>
	<script src="${path}resources/templates/centaurus/js/jquery.js"></script>
	<script src="${path}resources/templates/centaurus/js/bootstrap.js"></script>
	<script src="${path}resources/templates/centaurus/js/jquery.nanoscroller.min.js"></script>
	<script src="${path}resources/templates/centaurus/js/demo.js"></script>
	<script src="${path}resources/templates/centaurus/js/moment.min.js"></script>
	<script src="${path}resources/templates/centaurus/js/daterangerpicker.js"></script>
	<script src="${path}resources/templates/centaurus/js/scripts.js"></script>

	<script type="text/javascript">
	$(document).ready(function() {
		$('#reportrange').daterangepicker({
			startDate: moment().subtract('days', 29),
			endDate: moment(),
			minDate: '01/01/2012',
			maxDate: '12/31/2014',
			dateLimit: { days: 60 },
			showDropdowns: true,
			showWeekNumbers: true,
			timePicker: false,
			timePickerIncrement: 1,
			timePicker12Hour: true,
			ranges: {
				'Today': [moment(), moment()],
				'Yesterday': [moment().subtract('days', 1), moment().subtract('days', 1)],
				'Last 7 Days': [moment().subtract('days', 6), moment()],
				'Last 30 Days': [moment().subtract('days', 29), moment()],
				'This Month': [moment().startOf('month'), moment().endOf('month')],
				'Last Month': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
			},
			opens: 'left',
			buttonClasses: ['btn btn-default'],
			applyClass: 'btn-small btn-primary',
			cancelClass: 'btn-small',
			format: 'MM/DD/YYYY',
			separator: ' to ',
			locale: {
				applyLabel: 'Submit',
				fromLabel: 'From',
				toLabel: 'To',
				customRangeLabel: 'Custom Range',
				daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
				monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
				firstDay: 1
			}
		 },
		 function(start, end) {
		  console.log("Callback has been called!");
		  $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
		 }
	  );
	  //Set the initial state of the picker label
	  $('#reportrange span').html(moment().subtract('days', 29).format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));
	});
	</script>
</body>
</html>