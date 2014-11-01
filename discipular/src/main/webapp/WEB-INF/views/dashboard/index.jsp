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
<script>
	var dados = $('.canvas-holder').data('dados');
	var labels = $('.canvas-holder').data('labels');
	var abc = labels.substring(1, labels.length - 1).split(',');

	var dataLine = {
		labels : abc,
		datasets : [ {
			label : "My First dataset",
			fillColor : "rgba(220,220,220,0.2)",
			strokeColor : "#1fbaa0",
			pointColor : "rgba(220,220,220,1)",
			pointStrokeColor : "#1fbaa0",
			pointHighlightFill : "#1fbaa0",
			pointHighlightStroke : "rgba(220,220,220,1)",
			data : dados
		}
		// 	        ,
		// 	        {
		// 	            label: "My Second dataset",
		// 	            fillColor: "rgba(151,187,205,0.2)",
		// 	            strokeColor: "rgba(151,187,205,1)",
		// 	            pointColor: "rgba(151,187,205,1)",
		// 	            pointStrokeColor: "#fff",
		// 	            pointHighlightFill: "#fff",
		// 	            pointHighlightStroke: "rgba(151,187,205,1)",
		// 	            data: [28, 48, 40, 19, 86, 27, 90]
		// 	        }
		]
	};
	var ctx = document.getElementById("myChart").getContext("2d");
	var myNewChart = new Chart(ctx).Line(dataLine);
</script>


