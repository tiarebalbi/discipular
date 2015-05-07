package br.com.discipular.domain.predicate;

import java.time.LocalDate;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import br.com.discipular.domain.model.Celula;
import br.com.discipular.domain.model.QRelatorio;
import br.com.discipular.domain.model.Relatorio;
import br.com.discipular.domain.model.Usuario;

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
		return condicao.usuario.id.eq(celula.getLider().getId()).and(condicao.celula.eq(celula));
	}

	public static Predicate buscarPorPeriodoE(Long idUsuario, LocalDate inicio, LocalDate fim) {
		QRelatorio condicao = QRelatorio.relatorio;
		return condicao.usuario.id.eq(idUsuario).and(condicao.data.between(inicio, fim));
	}

	public static Predicate buscarPorSupervisor(Usuario supervisor) {
		QRelatorio condicao = QRelatorio.relatorio;
		return condicao.celula.area.eq(supervisor.getArea());
	}

	public static Predicate buscarPorSupervisorECelula(String celula, Usuario supervisor) {
		QRelatorio condicao = QRelatorio.relatorio;
		return condicao.celula.area.eq(supervisor.getArea()).and(condicao.celula.nome.startsWithIgnoreCase(celula).or(condicao.celula.nome.containsIgnoreCase(celula)));
	}

}
