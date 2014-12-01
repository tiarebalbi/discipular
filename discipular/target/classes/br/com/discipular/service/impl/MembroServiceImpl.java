package br.com.discipular.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.discipular.model.Membro;
import br.com.discipular.predicate.MembroPredicate;
import br.com.discipular.repository.MembroRepository;
import br.com.discipular.service.MembroService;

import com.mysema.query.types.Predicate;

/**
 * Implementação dos métodos de consulta e manipulação do modelo {@link Membro}
 * 
 * @author Lucas Campos
 * @since 1.0.0
 * @version 1.0.0
 *
 * 	08/09/2014 
 */
@Service
public class MembroServiceImpl implements MembroService {

	@Resource
	private MembroRepository repository;
	
	@Override
	public Membro salvar(Membro entidade) throws Exception {
		
		if(isFull(entidade) && entidade.getId() == null) {
			throw new Exception("Está célula já está lotada, favor encaminhar o membro para outra célula.");
		}
		
		return repository.save(entidade);
		
	}
	
	@Override
	public void excluir(Long id) {
		Assert.notNull(id, "ID nulo, não foi possível excluir este registro.");
		repository.delete(id);
	}

	@Override
	public Membro buscarRegistro(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Membro buscarRegistro(Predicate condicao) {
		return repository.findOne(condicao);
	}

	@Override
	public List<Membro> buscarTodos(Predicate condicao) {
		return (List<Membro>) repository.findAll(condicao);
	}

	@Override
	public Page<Membro> buscarTodos(Predicate condicao, Pageable paginacao) {
		return repository.findAll(condicao, paginacao);
	}
	
	@Override
	public Page<Membro> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao);
	}
	
	@Override
	public long count(Predicate condicao) {
		return repository.count(condicao);
	}
	
	private boolean isFull(Membro membro)  {
		long qtdeMembros = this.count(MembroPredicate.buscarPor(membro.getCelula()));
		return qtdeMembros >= 14; 
	}
	
}
