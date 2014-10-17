'strict'

var urlBase = $('body').data('url');
var paginas = $(".table-responsive").data('pagina');
var marker = 0;
var modulo = $(".table-responsive").data('modulo');

function buscar() {
	var condicao = $('#condicao').val();
	if(condicao.length > 0) {
		$('.anterior').parent().addClass('disabled');
		$('.proximo').parent().addClass('disabled');
		$.ajax({
	        type: 'POST',
	        dataType: 'json',
	        url: urlBase + "admin/" + modulo + "/find/" + condicao, 
	        complete : function(xhr, status) {
				addMenu(xhr);
	        },
		});
	} else {
		window.location = urlBase + "admin/" + modulo;
	}
}

$('.proximo').on('click', function(e) {
	e.preventDefault();
	if(marker < paginas - 1 && !$(this).parent().hasClass('disabled')) {
		$('.anterior').parent().removeClass('disabled');
		marker++;
		$.ajax({
	        type: "POST",
	        dataType: 'json',
	        url: urlBase + "admin/" + modulo + "/next",
	        complete : function(xhr, status) {
	    		addMenu(xhr);
	        },
		});
	}
	if(marker >= paginas - 1) {
		$(this).parent().addClass('disabled');
	}
});

$('.anterior').on('click', function(e) {
	e.preventDefault();
	if(marker > 0 && !$(this).parent().hasClass('disabled')) {
		$('.proximo').parent().removeClass('disabled');
		marker--;
		$.ajax({
	        type: "POST",
	        dataType: 'json',
	        url: urlBase + "admin/" + modulo + "/previous",
	        complete : function(xhr, status) {
	    		addMenu(xhr);
	        },
	    });
	}
	if(marker < 1) {
		$(this).parent().addClass('disabled');
	}
});