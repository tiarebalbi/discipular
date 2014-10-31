<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/" var="path"></spring:url>


Hello Spring!
<div class="canvas-holder" data-dados="${dados}" data-labels="${labels}">
	<canvas id="myChart" width="640" height="400"></canvas>
</div>
<script src="${path}resources/Chart.min.js"></script>
<script>

var dados = $('.canvas-holder').data('dados');
var labels = $('.canvas-holder').data('labels');
var abc = labels.substring(1, labels.length - 1).split(',');


var dataLine = {
	    labels: abc,
	    datasets: [
	        {
	            label: "My First dataset",
	            fillColor: "rgba(220,220,220,0.2)",
	            strokeColor: "rgba(220,220,220,1)",
	            pointColor: "rgba(220,220,220,1)",
	            pointStrokeColor: "#fff",
	            pointHighlightFill: "#fff",
	            pointHighlightStroke: "rgba(220,220,220,1)",
	            data: dados
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


