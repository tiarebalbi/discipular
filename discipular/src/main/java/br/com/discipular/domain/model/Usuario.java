package br.com.discipular.domain.model;

import br.com.discipular.domain.enumetator.TipoRede;
import br.com.discipular.domain.enumetator.TipoUsuario;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Modelo que representa os usuários do sistema
 * 
 * @author Lucas Campos
 * @date 08/09/2014
 */
@Document
public class Usuario extends AbstractDocument {

	private String nome;
	
	private String login;

	private String senha;
	
	private Integer area;

	private String email;

	private String celular;

	@Enumerated(value = EnumType.STRING)
	private TipoUsuario tipo;

	@Enumerated(value = EnumType.STRING)
	private TipoRede rede;

	public Usuario criptografarSenha() {
		this.setSenha(new BCryptPasswordEncoder().encode(this.getSenha()));
		return this;
	}

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

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public TipoRede getRede() {
		return rede;
	}

	public void setRede(TipoRede rede) {
		this.rede = rede;
	}
}
