package br.com.discipular.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.discipular.model.Supervisor;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;


/**
 * Modelo que representa os supervisores do sistema
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	Oct 7, 2014
 * 
 */
public interface SupervisorService {

	Supervisor salvar(Supervisor entidade);

	void excluir(Supervisor entidade);

	void excluir(Long id);

	Supervisor buscarRegistro(Long id);

	Supervisor buscarRegistro(Predicate condicao);

	List<Supervisor> buscarTodos();

	Page<Supervisor> buscarTodos(Pageable paginacao);

	List<Supervisor> buscarTodos(Predicate condicao);

	Page<Supervisor> buscarTodos(Predicate condicao, Pageable paginacao);

	List<Supervisor> buscarTodos(Predicate condicao, OrderSpecifier<String> ordem);

	long count(Predicate condicao);
	
}
