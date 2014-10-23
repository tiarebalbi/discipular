$(".alterar-senha").on('click', function() {
	var urlBase = $("body").data("url")
	var senha = $("#nova-senha").val();
	var confirm = $("#confirm-senha").val();
	
	$.ajax({
        type: "POST",
        dataType: 'json',
        url: urlBase + "trocar-senha/" + senha + "/" + confirm,
        complete : function(xhr, status) {
        	$("#nova-senha").val("");
        	$("#confirm-senha").val("");
        	
        	if(senha == confirm) {
        		addMensagemUser("Sua senha foi alterada com sucesso.", "success", "check");
        	} else {
        		addMensagemUser("As senhsa não batem, caso o erro persista favor entrar em contato com o administrador do projeto.", "danger", "times");
        	}
        },
	});
	
	var addMensagemUser = function(mensagem, status, icon) {
		$(".change-password").html("");
		$(".change-password").append(
				'<div class="alert alert-' + status + ' fade in">' +
				'<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' +
				'<i class="fa fa-' + icon + '-circle fa-fw fa-lg"></i>' + mensagem + 
				'</div>')
	}
	
});

