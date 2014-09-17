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
	
	<!-- 	FORM	 -->
	<link rel="stylesheet" href="${path}resources/templates/centaurus/css/bootstrap-timepicker.css" />
	<link rel="stylesheet" href="${path}resources/templates/centaurus/css/datepicker.css" />
	<link rel="stylesheet" href="${path}resources/templates/centaurus/css/select2.css" />
	<!-- 	FIM FORM -->
	
	
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
	
	<!-- 	FORM	 -->
	<script src="${path}resources/templates/centaurus/js/bootstrap-datepicker.js"></script>
	<script src="${path}resources/templates/centaurus/js/bootstrap-timepicker.min.js"></script>
	<script src="${path}resources/templates/centaurus/js/hogan.js"></script>
	<script src="${path}resources/templates/centaurus/js/jquery.maskedinput.min.js"></script>
	<script src="${path}resources/templates/centaurus/js/jquery.pwstrength.js"></script>
	<script src="${path}resources/templates/centaurus/js/select2.js"></script>
	<script src="${path}resources/templates/centaurus/js/typeahead.min.js"></script>
	<!-- 	FIM FORM -->
</body>
</html>