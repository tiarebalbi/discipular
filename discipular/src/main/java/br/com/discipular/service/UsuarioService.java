package br.com.discipular.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.discipular.model.Usuario;

import com.mysema.query.types.Predicate;

/**
 * Métodos de consulta e manipulação do modelo {@link Usuario}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	10/09/2014 
 */
public interface UsuarioService {

	Usuario salvar(Usuario usuario) throws Exception;

	void excluir(Long id);

	Usuario buscarRegistro(Long id);

	Usuario buscarRegistro(Predicate condicao);

	List<Usuario> buscarTodos(Predicate condicao);

	Page<Usuario> buscarTodos(Predicate condicao, Pageable paginacao);

	long count(Predicate buscarPorCelula);
	
}
