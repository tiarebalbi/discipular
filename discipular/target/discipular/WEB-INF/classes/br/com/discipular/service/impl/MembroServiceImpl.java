package br.com.discipular.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.discipular.model.Membro;
import br.com.discipular.predicate.MembroPredicate;
import br.com.discipular.repository.MembroRepository;
import br.com.discipular.service.MembroService;

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
	
	public MembroRepository getRepositorio() {
		return this.repository;
	}
	
	@Override
	public Membro salvar(Membro entidade) throws Exception {
		
		if(isFull(entidade) && entidade.getId() == null) {
			throw new Exception("Está célula já está lotada, favor encaminhar o membro para outra célula.");
		}
		
		return repository.save(entidade);
		
	}
	
	public boolean isFull(Membro membro)  {
		long qtdeMembros = this.repository.count(MembroPredicate.buscarPor(membro.getCelula()));
		return qtdeMembros >= 14; 
	}

	public void setRepository(MembroRepository repository) {
		this.repository = repository;
	}
	
}
