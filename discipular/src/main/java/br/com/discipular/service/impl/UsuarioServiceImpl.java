package br.com.discipular.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.discipular.context.security.DiscipularPasswordEncoder;
import br.com.discipular.model.Usuario;
import br.com.discipular.predicate.UsuarioPredicate;
import br.com.discipular.repository.UsuarioRepository;
import br.com.discipular.service.UsuarioService;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

/**
 * Implementação dos métodos de consulta e manipulação do modelo {@link Usuario}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	10/09/2014 
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	
	@Override
	public Usuario salvar(Usuario entidade) throws Exception {
		
		if(entidade.getSenha() != null) {
			entidade.setSenha(new DiscipularPasswordEncoder().encode(entidade.getSenha()));
		}
		
		if(!isLoginValido(entidade)) {
				throw new Exception("Já existe um usuário com este login, favor utilizar outro login.");
		}
		
		return this.repository.save(entidade);
	}

	@Override
	public void excluir(Usuario entidade) {
		this.repository.delete(entidade);
	}

	@Override
	public void excluir(Long id) {
		this.repository.delete(id);
	}

	@Override
	public Usuario buscarRegistro(Long id) {
		return this.repository.findOne(id);
	}

	@Override
	public Usuario buscarRegistro(Predicate condicao) {
		return this.repository.findOne(condicao);
	}

	@Override
	public List<Usuario> buscarTodos() {
		return this.repository.findAll();
	}

	@Override
	public Page<Usuario> buscarTodos(Pageable paginacao) {
		return this.repository.findAll(paginacao);
	}

	@Override
	public List<Usuario> buscarTodos(Predicate condicao) {
		return (List<Usuario>) this.repository.findAll(condicao);
	}

	@Override
	public Page<Usuario> buscarTodos(Predicate condicao, Pageable paginacao) {
		return this.repository.findAll(condicao, paginacao);
	}

	@Override
	public List<Usuario> buscarTodos(Predicate condicao, OrderSpecifier<String> ordem) {
		return (List<Usuario>) this.repository.findAll(condicao, ordem);
	}
	
	public long count(Predicate condicao) {
		return this.repository.count(condicao);
	}
	
	private boolean isLoginValido(Usuario usuario) {
		long qtdeUsuarios = this.count(UsuarioPredicate.buscarPorLogin(usuario.getLogin()));
	
		if(qtdeUsuarios == 0) {
			return true;
		} 
		
		Usuario retorno = this.buscarRegistro(UsuarioPredicate.buscarPorLogin(usuario.getLogin()));
		
		return usuario.getId() != null && usuario.getId().equals(retorno.getId());
	}

}
