package br.com.discipular.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.discipular.model.Celula;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

/**
 * Métodos de consulta e manipulação do modelo {@link Celula}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	10/09/2014 
 */
public interface CelulaService {

	public Celula salvar(Celula usuario);
	
	public void excluir(Celula usuario);
	
	public void excluir(Long id);
	
	public Celula buscarRegistro(Long id);
	
	public Celula buscarRegistro(Predicate condicao);
	
	public List<Celula> buscarTodos();
	
	public Page<Celula> buscarTodos(Pageable paginacao);
	
	public List<Celula> buscarTodos(Predicate condicao);
	
	public Page<Celula> buscarTodos(Predicate condicao, Pageable paginacao);

	public List<Celula> buscarTodos(Predicate condicao, OrderSpecifier<String> ordem);
	
}
