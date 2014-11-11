package br.com.discipular.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import br.com.discipular.enumerator.TipoUsuario;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@Column(length = 50)
	private String nome;
	
	@NotNull
	@Column(length = 22)
	private String login;
	
	@NotNull
	private String senha;
	
	private String email;
	
	private String telefone;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<Celula> celulas;
	
	@Transient
	private String celula;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

	/**
	 * @return the celula
	 */
	public String getCelula() {
		return celula;
	}

	/**
	 * @param celula the celula to set
	 */
	public void setCelula(String celula) {
		this.celula = celula;
	}

}
