package br.com.discipular.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Modelo que representa os relatórios das células
 * 
 * @author Lucas Campos
 * @date 08/09/2014
 */
@Document
public class Relatorio extends AbstractDocument {

	private int geral;
	
	private int e5;
	
	private int participacao;
	
	private int tempo;
	
	private int conteudo;
	
	private String tema;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataCriacao;
	
	private String observacao;
	
	private List<Chamada> chamada;
	
	private Usuario usuario;

	public int getGeral() {
		return geral;
	}

	public void setGeral(int geral) {
		this.geral = geral;
	}

	public int getE5() {
		return e5;
	}

	public void setE5(int e5) {
		this.e5 = e5;
	}

	public int getParticipacao() {
		return participacao;
	}

	public void setParticipacao(int participacao) {
		this.participacao = participacao;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public int getConteudo() {
		return conteudo;
	}

	public void setConteudo(int conteudo) {
		this.conteudo = conteudo;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<Chamada> getChamada() {
		return chamada;
	}

	public void setChamada(List<Chamada> chamada) {
		this.chamada = chamada;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
