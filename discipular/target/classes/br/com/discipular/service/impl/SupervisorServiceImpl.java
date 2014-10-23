package br.com.discipular.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.discipular.model.Supervisor;
import br.com.discipular.repository.SupervisorRepository;
import br.com.discipular.service.SupervisorService;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

/**
 * Implementação dos métodos de consulta e manipulação do modelo {@link Supervisor}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 *        Oct 7, 2014
 */
@Service
public class SupervisorServiceImpl implements SupervisorService {

	@Resource
	private SupervisorRepository repository;

	public Supervisor salvar(Supervisor entidade) {
		return repository.save(entidade);
	}

	public void excluir(Supervisor entidade) {
		repository.delete(entidade);
	}

	public void excluir(Long id) {
		Assert.notNull(id, "ID nulo, não foi possível excluir este registro.");
		repository.delete(id);
	}

	public Supervisor buscarRegistro(Long id) {
		return repository.findOne(id);
	}

	public Supervisor buscarRegistro(Predicate condicao) {
		return repository.findOne(condicao);
	}

	public List<Supervisor> buscarTodos() {
		return repository.findAll();
	}

	public List<Supervisor> buscarTodos(Predicate condicao) {
		return (List<Supervisor>) repository.findAll(condicao);
	}

	public List<Supervisor> buscarTodos(Predicate condicao,
			OrderSpecifier<String> ordem) {
		return (List<Supervisor>) repository.findAll(condicao, ordem);
	}

	@Override
	public Page<Supervisor> buscarTodos(Predicate condicao, Pageable paginacao) {
		return repository.findAll(condicao, paginacao);
	}

	@Override
	public Page<Supervisor> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao);
	}

	@Override
	public long count(Predicate condicao) {
		return repository.count(condicao);
	}
	
}
