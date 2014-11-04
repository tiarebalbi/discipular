package br.com.discipular.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.discipular.model.Chamada;
import br.com.discipular.repository.ChamadaRepository;
import br.com.discipular.service.ChamadaService;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

/**
 * Implementação dos métodos de consulta e manipulação do modelo {@link Chamada}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	06/10/2014 
 */
@Service
public class ChamadaServiceImpl implements ChamadaService {

	@Autowired
	private ChamadaRepository repository;
	
	
	@Override
	public Chamada salvar(Chamada entidade) {
		return this.repository.save(entidade);
	}

	@Override
	public void excluir(Chamada entidade) {
		this.repository.delete(entidade);
	}

	@Override
	public void excluir(Long id) {
		this.repository.delete(id);
	}

	@Override
	public Chamada buscarRegistro(Long id) {
		return this.repository.findOne(id);
	}

	@Override
	public Chamada buscarRegistro(Predicate condicao) {
		return this.repository.findOne(condicao);
	}

	@Override
	public List<Chamada> buscarTodos() {
		return this.repository.findAll();
	}

	@Override
	public Page<Chamada> buscarTodos(Pageable paginacao) {
		return this.repository.findAll(paginacao);
	}

	@Override
	public List<Chamada> buscarTodos(Predicate condicao) {
		return (List<Chamada>) this.repository.findAll(condicao);
	}

	@Override
	public Page<Chamada> buscarTodos(Predicate condicao, Pageable paginacao) {
		return this.repository.findAll(condicao, paginacao);
	}

	@Override
	public List<Chamada> buscarTodos(Predicate condicao, OrderSpecifier<String> ordem) {
		return (List<Chamada>) this.repository.findAll(condicao, ordem);
	}
	
	@Override
	public long count(Predicate condicao) {
		return this.repository.count(condicao);
	}

	@Override
	public List<Chamada> salvar(List<Chamada> chamada) {
		return this.repository.save(chamada);
	}

	
}
