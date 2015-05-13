package br.com.discipular.domain.model;

import br.com.discipular.domain.enumetator.TipoChamada;

/**
 * Modelo que representa a chamada da célula
 * 
 * @author Lucas Campos
 * @date 08/09/2014
 */
public class Chamada {

	private String nome;

	private TipoChamada tipo;

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
}
