package br.com.discipular.service;

import br.com.discipular.model.Membro;
import br.com.discipular.repository.MembroRepository;

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
	
	MembroRepository getRepositorio();

	Membro salvar(Membro usuario) throws Exception;
	
}
