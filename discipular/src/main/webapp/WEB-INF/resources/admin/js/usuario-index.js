function addMenu(xhr) {
	var response = JSON.parse(xhr.responseText)
	$('tbody').html("");
	$('table').find('span').html("");
	if(response.registros.length > 0) {
		$(response.registros).each(function (index, value) {
			$('tbody').append("<tr>" +
					"<td>" +
					value.nome +
					"</td>" +
					"<td class='hidden-xs'>" +
					value.login +
					"</td>" +
					"<td>" +
					value.celula +
					"</td>" +
					"<td>" +
					value.telefone +
					"</td>" +
					"<td class='hidden-xs'>" +
					value.email +
					"</td>" +
					"<td class='hidden-xs'>" +
					'<div class="btn-group"> <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown"> Opções <span class="caret"></span> </button>' +
					'<ul class="dropdown-menu" role="menu">' +
					'<li><a href="' + urlBase + modulo + '/editar/' + value.id + '"><i class="fa fa-pencil-square-o"></i> Editar</a></li>' +
					'<li><a data-toggle="modal" data-target="#trocar-senha-' + value.id + '"><i class="fa fa-key"></i>Alterar Senha</a></li><li class="divider"></li>'+
					'<li><a data-toggle="modal" data-target="#excluir-' + value.id + '"><i class="fa fa-trash-o"></i> Excluir</a></li> </ul>' +
					
					'<div class="modal fade" id="trocar-senha-' + value.id + '" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">' +
					'<div class="modal-dialog"><div class="modal-content"><div class="modal-header" style="background-color: #1a2d69; color:#FFF">' +
					'<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>' +
					'<h4 class="modal-title" id="myModalLabel"><i class="fa fa-warning"></i> Atenção!</h4></div><div class="modal-body"><div class="text-center">' +
					'<i class="fa fa-lock fa-5x"></i></div><div class="text-center"><p style="font-size : 2em;">Tem certeza que deseja resetar a senha do(a) líder <strong>' + value.nome + '</strong>?</p>' +
					'</div></div><div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>' +
					'<a href="' + urlBase + modulo + '/alterar-senha/' + value.id + '" class="btn btn-danger">Resetar</a></div></div></div></div>' +
					
					'</div> <div class="modal fade" id="excluir-' + value.id + '" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">' +
					'<div class="modal-dialog"> <div class="modal-content"> <div class="modal-header" style="background-color: #1a2d69; color:#FFF"> <button type="button" class="close" data-dismiss="modal">' +
					'<span aria-hidden="true">&times;</span><span class="sr-only">Close</span> </button> <h4 class="modal-title" id="myModalLabel"><i class="fa fa-warning"></i> Atenção!</h4> </div>' +
					'<div class="modal-body">  <p>Você realmente deseja excluir o usuário ' + value.nome + '?</p> </div> <div class="modal-footer"> <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>' +
					'<a href=" ' + urlBase + modulo + '/excluir/ '+ value.id + '" class="btn btn-danger">Excluir</a> </div> </div> </div> </div>' +
					"</td>" +
					"</tr>");
		});
	}  else {
		$('table').append("<span class='label label-danger text-center'>Nenhum registro foi encontrado.</span>");
	}
}