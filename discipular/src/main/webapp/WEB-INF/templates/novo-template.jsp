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
	<link rel="stylesheet" href="${path}resources/templates/centaurus/css/bootstrap-wizard.css" />
	<link rel="stylesheet" href="${path}resources/templates/centaurus/css/select2.css" />
	
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
								<li><button id="open-wizard" class="btn btn-primary">Open wizard</button></li>
							</ul>
						</li>
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

	<!-- MODAL -->
	<div class="modal wizard-modal in" role="dialog"
		style="margin-top: -232.5px; width: 750px; margin-left: -375px; display: none;"
		aria-hidden="true">
		<div class="wizard-modal-header modal-header">
			<button class="wizard-close close" type="button">x</button>
			<h3 class="wizard-title">Create Server</h3>
			<span class="wizard-subtitle"></span>
		</div>
		<form>
			<div class="wizard-cards">
				<div class="wizard-card-container">
					<div class="wizard-card" data-onvalidated="setServerName"
						data-cardname="name" style="height: 300px; display: block;">
						<h3>
							<span>Name &amp; FQDN</span>
						</h3>
						<div class="wizard-input-section">
							<p>To begin, please enter the IP of your server or the
								fully-qualified name.</p>
							<div class="form-group">
								<label for="exampleInputEmail1">Email address</label> <input
									type="text" class="form-control" id="new-server-fqdn"
									placeholder="FQDN or IP" data-validate="fqdn_or_ip">
							</div>
						</div>
						<div class="wizard-input-section">
							<p>Optionally, give this server a label.</p>
							<div class="form-group">
								<label for="exampleInputEmail1">Email address</label> <input
									type="text" class="form-control" id="new-server-name"
									placeholder="Server name (optional)" data-validate="">
							</div>
						</div>
					</div>
				</div>
				<div class="wizard-modal-footer">
					<div class="wizard-buttons-container">
						<button class="btn wizard-cancel wizard-close" type="button"
							style="display: inline-block;">Cancel</button>
						<div class="btn-group-single pull-right">
							<button class="btn wizard-back disabled" type="button">Back</button>
							<button class="btn wizard-next btn-primary" type="button">Next</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- FIM MODAL -->
	<script src="${path}resources/templates/centaurus/js/jquery.js"></script>
	<script src="${path}resources/templates/centaurus/js/scripts.js"></script>
	<script src="${path}resources/templates/centaurus/js/header.js"></script>
	
	<script src="${path}resources/templates/centaurus/js/bootstrap.js"></script>
	<script src="${path}resources/templates/centaurus/js/bootstrap-wizard.js"></script>
	<script src="${path}resources/templates/centaurus/js/select2.min.js"></script>
	
	
	<script type="text/javascript">
	function setServerName(card) {
		var host = $("#new-server-fqdn").val();
		var name = $("#new-server-name").val();
		var displayName = host;
	
		if (name) {
			displayName = name + " ("+host+")";
		};
	
		card.wizard.setSubtitle(displayName);
		card.wizard.el.find(".create-server-name").text(displayName);
	}
	
	function fqdn_or_ip(el) {
		var val = el.val();
		ret = {
			status: true
		};
		if (!validateFQDN(val)) {
			if (!validateIP(val)) {
				ret.status = false;
				ret.msg = "Invalid IP address or FQDN";
			}
		}
		return ret;
	}
	
	
	$(function() {
		
		$('#sel2').select2();
	
		$.fn.wizard.logging = false;
	
		var wizard = $("#wizard-demo").wizard({
			showCancel: true
		});
	
		//$(".chzn-select").chosen();
	
		wizard.el.find(".wizard-ns-select").change(function() {
			wizard.el.find(".wizard-ns-detail").show();
		});
	
		wizard.el.find(".create-server-service-list").change(function() {
			var noOption = $(this).find("option:selected").length == 0;
			wizard.getCard(this).toggleAlert(null, noOption);
		});
	
		wizard.cards["name"].on("validated", function(card) {
			var hostname = card.el.find("#new-server-fqdn").val();
		});
	
		wizard.on("submit", function(wizard) {
			var submit = {
				"hostname": $("#new-server-fqdn").val()
			};
	
			setTimeout(function() {
				wizard.trigger("success");
				wizard.hideButtons();
				wizard._submitting = false;
				wizard.showSubmitCard("success");
				wizard.updateProgressBar(0);
			}, 2000);
		});
	
		wizard.on("reset", function(wizard) {
			wizard.setSubtitle("");
			wizard.el.find("#new-server-fqdn").val("");
			wizard.el.find("#new-server-name").val("");
		});
	
		wizard.el.find(".wizard-success .im-done").click(function() {
			wizard.reset().close();
		});
	
		wizard.el.find(".wizard-success .create-another-server").click(function() {
			wizard.reset();
		});
	
		$(".wizard-group-list").click(function() {
			alert("Disabled for demo.");
		});
	
		$("#open-wizard").click(function() {
			wizard.show();
		});
	
		wizard.show();
	});
	</script>
</body>
</html>