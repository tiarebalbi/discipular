var nomes = [];
var chamadas = [];
var chamada = {}

$(document).ready(function() {
	chamadak();
})

function chamadak() {
	var a;
	a = $(".membro-nome1").data("nome");
	console.log(a);
	
	var b;
	b = $(".membro-nome2").data("nome");
	console.log(b);
	
	var c;
	c = $(".membro-nome3").data("nome");
	console.log(c);

	chamada.push(a);
	chamada.push(b);
	chamada.push(c);
	
	$("#chamada").val(chamada);
	
//	console.log("oi");
//	nomes.push($(".membro-nome").val());
//	console.log(nomes);
//	
//	$.each(".membro-chamada"), function() {
//		chamadas.push(this.val());
//	}
//	
//	chamada.nomes = nomes;
//	chamada.chamadas = chamadas;
//	
//	$("#chamada").val(chamada);
	
//	console.log(chamada);
}

