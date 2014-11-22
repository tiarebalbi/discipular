package br.com.discipular.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.discipular.model.Membro;

import com.mysema.query.types.Predicate;

/**
 * Métodos de consulta e manipulação do modelo {@link Membro}
 * 
 * @author Lucas Campos
 * @since 1.0.0
 * @version 1.0.0
 *
 * 	08/09/2014 
 */
public interface MembroService {

	public Membro salvar(Membro usuario) throws Exception;
	
	public void excluir(Long id);
	
	public Membro buscarRegistro(Long id);
	
	public Membro buscarRegistro(Predicate condicao);
	
	public Page<Membro> buscarTodos(Pageable paginacao);

	public List<Membro> buscarTodos(Predicate condicao);
	
	public Page<Membro> buscarTodos(Predicate condicao, Pageable paginacao);
	
	long count(Predicate condicao);
	
}
