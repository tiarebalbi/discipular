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
					"<td>" +
					value.usuario.nome +
					"</td>" +
					"<td>" +
//					value.supervisor.usuario.nome +
					value.nome +
					"</td>" +
					"<td>" +
					value.dia +
					"</td>" +
					"<td>" +
					value.horario +
					"</td>" +
					"<td>" +
					value.qtdeMembros +
					"</td>" +
					"</tr>");
    		});
	}  else {
		$('table').append("<span class='label label-danger text-center'>Nenhum registro foi encontrado.</span>");
	}
}