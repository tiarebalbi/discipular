package br.com.discipular.domain.model;

import br.com.discipular.domain.enumetator.TipoChamada;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Modelo que representa a chamada da célula
 * 
 * @author Lucas Campos
 * @date 08/09/2014
 */
public class Chamada {

	private String nome;

	@Enumerated(value = EnumType.STRING)
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
