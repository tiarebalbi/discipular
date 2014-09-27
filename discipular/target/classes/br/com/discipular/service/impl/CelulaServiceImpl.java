package br.com.discipular.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.discipular.model.Celula;
import br.com.discipular.predicate.CelulaPredicate;
import br.com.discipular.repository.CelulaRepository;
import br.com.discipular.service.CelulaService;

import com.mysema.query.types.OrderSpecifier;
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
		
		if(!isNomeValido(celula)) {
			throw new Exception("Já existe uma célula com este nome, favor utilizar outro nome.");
		}
		
		return this.repository.save(celula);
	}

	@Override
	public void excluir(Celula celula) {
		this.repository.delete(celula);
	}

	@Override
	public void excluir(Long id) {
		this.repository.delete(id);
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
	public List<Celula> buscarTodos() {
		return this.repository.findAll();
	}

	@Override
	public Page<Celula> buscarTodos(Pageable paginacao) {
		return this.repository.findAll(paginacao);
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
	public List<Celula> buscarTodos(Predicate condicao, OrderSpecifier<String> ordem) {
		return (List<Celula>) this.repository.findAll(condicao, ordem);
	}
	
	@Override
	public long count(Predicate condicao) {
		return this.repository.count(condicao);
	}

	private boolean isNomeValido(Celula celula) {
		long qtdeUsuarios = this.count(CelulaPredicate.buscarPorNome(celula.getNome()));
	
		if(qtdeUsuarios == 0) {
			return true;
		} 
		
		Celula retorno = this.buscarRegistro(CelulaPredicate.buscarPorNome(celula.getNome()));
		
		return celula.getId() != null && celula.getId().equals(retorno.getId());
	}
	
}