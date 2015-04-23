package br.com.discipular.domain.predicate;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import br.com.discipular.domain.model.Celula;
import br.com.discipular.domain.model.QCelula;
import br.com.discipular.domain.model.Usuario;

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
		return condicao.nome.startsWithIgnoreCase(nome).or(condicao.nome.containsIgnoreCase(nome)).and(condicao.apagada.eq(false));
	}

	public static Predicate buscarPorNome(String nome) {
		QCelula condicao = QCelula.celula;
		return condicao.nome.eq(nome);
	}

	public static Predicate buscarPorSupervisor(Usuario supervisor) {
		QCelula condicao = QCelula.celula;
		return condicao.supervisor.id.eq(supervisor.getId());
	}

	public static Predicate buscarPorCelulaAtiva() {
		QCelula condicao = QCelula.celula;
		return condicao.apagada.eq(false);
	}

	public static Predicate buscarPorLider(Long idUsuario) {
		QCelula condicao = QCelula.celula;
		return condicao.usuario.id.eq(idUsuario);
	}

	public static Predicate buscarPorCelulaAtivaEArea(int area) {
		QCelula condicao = QCelula.celula;
		return condicao.apagada.eq(false).and(condicao.area.eq(area));
	}

	public static Predicate buscarPorNomeComFiltroCelulaAtivaEArea(String nome, int area) {
		QCelula condicao = QCelula.celula;
		return condicao.apagada.eq(false).and(condicao.area.eq(area)).and(condicao.nome.startsWithIgnoreCase(nome).or(condicao.nome.containsIgnoreCase(nome)));
	}

}