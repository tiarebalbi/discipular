package br.com.discipular.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.discipular.model.Relatorio;
import br.com.discipular.repository.RelatorioRepository;
import br.com.discipular.service.RelatorioService;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

/**
 * Implementação dos métodos de consulta e manipulação do modelo {@link Relatorio}
 * 
 * @author Lucas Campos
 * @since 1.0.0
 * @version 1.0.0
 *
 * 	12/09/2014 
 */
@Service
public class RelatorioServiceImpl implements RelatorioService {

	@Resource
	private RelatorioRepository repository;
	
	public Relatorio salvar(Relatorio relatorio) {
		
		Assert.notNull(relatorio, "Registro nulo, não foi possível salvar este registro.");
		
		return repository.save(relatorio);
		
	}

	public void excluir(Relatorio relatorio) {
		Assert.notNull(relatorio, "Registro nulo, não foi possível excluir este registro.");
		repository.delete(relatorio);
	}
	
	public void excluir(Long id) {
		Assert.notNull(id, "ID nulo, não foi possível excluir este registro.");
		repository.delete(id);
	}

	public Relatorio buscarRegistro(Long id) {
		return repository.findOne(id);
	}

	public Relatorio buscarRegistro(Predicate condicao) {
		return repository.findOne(condicao);
	}

	public List<Relatorio> buscarTodos() {
		return repository.findAll();
	}

	public List<Relatorio> buscarTodos(Predicate condicao) {
		return (List<Relatorio>) repository.findAll(condicao);
	}

	public List<Relatorio> buscarTodos(Predicate condicao, OrderSpecifier<String> ordem) {
		return (List<Relatorio>) repository.findAll(condicao, ordem);
	}

	@Override
	public Page<Relatorio> buscarTodos(Predicate condicao, Pageable paginacao) {
		return repository.findAll(condicao, paginacao);
	}
	
	@Override
	public Page<Relatorio> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao);
	}	
	
}
