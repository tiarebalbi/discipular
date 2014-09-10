package br.com.discipular.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.discipular.model.Membro;
import br.com.discipular.repository.MembroRepository;
import br.com.discipular.service.MembroService;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

/**
 * Implementação dos métodos de consulta e manipulação do modelo {@link Membro}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	08/09/2014 
 */
@Service
public class MembroServiceImpl implements MembroService {

	@Resource
	private MembroRepository repository;
	
	public Membro salvar(Membro usuario) {
		
		Assert.notNull(usuario, "Usuário nulo, não foi possível salvar este registro.");
		
		return repository.save(usuario);
		
	}

	public void excluir(Membro usuario) {
		Assert.notNull(usuario, "Usuário nulo, não foi possível excluir este registro.");
		repository.delete(usuario);
	}
	
	public void excluir(Long id) {
		Assert.notNull(id, "ID nulo, não foi possível excluir este registro.");
		repository.delete(id);
	}

	public Membro buscarRegistro(Long id) {
		return repository.findOne(id);
	}

	public Membro buscarRegistro(Predicate condicao) {
		return repository.findOne(condicao);
	}

	public List<Membro> buscarTodos() {
		return repository.findAll();
	}

	public List<Membro> buscarTodos(Predicate condicao) {
		return (List<Membro>) repository.findAll(condicao);
	}

	public List<Membro> buscarTodos(Predicate condicao, OrderSpecifier<String> ordem) {
		return (List<Membro>) repository.findAll(condicao, ordem);
	}

	@Override
	public Page<Membro> buscarTodos(Predicate condicao, Pageable paginacao) {
		return repository.findAll(condicao, paginacao);
	}
	
	@Override
	public Page<Membro> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao);
	}

}
