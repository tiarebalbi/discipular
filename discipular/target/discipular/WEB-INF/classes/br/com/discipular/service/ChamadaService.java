package br.com.discipular.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.discipular.model.Chamada;

import com.mysema.query.types.OrderSpecifier;
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
	
	public void excluir(Chamada entidade);
	
	public void excluir(Long id);
	
	public Chamada buscarRegistro(Long id);
	
	public Chamada buscarRegistro(Predicate condicao);
	
	public List<Chamada> buscarTodos();
	
	public Page<Chamada> buscarTodos(Pageable paginacao);
	
	public List<Chamada> buscarTodos(Predicate condicao);
	
	public Page<Chamada> buscarTodos(Predicate condicao, Pageable paginacao);

	public List<Chamada> buscarTodos(Predicate condicao, OrderSpecifier<String> ordem);

	long count(Predicate condicao);

	public List<Chamada> salvar(List<Chamada> chamada);
	
}
