package br.com.discipular.predicate;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import br.com.discipular.model.Celula;
import br.com.discipular.model.QCelula;

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
		return condicao.nome.startsWith(nome).or(condicao.nome.endsWith(nome));
	}

	public static Predicate buscarPorNome(String nome) {
		QCelula condicao = QCelula.celula;
		return condicao.nome.eq(nome);
	}

	public static Predicate buscarPorUsuarioNulo() {
		QCelula condicao = QCelula.celula;
		return condicao.usuario.isNull();
	}

}
