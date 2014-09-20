


$(document).ready(function() {
	chamadak();
})

function chamadak() {
	var nomes = [];
	var status = [];
	var chamada = [];
	
	var a;
	a = $(".membro-nome1").data("nome");
	console.log(a);
	
	var b;
	b = $(".membro-nome2").data("nome");
	console.log(b);
	
	var c;
	c = $(".membro-nome3").data("nome");
	console.log(c);

	nomes.push(a);
	nomes.push(b);
	nomes.push(c);
	
	var q = $(".membro-chamada1").val();
	console.log(q);
	
	var w = $(".membro-chamada2").val();
	console.log(w);
	
	e = $(".membro-chamada3").val();
	console.log(e);
	
	status.push(q);
	status.push(w);
	status.push(e);
	
	var i = 0;
	
	for(i; i < nomes.length; i++) {
		
		var chamadax = {nome : nomes[i], 
				tipo : status[i]}
		
		chamada.push(chamadax);
	}
	
	console.log(chamada);
	
	$("#chamada").val(chamada);
	
}

