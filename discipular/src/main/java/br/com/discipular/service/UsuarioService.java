package br.com.discipular.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.discipular.model.Usuario;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

public interface UsuarioService {

	public Usuario salvar(Usuario usuario);
	
	public void excluir(Usuario usuario);
	
	public void excluir(Long id);
	
	public Usuario buscarRegistro(Long id);
	
	public Usuario buscarRegistro(Predicate condicao);
	
	public List<Usuario> buscarTodos();
	
	public List<Usuario> buscarTodos(Predicate condicao);
	
	public List<Usuario> buscarTodos(Predicate condicao, OrderSpecifier<String> ordem);
	
	public Page<Usuario> buscarTodos(Predicate condicao, Pageable paginacao);

	public Page<Usuario> buscarTodos(Pageable paginacao);
	
}
