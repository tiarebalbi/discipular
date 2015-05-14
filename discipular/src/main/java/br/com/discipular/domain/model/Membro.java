package br.com.discipular.domain.model;


import br.com.discipular.domain.enumetator.TipoMembro;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Modelo que representa os integrantes da célula
 * 
 * @author Lucas Campos
 * @date 08/09/2014
 */
@Document
public class Membro {

	@Id
	private String id;

	private String nome;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;

	private TipoMembro tipo;
	
	private String celular;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public TipoMembro getTipo() {
		return tipo;
	}

	public void setTipo(TipoMembro tipo) {
		this.tipo = tipo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
}
