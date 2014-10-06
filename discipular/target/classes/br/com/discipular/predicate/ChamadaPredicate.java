package br.com.discipular.predicate;

import br.com.discipular.model.QChamada;
import br.com.discipular.model.Relatorio;

import com.mysema.query.types.Predicate;

public class ChamadaPredicate {

	public static Predicate buscarPor(Relatorio relatorio) {
		QChamada condicao = QChamada.chamada;
		return condicao.relatorio.eq(relatorio);
	}
	
}
