package br.com.discipular.predicate;

import org.springframework.data.domain.PageRequest;

import br.com.discipular.model.QUsuario;

import com.mysema.query.types.Predicate;

public class UsuarioPredicate {

	public static PageRequest buscarPaginacao(int pagina, int tamanho) {
		return new PageRequest(pagina, tamanho);
	}
	
	public static Predicate buscarPorNome(String nome) {
		QUsuario condicao = QUsuario.usuario;
		return condicao.nome.startsWithIgnoreCase(nome).or(condicao.nome.endsWithIgnoreCase(nome));
	}	
	
}
