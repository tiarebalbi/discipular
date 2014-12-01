package br.com.discipular.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.discipular.model.Chamada;
import br.com.discipular.repository.ChamadaRepository;
import br.com.discipular.service.ChamadaService;

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
	public List<Chamada> salvar(List<Chamada> chamada) {
		return this.repository.save(chamada);
	}

	@Override
	public List<Chamada> buscarTodos(Predicate condicao) {
		return (List<Chamada>) this.repository.findAll(condicao);
	}

	@Override
	public long count(Predicate condicao) {
		return this.repository.count(condicao);
	}

}
