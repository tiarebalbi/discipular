package br.com.discipular.predicate;

import org.springframework.data.domain.PageRequest;

import br.com.discipular.model.QUsuario;
import br.com.discipular.model.Usuario;

import com.mysema.query.types.Predicate;

/**
 * Condições de busca do modelo {@link Usuario}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	10/09/2014 
 */
public class UsuarioPredicate {

	public static PageRequest buscarPaginacao(int pagina, int tamanho) {
		return new PageRequest(pagina, tamanho);
	}

	public static Predicate buscarPorNomeComFiltro(String login) {
		QUsuario condicao = QUsuario.usuario;
		return condicao.login.startsWith(login).or(condicao.login.endsWith(login));
	}

	public static Predicate buscarPorLogin(String login) {
		QUsuario condicao = QUsuario.usuario;
		return condicao.login.eq(login);
	}

	public static Predicate buscarPorCelula(String celula) {
		QUsuario condicao = QUsuario.usuario;
		return condicao.celula.nome.eq(celula);
	}
	
}
