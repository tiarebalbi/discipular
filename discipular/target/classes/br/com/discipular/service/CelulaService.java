package br.com.discipular.service;

import br.com.discipular.model.Celula;
import br.com.discipular.repository.CelulaRepository;

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
	
	CelulaRepository getRepositorio();

	Celula salvar(Celula usuario) throws Exception;
	
	void excluir(Long id) throws Exception;
	
}
