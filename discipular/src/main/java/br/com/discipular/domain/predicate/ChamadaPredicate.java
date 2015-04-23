package br.com.discipular.domain.predicate;

import br.com.discipular.domain.enumetator.TipoChamada;
import br.com.discipular.domain.model.QChamada;
import br.com.discipular.domain.model.Relatorio;

import com.mysema.query.types.Predicate;

public class ChamadaPredicate {

	public static Predicate buscarPor(Relatorio relatorio) {
		QChamada condicao = QChamada.chamada;
		return condicao.relatorio.eq(relatorio);
	}

	public static Predicate buscarPorRelatorioEStatus(Relatorio relatorio, TipoChamada tipo) {
		QChamada condicao = QChamada.chamada;
		return condicao.relatorio.eq(relatorio).and(condicao.tipo.eq(tipo));
	}
	
}
