package br.com.discipular.predicate;

import java.time.LocalDate;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import br.com.discipular.model.QRelatorio;
import br.com.discipular.model.Relatorio;
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

	//TODO corrigir condição
	public static Predicate buscarPorNomeComFiltro(String nome) {
		QRelatorio condicao = QRelatorio.relatorio;
		return condicao.data.eq(LocalDate.now());
	}

	public static Predicate buscarPor(Usuario usuario) {
		QRelatorio condicao = QRelatorio.relatorio;
		return condicao.usuario.eq(usuario);
	}

}
