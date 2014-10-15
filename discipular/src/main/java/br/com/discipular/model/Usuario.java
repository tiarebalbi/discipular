package br.com.discipular.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
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
public class Usuario extends AbstractModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -665605079063752910L;

	@NotNull
	@Column(length = 22)
	private String login;
	
	@NotNull
	private String senha;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;
	
	@OneToMany(mappedBy = "usuario")
	private List<Celula> celulas;
	
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

	public List<Celula> getCelulas() {
		return celulas;
	}

	public void setCelulas(List<Celula> celulas) {
		this.celulas = celulas;
	}

}
