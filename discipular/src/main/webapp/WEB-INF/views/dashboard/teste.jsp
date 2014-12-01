<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/" var="path"></spring:url>

<div class="row">
	<div class="col-lg-6">
		<div class="main-box">
			<header class="main-box-header clearfix">
				<h2>Gráfico de Frequência</h2>
			</header>
			<div class="main-box-body clearfix">
				<div class="canvas-holder" data-dados="${dados}" data-labels="${labels}">
					<canvas id="myChart" height="200"></canvas>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="canvas-holder" data-dados="${dados}" data-labels="${labels}">
	<canvas id="myChart" width="640" height="400"></canvas>
</div>

<script src="${path}resources/Chart.min.js"></script>
<script src="${path}resources/user/js/example-graph.js"></script>