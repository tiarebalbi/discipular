package br.com.discipular.predicate;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import br.com.discipular.model.Celula;
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
		return new PageRequest(pagina, tamanho, Direction.ASC, "nome");
	}
	
	public static PageRequest buscarPaginacaoAdmin(int pagina, int tamanho) {
		return new PageRequest(pagina, tamanho, Direction.ASC, "nome", "dataNascimento");
	}
	
	public static Predicate buscarPorNomeComFiltro(String nome) {
		QMembro condicao = QMembro.membro;
		return condicao.nome.startsWithIgnoreCase(nome).or(condicao.nome.endsWithIgnoreCase(nome));
	}
	
	public static Predicate buscarPorCelulaComFiltro(String nome) {
		QMembro condicao = QMembro.membro;
		return condicao.celula.nome.startsWithIgnoreCase(nome).or(condicao.celula.nome.endsWithIgnoreCase(nome));
	}

	public static Predicate buscarPorNome(String text) {
		QMembro condicao = QMembro.membro;
		return condicao.nome.eq(text);
	}

	public static Predicate buscarPor(Celula celula) {
		QMembro condicao = QMembro.membro;
		return condicao.celula.eq(celula);
	}	
	
}
