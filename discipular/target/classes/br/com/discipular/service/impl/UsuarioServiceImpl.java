package br.com.discipular.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.discipular.model.Usuario;
import br.com.discipular.repository.UsuarioRepository;
import br.com.discipular.service.UsuarioService;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Resource
	private UsuarioRepository repository;
	
	public Usuario salvar(Usuario usuario) {
		
		Assert.notNull(usuario, "Usuário nulo, não foi possível salvar este registro.");
		
		return repository.save(usuario);
		
	}

	public void excluir(Usuario usuario) {
		Assert.notNull(usuario, "Usuário nulo, não foi possível excluir este registro.");
		repository.delete(usuario);
	}
	
	public void excluir(Long id) {
		Assert.notNull(id, "ID nulo, não foi possível excluir este registro.");
		repository.delete(id);
	}

	public Usuario buscarRegistro(Long id) {
		return repository.findOne(id);
	}

	public Usuario buscarRegistro(Predicate condicao) {
		return repository.findOne(condicao);
	}

	public List<Usuario> buscarTodos() {
		return repository.findAll();
	}

	public List<Usuario> buscarTodos(Predicate condicao) {
		return (List<Usuario>) repository.findAll(condicao);
	}

	public List<Usuario> buscarTodos(Predicate condicao, OrderSpecifier<String> ordem) {
		return (List<Usuario>) repository.findAll(condicao, ordem);
	}

	@Override
	public Page<Usuario> buscarTodos(Predicate condicao, Pageable paginacao) {
		return repository.findAll(condicao, paginacao);
	}
	
	@Override
	public Page<Usuario> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao);
	}

}
