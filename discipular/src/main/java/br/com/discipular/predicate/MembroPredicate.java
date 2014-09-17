package br.com.discipular.predicate;

import org.springframework.data.domain.PageRequest;

import br.com.discipular.model.Membro;
import br.com.discipular.model.QMembro;

import com.mysema.query.types.Predicate;

/**
 * Condições de busca do modelo {@link Membro}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	08/09/2014 
 */
public class MembroPredicate {

	public static PageRequest buscarPaginacao(int pagina, int tamanho) {
		return new PageRequest(pagina, tamanho);
	}
	
	public static Predicate buscarPorNomeComFiltro(String nome) {
		QMembro condicao = QMembro.membro;
		return condicao.nome.startsWithIgnoreCase(nome).or(condicao.nome.endsWithIgnoreCase(nome));
	}

	public static Predicate buscarPorNome(String text) {
		QMembro condicao = QMembro.membro;
		return condicao.nome.eq(text);
	}	
	
}
