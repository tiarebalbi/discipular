urlBase = $("body").data("url")

$(".alterar-senha").on('click', function() {
	console.log("as");
	var senha = $("#nova-senha").val();
	var confirm = $("#confirm-senha").val();
	
	$.ajax({
        type: "POST",
        dataType: 'json',
        url: urlBase + "/trocar-senha/" + senha + "/" + confirm,
        complete : function(xhr, status) {
        	$("#nova-senha").val("");
        	$("#confirm-senha").val("");
        },
	});
});