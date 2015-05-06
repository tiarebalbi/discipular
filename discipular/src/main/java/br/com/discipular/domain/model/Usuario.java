package br.com.discipular.domain.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import br.com.discipular.domain.enumetator.TipoRede;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.discipular.domain.enumetator.TipoUsuario;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Modelo que representa os usuários do sistema
 * 
 * @author Lucas Campos
 * @date 08/09/2014
 */
@Entity
public class Usuario extends AbstractModel {

	@Column(length = 50)
	private String nome;
	
	@Column(length = 25)
	private String login;

	@Column(length = 70)
	private String senha;
	
	private Integer area;

	@Column(length = 30)
	private String email;

	@Column(length = 35)
	private String celular;
	
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;

	@Enumerated(EnumType.STRING)
	private TipoRede rede;

	@OneToOne(mappedBy = "usuario")
	private Lider lider;

	@OneToOne(mappedBy = "usuario")
	private Supervisor supervisor;
	
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

	public Lider getLider() {
		return lider;
	}

	public void setLider(Lider lider) {
		this.lider = lider;
	}

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}
}
