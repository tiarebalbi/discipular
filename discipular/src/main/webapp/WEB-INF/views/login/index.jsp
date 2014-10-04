<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/" var="path"></spring:url>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Discipular {{2 + 2}}</title>

	<link rel="stylesheet" href="${path}resources/both/login/css/login-bootstrap.css" />
	<link rel="stylesheet" href="${path}resources/both/login/css/login-layout.css" />
	<link rel="stylesheet" href="${path}resources/both/login/css/login-elements.css" />
	<link rel="stylesheet" href="${path}resources/both/font-awesome-4.1.0/css/font-awesome.min.css" />
	
	<link type="image/x-icon" href="${path}resources/imagens/logo-discipular.png" rel="shortcut icon" />
</head>
<body id="login-page">
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<div id="login-box">
					<div class="row">
						<div class="col-xs-12">
							<header id="login-header">
								<div id="login-logo">
									<img src="${path}resources/imagens/discipular-logo8.png" alt="" />
								</div>
							</header>
							<div id="login-box-inner">
								<form method="post" role="form" action="${path}logar">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-user"></i></span>
										<input class="form-control" name="discipular_username" required type="text" placeholder="Login" />
									</div>
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-key"></i></span>
										<input  class="form-control" name="discipular_password" required type="password" placeholder="Password" />
									</div>
<!-- 									<div id="remember-me-wrapper"> -->
<!-- 										<div class="row"> -->
<!-- 											<div class="col-xs-6"> -->
<!-- 												<div class="checkbox-nice"> -->
<!-- 													<input type="checkbox" id="remember-me" checked="checked" /> -->
<!-- 													<label for="remember-me"> Remember me </label> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 											<a href="#" id="login-forget-link" class="col-xs-6">Forgot password? </a> -->
<!-- 										</div> -->
<!-- 									</div> -->
									<div class="row">
										<div class="col-xs-12">
											<button type="submit" class="btn btn-success col-xs-12">Login</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>