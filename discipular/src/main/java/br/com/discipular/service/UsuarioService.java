package br.com.discipular.service;

import java.util.List;

import br.com.discipular.domain.model.Celula;
import br.com.discipular.domain.model.Usuario;
import br.com.discipular.domain.repository.UsuarioRepository;

/**
 * Métodos de consulta e manipulação do modelo {@link Usuario}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	10/09/2014 
 */
public interface UsuarioService {
	
	UsuarioRepository getRepositorio();

	Usuario salvar(Usuario usuario) throws Exception;

	List<Usuario> buscarLideresSemCelula(Celula celula);

}
