package br.com.discipular.service;

import java.util.List;

import br.com.discipular.model.Chamada;

import com.mysema.query.types.Predicate;

/**
 * Métodos de consulta e manipulação do modelo {@link Chamada}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	06/10/2014 
 */
public interface ChamadaService {

	public Chamada salvar(Chamada entidade);
	
	public List<Chamada> salvar(List<Chamada> chamada);
	
	public List<Chamada> buscarTodos(Predicate condicao);

	long count(Predicate condicao);
	
}
