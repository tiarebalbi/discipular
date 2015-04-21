package br.com.discipular.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.discipular.enumerator.TipoUsuario;
import br.com.discipular.model.Celula;
import br.com.discipular.model.Usuario;
import br.com.discipular.predicate.UsuarioPredicate;
import br.com.discipular.repository.UsuarioRepository;
import br.com.discipular.service.UsuarioService;

/**
 * Implementação dos métodos de consulta e manipulação do modelo {@link Usuario}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	10/09/2014 
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	
	@Override
	public Usuario salvar(Usuario usuario) throws Exception {
		Assert.notNull(usuario.getArea(), "Favor preencher o campo área.");
		
		if(!isLoginValido(usuario)) {
			throw new Exception("Já existe um líder/supervisor cadastrado com este login, favor utilizar outro login.");
		}
		
		if(usuario.getSenha() != null && usuario.getId() == null) {
			usuario.criptografarSenha();
		}
		
		usuario.setEmail(usuario.getEmail().toLowerCase());
		
		return this.repository.save(usuario);
	}

	
	private boolean isLoginValido(Usuario usuario) {
		usuario.setLogin(usuario.getLogin().trim());
		
		long qtdeUsuarios = this.repository.count(UsuarioPredicate.buscarPorLogin(usuario.getLogin()));

		if(qtdeUsuarios == 0) return true;
		
		Usuario retorno = this.repository.findOne(UsuarioPredicate.buscarPorLogin(usuario.getLogin()));
		
		return usuario.getId() != null && usuario.getId().equals(retorno.getId());
	}
	
	@Override
	public List<Usuario> buscarLideresSemCelula(Celula celula) {
		List<Usuario> lideres = new ArrayList<>();
		
		if(celula.getUsuario() != null) {
			lideres.add(celula.getUsuario());
			lideres.addAll((Collection<? extends Usuario>) this.repository.findAll(UsuarioPredicate.buscarLiderSemCelulaDiferente(celula.getUsuario())));
		} else {
			lideres.addAll((Collection<? extends Usuario>) this.repository.findAll(UsuarioPredicate.buscarPorTipoSemCelula(TipoUsuario.LIDER)));
		}
		
		return lideres;
	}


	@Override
	public UsuarioRepository getRepositorio() {
		return this.repository;
	}

	public void setRepositorio(UsuarioRepository repository) {
		this.repository = repository;
	}

}
