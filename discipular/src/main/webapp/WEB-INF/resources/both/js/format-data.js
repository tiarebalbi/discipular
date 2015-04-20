$(document).ready(function() {
	var data = $(".data").data("data");
	if (data != "" && data != null) {
		$(".data").html(data);
	}
});