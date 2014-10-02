package br.com.discipular.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.discipular.model.Usuario;

import com.mysema.query.types.OrderSpecifier;
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

	public Usuario salvar(Usuario usuario) throws Exception;

	public void excluir(Usuario usuario);

	public void excluir(Long id);

	public Usuario buscarRegistro(Long id);

	public Usuario buscarRegistro(Predicate condicao);

	public List<Usuario> buscarTodos();

	public Page<Usuario> buscarTodos(Pageable paginacao);

	public List<Usuario> buscarTodos(Predicate condicao);

	public Page<Usuario> buscarTodos(Predicate condicao, Pageable paginacao);

	public List<Usuario> buscarTodos(Predicate condicao, OrderSpecifier<String> ordem);
	
}
