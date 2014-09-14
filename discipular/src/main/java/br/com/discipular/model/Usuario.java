package br.com.discipular.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import br.com.discipular.enumerator.TipoUsuario;

/**
 * Modelo que representa os usuários do sistema
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	08/09/2014 
 */
@Entity
public class Usuario extends AbstractModel {

	@NotNull
	@Column(length = 22)
	private String login;
	
	@NotNull
	@Column(length = 50)
	private String senha;
	
	private TipoUsuario tipo;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	
}
