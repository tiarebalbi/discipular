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
				value.celula.nome +
				"</td>" +
				"<td>" +
				value.tipo +
				"</td>" +
				"<td>" +
				value.celular +
				"</td>" +
				"<td>" +
				value.email +
				"</td>" +
				"<td>" +
				value.dataNascimento +
				"</td>" +
				"</tr>");
    		});
	} else {
		$('table').append("<span class='label label-danger text-center'>Nenhum registro foi encontrado.</span>");
	}
}
