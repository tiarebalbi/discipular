function addMenu(xhr) {
	var response = JSON.parse(xhr.responseText)
	$('tbody').html("");
	$('table').find('span').html("");
	if(response.registros.length > 0) {
		$(response.registros).each(function (index, value) {
			var responsavel = (value.usuario == "null" ? value.usuario.login : "");
			$('tbody').append("<tr>" +
					"<td>" +
					value.celula.nome +
					"</td>" +
					"<td class='hidden-xs'>" +
					responsavel +
					"</td>" +
					"<td>" +
					value.dataFormat +
					"</td>" +
					"</tr>");
		});
	}  else {
		$('table').append("<span class='label label-danger text-center'>Nenhum registro foi encontrado.</span>");
	}
}
