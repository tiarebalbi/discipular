package br.com.discipular.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import br.com.discipular.domain.enumetator.TipoChamada;

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
	
	@Column(length = 50)
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private TipoChamada tipo;
	
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

	public Relatorio getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(Relatorio relatorio) {
		this.relatorio = relatorio;
	}
	
}
