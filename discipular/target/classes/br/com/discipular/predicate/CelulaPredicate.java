package br.com.discipular.predicate;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import br.com.discipular.model.Celula;
import br.com.discipular.model.QCelula;
import br.com.discipular.model.Supervisor;

import com.mysema.query.types.Predicate;

/**
 * Condições de busca do modelo {@link Celula}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	08/09/2014 
 */
public class CelulaPredicate {

	public static Pageable buscarPaginacao(int pagina, int quantidade) {
		return new PageRequest(pagina, quantidade, Direction.ASC, "nome");
	}

	public static Predicate buscarPorNomeComFiltro(String nome) {
		QCelula condicao = QCelula.celula;
		return condicao.nome.startsWithIgnoreCase(nome).or(condicao.nome.endsWithIgnoreCase(nome)).and(condicao.apagada.eq(false));
	}

	public static Predicate buscarPorNome(String nome) {
		QCelula condicao = QCelula.celula;
		return condicao.nome.eq(nome);
	}

	public static Predicate buscarPorUsuarioNulo() {
		QCelula condicao = QCelula.celula;
		return condicao.idUsuario.isNull();
	}
	
	public static Predicate buscarPorIdUsuario(Long idUsuario) {
		QCelula condicao = QCelula.celula;
		return condicao.idUsuario.eq(idUsuario);
	}

	public static Predicate buscarPor(Supervisor supervisor) {
		QCelula condicao = QCelula.celula;
		return condicao.supervisor.id.eq(supervisor.getId());
	}

	public static Predicate buscarPorCelulaAtiva() {
		QCelula condicao = QCelula.celula;
		return condicao.apagada.eq(false);
	}

}
