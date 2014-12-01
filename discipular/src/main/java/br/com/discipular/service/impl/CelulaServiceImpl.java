package br.com.discipular.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.discipular.model.Celula;
import br.com.discipular.repository.CelulaRepository;
import br.com.discipular.service.CelulaService;

import com.mysema.query.types.Predicate;

/**
 * Implementação dos métodos de consulta e manipulação do modelo {@link Celula}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	10/09/2014 
 */
@Service
public class CelulaServiceImpl implements CelulaService {

	@Autowired
	private CelulaRepository repository;
	
	
	@Override
	public Celula salvar(Celula celula) throws Exception {

		if(celula.getSupervisor() == null) {
			celula.setArea(0);
		} else {
			celula.setArea(celula.getSupervisor().getArea());
		}
		
		return this.repository.save(celula);
	}

	@Override
	public void excluir(Long id) throws Exception {
		Celula celula = this.buscarRegistro(id);
		celula.setSupervisor(null);
		celula.setUsuario(null);
		celula.setApagada(true);
		this.salvar(celula);
	}

	@Override
	public Celula buscarRegistro(Long id) {
		return this.repository.findOne(id);
	}

	@Override
	public Celula buscarRegistro(Predicate condicao) {
		return this.repository.findOne(condicao);
	}

	@Override
	public List<Celula> buscarTodos(Predicate condicao) {
		return (List<Celula>) this.repository.findAll(condicao);
	}

	@Override
	public Page<Celula> buscarTodos(Predicate condicao, Pageable paginacao) {
		return this.repository.findAll(condicao, paginacao);
	}

	@Override
	public long count(Predicate condicao) {
		return this.repository.count(condicao);
	}

	@Override
	public void salvar(List<Celula> celulas) {
		this.repository.save(celulas);
	}
	
}