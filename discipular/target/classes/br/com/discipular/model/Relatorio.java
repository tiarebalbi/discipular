package br.com.discipular.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.discipular.serializer.LocalDateSerializer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Modelo que representa os relatórios das células
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	08/09/2014 
 */
@Entity
public class Relatorio extends AbstractModel {
	
	private int geral;
	
	private int e5;
	
	private int participacao;
	
	private int tempo;
	
	private int conteudo;
	
	private String tema;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate data;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dataCriacao;
	
	@Column(length = 500)
	private String observacao;
	
	@ManyToOne
	private Celula celula;
	
	@OneToMany(mappedBy = "relatorio", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Chamada> chamada;
	
	@ManyToOne
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

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Celula getCelula() {
		return celula;
	}

	public void setCelula(Celula celula) {
		this.celula = celula;
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
