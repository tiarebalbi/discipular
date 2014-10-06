function buscar() {
	var urlBase = $('body').data('url');
	var condicao = $('#condicao').val();
	console.log(condicao);
	$.ajax({
        type: 'POST',
        dataType: 'json',
        url: urlBase + "admin/celula/find/" + condicao, 
        complete : function(xhr, status) {
        	var response = JSON.parse(xhr.responseText)
        	$('tbody').html("");
        	$('table').find('span').html("");
        	if(response.registros.length > 0) {
        		$(response.registros).each(function (index, value) {
        			if(value.supervisor != null) {
	        			$('tbody').append("<tr>" +
	        					"<td>" +
	        					value.nome +
	        					"</td>" +
	        					"<td>" +
	        					value.lider +
	        					"</td>" +
	        					"<td>" +
	        					value.supervisor.nome +
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
	        		} else {
	        			$('tbody').append("<tr>" +
	        					"<td>" +
	        					value.nome +
	        					"</td>" +
	        					"<td>" +
	        					value.lider +
	        					"</td>" +
	        					"<td>" +
	        					"" +
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
	        		}
	        		});
        	}  else {
        		$('table').append("<span class='label label-danger text-center'>Nenhum registro foi encontrado.</span>");
        	}
        },
	});
}