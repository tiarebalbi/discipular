<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<spring:url value="/" var="path"></spring:url>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Discipular</title>
	
	<link rel="stylesheet" href="${path}resources/font-awesome-4.1.0/css/font-awesome.min.css" />
	<link rel="stylesheet" href="${path}resources/templates/centaurus/css/bootstrap.min.css"  />
	<link rel="stylesheet" href="${path}resources/templates/centaurus/css/layout.css" />
	<link rel="stylesheet" href="${path}resources/templates/centaurus/css/elements.css" />
	
	<link type="image/x-icon" href="${path}resources/imagens/logo-discipular.png" rel="shortcut icon" />
</head>
<sec:authentication property="principal.username" var="username" />
<body class="theme-red">
	<header class="navbar" id="header-navbar">
		<div class="container">
			<a href="${path}" id="logo" class="navbar-brand"> 
				<img src="${path}resources/imagens/discipular-logo7.png" alt="" class="normal-logo logo-white" /> 
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
							<span class="hidden-xs">${username}</span> <b class="caret"></b>
						</a>
							<ul class="dropdown-menu">
								<li><a href="#"><i class="fa fa-key"></i>Trocar Senha</a></li>
								<li><a href="${path}logout"><i class="fa fa-power-off"></i>Logout</a></li>
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
								<sec:authorize access="hasRole('ROLE_ADMINISTRADOR')">
									<li>
										<a href="${path}admin/usuario"> <i class="fa fa-users"></i><span>Usuários</span></a>
									</li>
									<li>
										<a href="${path}admin/celula"> <i class="fa fa-sitemap"></i><span>Células</span></a>
									</li>
								</sec:authorize>
								<li>
									<a href="${path}membro"> <i class="fa fa-child"></i><span>Membro</span></a>
								</li>
								<li>
									<a href="${path}relatorio"> <i class="fa fa-file-text-o"></i><span>Relatório</span></a>
								</li>
							</ul>
						</div>
					</div>
				</section>
			</div>
			<div id="content-wrapper">
			
				<tiles:insertAttribute name="conteudo" />
				
				<footer id="footer-bar" class="row">
					<p id="footer-copyright" class="col-xs-12">
						&copy; 2014 <a href="//www.twitter.com/lucsgcampos" target="_blank">Lucas Campos</a>.
					</p>
				</footer>
			</div>
		</div>
	</div>

	<script src="${path}resources/templates/centaurus/js/header.js"></script>
	<script src="${path}resources/templates/centaurus/js/jquery.js"></script>
	<script src="${path}resources/templates/centaurus/js/bootstrap.js"></script>
</body>
</html>