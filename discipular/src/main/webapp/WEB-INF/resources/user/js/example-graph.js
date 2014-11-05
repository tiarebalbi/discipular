$(document).ready(function() {
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
		} ]
	};
	var ctx = document.getElementById("myChart").getContext("2d");
	var myNewChart = new Chart(ctx).Line(dataLine);
});


