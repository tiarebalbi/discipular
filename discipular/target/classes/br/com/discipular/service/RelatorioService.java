package br.com.discipular.service;

import java.util.List;

import br.com.discipular.model.Relatorio;
import br.com.discipular.repository.RelatorioRepository;
	
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
	
	Relatorio salvar(Relatorio usuario);
	
	List<Relatorio> salvar(List<Relatorio> relatorios);
	
	List<Relatorio> buscarPorSupervisor(String loginSupervisor);
	
	RelatorioRepository getRepositorio();
	
}
