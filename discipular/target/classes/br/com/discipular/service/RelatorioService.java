package br.com.discipular.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.discipular.model.Relatorio;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
	
/**
 * Métodos de consulta e manipulação do modelo {@link Relatorio}
 * 
 * @author Lucas Campos
 * @since 1.0.0
 * @version 1.0.0
 *
 * 	12/09/2014 
 */
public interface RelatorioService {
	
	public Relatorio salvar(Relatorio usuario);
	
	public void excluir(Relatorio usuario);
	
	public void excluir(Long id);
	
	public Relatorio buscarRegistro(Long id);
	
	public Relatorio buscarRegistro(Predicate condicao);
	
	public List<Relatorio> buscarTodos();
	
	public Page<Relatorio> buscarTodos(Pageable paginacao);

	public List<Relatorio> buscarTodos(Predicate condicao);
	
	public Page<Relatorio> buscarTodos(Predicate condicao, Pageable paginacao);
	
	public List<Relatorio> buscarTodos(Predicate condicao, OrderSpecifier<String> ordem);

	public List<Relatorio> salvar(List<Relatorio> relatorios);
	
	public List<Relatorio> buscarPorSupervisor(String loginSupervisor);
	
}
