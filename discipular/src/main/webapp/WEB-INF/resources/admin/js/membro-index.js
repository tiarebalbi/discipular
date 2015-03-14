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
				value.celula.nome +
				"</td>" +
				"<td class='hidden-xs'>" +
				value.tipo +
				"</td>" +
				"<td>" +
				value.celular +
				"</td>" +
				"<td class='hidden-xs'>" +
				value.data +
				"</td>" +
				"</tr>");
    		});
	} else {
		$('table').append("<span class='label label-danger text-center'>Nenhum registro foi encontrado.</span>");
	}
}
