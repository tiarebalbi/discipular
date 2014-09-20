package br.com.discipular.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.discipular.enumerator.TipoChamada;

/**
 * Modelo que representa a chamada da célula
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	08/09/2014 
 */
@Entity
public class Chamada extends AbstractModel {
	
	@NotNull
	private String nome;
	
	@NotNull
	private TipoChamada tipo;
	
	private String observacao;
	
	@ManyToOne
	private Relatorio relatorio;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoChamada getTipo() {
		return tipo;
	}

	public void setTipo(TipoChamada tipo) {
		this.tipo = tipo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
