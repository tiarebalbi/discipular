package br.com.discipular.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.discipular.model.Celula;

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

	public Celula salvar(Celula usuario) throws Exception;
	
	void salvar(List<Celula> celulas);
	
	public void excluir(Long id) throws Exception;
	
	public Celula buscarRegistro(Long id);
	
	public Celula buscarRegistro(Predicate condicao);
	
	public List<Celula> buscarTodos(Predicate condicao);
	
	public Page<Celula> buscarTodos(Predicate condicao, Pageable paginacao);

	long count(Predicate condicao);

}
