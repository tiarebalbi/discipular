package br.com.discipular.predicate;

import java.time.LocalDate;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import br.com.discipular.model.Celula;
import br.com.discipular.model.QRelatorio;
import br.com.discipular.model.Relatorio;
import br.com.discipular.model.Supervisor;
import br.com.discipular.model.Usuario;

import com.mysema.query.types.Predicate;

/**
 * Condições de busca do modelo {@link Relatorio}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	12/09/2014 
 */
public class RelatorioPredicate {

	public static Pageable buscarPaginacao(int pagina, int quantidade) {
		return new PageRequest(pagina, quantidade, Direction.DESC, "data");
	}
	
	public static Predicate buscarPor(String celula) {
		QRelatorio condicao = QRelatorio.relatorio;
		return condicao.celula.nome.startsWithIgnoreCase(celula).or(condicao.celula.nome.containsIgnoreCase(celula));
	}

	public static Predicate buscarPor(Usuario usuario) {
		QRelatorio condicao = QRelatorio.relatorio;
		return condicao.usuario.id.eq(usuario.getId());
	}

	public static Predicate buscarPorUsuarioECelula(Celula celula) {
		QRelatorio condicao = QRelatorio.relatorio;
		return condicao.usuario.id.eq(celula.getUsuario().getId()).and(condicao.celula.eq(celula));
	}

	public static Predicate buscarPorPeriodoE(Usuario usuario, LocalDate inicio, LocalDate fim) {
		QRelatorio condicao = QRelatorio.relatorio;
		return condicao.usuario.id.eq(usuario.getId()).and(condicao.data.between(inicio, fim));
	}

	public static Predicate buscarPor(Supervisor supervisor) {
		QRelatorio condicao = QRelatorio.relatorio;
		return condicao.celula.area.eq(supervisor.getArea());
	}

	public static Predicate buscarPorSupervisorECelula(String celula, Supervisor supervisor) {
		QRelatorio condicao = QRelatorio.relatorio;
		return condicao.celula.area.eq(supervisor.getArea()).and(condicao.celula.nome.startsWithIgnoreCase(celula).or(condicao.celula.nome.containsIgnoreCase(celula)));
	}

}
