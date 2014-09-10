package br.com.discipular.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import br.com.discipular.enumerator.TipoMembro;

/**
 * Modelo que representa os integrantes da célula
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	08/09/2014 
 */
@Entity
public class Membro extends AbstractModel {
	//TODO verificar se é necessário ter a variável célula
	@NotNull
	@Column(length = 50)
	private String nome;

	@NotNull
	private LocalDate nascimento;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoMembro tipo;
	
	@Column(length = 50)
	private String email;
	
	@Column(length = 20)
	private String celular;
	
	@Column(length = 100)
	private String endereco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public TipoMembro getTipo() {
		return tipo;
	}

	public void setTipo(TipoMembro tipo) {
		this.tipo = tipo;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}
	
}
