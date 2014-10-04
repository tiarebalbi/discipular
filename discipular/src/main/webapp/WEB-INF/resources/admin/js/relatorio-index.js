console.log("oi");



function buscar() {
	var urlBase = $('body').data('url');
	var condicao = $('#condicao').val();
	$.ajax({
        type: 'POST',
        dataType: 'json',
        url: urlBase + "admin/relatorio/find/" + condicao, 
        complete : function(xhr, status) {
        	var response = JSON.parse(xhr.responseText)
        	$('tbody').html("");
        	$('table').find('span').html("");
        	if(response.registros.length > 0) {
        		$(response.registros).each(function (index, value) {
        			$('tbody').append("<tr>" +
        					"<td>" +
        					value.id +
        					"</td>" +
        					"<td>" +
        					value.celula.nome +
        					"</td>" +
        					"<td>" +
        					value.usuario.login +
        					"</td>" +
        					"<td>" +
        					value.dataFormat +
        					"</td>" +
        					"</tr>");
        		});
        	}  else {
        		$('table').append("<span class='label label-danger text-center'>Nenhum registro foi encontrado.</span>");
        	}
        },
	});
}