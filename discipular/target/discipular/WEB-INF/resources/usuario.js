'strict'	

var urlBase = $('body').data('url');
var paginas = $(".table-responsive").data('pagina');
var marker = 0;

$(".go").on('click', function () {
	var condicao = $('.find').val();
	if(condicao.length > 0) {
		$('.anterior').addClass('disabled');
		$('.proximo').addClass('disabled');
		$.ajax({
	        type: "POST",
	        dataType: 'json',
	        url: urlBase + "admin/usuario/find/" + condicao,
	        complete : function(xhr, status) {
	        	addMenu(xhr);
	        },
		});
	} else {
		window.location = urlBase + "admin/usuario";
	}
});

$('.proximo').on('click', function() {
	$('.anterior').removeClass('disabled');
	if(marker < paginas - 1) {
		marker++;
		$.ajax({
	        type: "POST",
	        dataType: 'json',
	        url: urlBase + "admin/usuario/next",
	        complete : function(xhr, status) {
	    		addMenu(xhr);
	        },
		});
		if(marker >= paginas - 1) {
			$(this).addClass('disabled');
		}
	} 
});

$('.anterior').on('click', function() {
	$('.proximo').removeClass('disabled');
	if(marker > 0) {			
		marker--;
		$.ajax({
	        type: "POST",
	        dataType: 'json',
	        url: urlBase + "admin/usuario/previous",
	        complete : function(xhr, status) {
	    		addMenu(xhr);
	        },
	    });
		if(marker < 1) {
			$(this).addClass('disabled');
		}
	}
});

function addMenu(xhr) {
	var response1 = JSON.parse(xhr.responseText)
	$('tbody').html("");
	$('table').find('span').html("");
	if(response1.usuarios.length > 0) {
		$(response1.usuarios).each(function (index, value) {
			$('tbody').append("<tr><td># " + value.id + "</td><" +
					"<td>" + value.nome + "</td>" +
					"<td>" + value.celular + "</td>" +
					"<td>" +
					"<div class='btn-group'>" +
					"<button type='button' class='btn btn-primary btn-sm dropdown-toggle' data-toggle='dropdown'> Opções <span class='caret'></span></button>" +
					"<ul class='dropdown-menu' role='menu'>" +	
					"<li><a href='" + urlBase + "admin/usuario/editar/" + value.id + "'><i class='fa fa-pencil-square-o'></i> Editar</a></li>" +
					"<li class='divider'></li>" +		
					"<li><a data-toggle='modal' data-target='#excluir-" + value.id + "'><i class='fa fa-trash-o'></i> Excluir</a></li>" +
					"</ul></div><div class='modal fade' id='excluir-" + value.id + "' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'>" +
					"<div class='modal-dialog'><div class='modal-content'><div class='modal-header'>" +
					"<button type='button' class='close' data-dismiss='modal'>" +
					"<span aria-hidden='true'>&times;</span><span class='sr-only'>Close</span></button>" + 
					"<h4 class='modal-title' id='myModalLabel'>Atenção</h4></div>" +
					"<div class='modal-body'><p>Você realmente deseja excluir o usuário " + value.nome + "?</p></div>" +
					"<div class='modal-footer'>	<button type='button' class='btn btn-default' data-dismiss='modal'>Cancelar</button>" +
					"<a href='" + urlBase + "admin/usuario/excluir/" + value.id + "' class='btn btn-danger'>Excluir</a></div></div></div></div>" +
					"</td>" +
					"/tr>");
		});
	} else {
		$('table').append("<span class='label label-danger text-center'>Nenhum registro foi encontrado.</span>");
	}
}