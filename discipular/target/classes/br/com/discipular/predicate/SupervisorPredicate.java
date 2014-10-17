package br.com.discipular.predicate;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import br.com.discipular.model.QSupervisor;

import com.mysema.query.types.Predicate;

public class SupervisorPredicate {

	public static Pageable buscarPaginacao(int pagina,int quantidade) {
		return new PageRequest(pagina, quantidade, Direction.ASC, "usuario.nome");
	}

	public static Predicate buscarPorNomeComFiltro(String nome) {
		QSupervisor condicao = QSupervisor.supervisor;
		return condicao.usuario.nome.startsWithIgnoreCase(nome).or(condicao.usuario.nome.endsWithIgnoreCase(nome));
	}

}
