package br.com.discipular.predicate;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import br.com.discipular.enumerator.TipoUsuario;
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
		return new PageRequest(pagina, tamanho, Direction.ASC, "nome");
	}

	public static Predicate buscarPorNomeComFiltro(String nome, TipoUsuario tipo) {
		QUsuario condicao = QUsuario.usuario;
		return condicao.nome.startsWithIgnoreCase(nome).and(condicao.nome.containsIgnoreCase(nome)).and(condicao.tipo.eq(tipo));
	}

	public static Predicate buscarPorLogin(String login) {
		QUsuario condicao = QUsuario.usuario;
		return condicao.login.eq(login);
	}

	public static Predicate buscarTipo(TipoUsuario tipo) {
		QUsuario condicao = QUsuario.usuario;
		return condicao.tipo.eq(tipo);
	}

	public static Predicate buscarLiderSemCelulaDiferente(Usuario lider) {
		QUsuario condicao = QUsuario.usuario;
		return condicao.tipo.eq(lider.getTipo()).and(condicao.id.ne(lider.getId())).and(condicao.celulas.isEmpty());
	}

	public static Predicate buscarPorTipoSemCelula(TipoUsuario tipo) {
		QUsuario condicao = QUsuario.usuario;
		return condicao.tipo.eq(tipo).and(condicao.celulas.isEmpty());
	}

	public static Predicate buscarTipoEArea(TipoUsuario tipo, Usuario usuario) {
		QUsuario condicao = QUsuario.usuario;
		return condicao.tipo.eq(tipo).and(condicao.area.eq(usuario.getArea()));
	}

	public static Predicate buscarPorNomeComFiltroTipoEArea(String nome, TipoUsuario tipo, Usuario usuario) {
		QUsuario condicao = QUsuario.usuario;
		return condicao.tipo.eq(tipo).and(condicao.area.eq(usuario.getArea())).and(condicao.nome.startsWithIgnoreCase(nome).and(condicao.nome.containsIgnoreCase(nome)));
	}

	public static Predicate buscarTipoEArea(TipoUsuario tipo, int area) {
		QUsuario condicao = QUsuario.usuario;
		return condicao.tipo.eq(tipo).and(condicao.area.eq(area));
	}

}
