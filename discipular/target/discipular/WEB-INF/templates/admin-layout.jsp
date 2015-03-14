<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<spring:url value="/" var="path"></spring:url>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta name="author" content="LucasCampos">
	<meta charset="UTF-8">

	<tiles:importAttribute name="titulo" />
	<title>${titulo}</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<!-- Bootstrap core CSS -->
	<link href="http://getbootstrap.com/dist/css/bootstrap.min.css"	rel="stylesheet">
	<link href="http://getbootstrap.com/examples/dashboard/dashboard.css" rel="stylesheet">

	<!-- Fontawesome 4.1.0 -->
<%-- 	<link href="${path}font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet"> --%>
	<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
	
	
	<!-- Bootstrap core JavaScript -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
</head>
<!-- <body style="background-color: #f3f5f9;"> -->
<body style="background-color: #000000;" data-url="${path}">
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Discipular</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
							<span class="thumb-sm avatar pull-left m-t-n-xs m-r-xs"> 
							</span> Lucas Campos <b class="caret"></b>
						</a>
						<ul class="dropdown-menu animated fadeInLeft">
							<li><a href="#"><i class="fa fa-cog"></i> Settings</a></li>
							<li><a href="#"><i class="fa fa-power-off"></i> Logout</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a href="${path}"><i class="fa fa-home"></i> Dashboard</a></li>
					<li><a href="${path}admin/usuario"><i class="fa fa-child"></i> Usuário</a></li>
					<li><a href="#"><i class="fa fa-users"></i> Célula</a></li>
				</ul>
			</div>
			<div class="col-md-10 col-md-offset-2" style="padding-top: 30px;">
				<tiles:insertAttribute name="conteudo" />
			</div>
		</div>
	</div>

	<script type="text/javascript" src="${path}angular.min.js"></script>

</body>
</html>