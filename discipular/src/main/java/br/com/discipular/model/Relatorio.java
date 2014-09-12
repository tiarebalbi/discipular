package br.com.discipular.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

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
	
	@NotNull
	private int ask1;
	
	@NotNull
	private int ask2;
	
	@NotNull
	private int ask3;
	
	@NotNull
	private int ask4;
	
	@NotNull
	private int ask5;
	
	@Column(length = 50)
	private String tema;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	@NotNull
	@OneToMany
	private List<Chamada> chamadas;
	
	@NotNull
	private String observacao;

	public int getAsk1() {
		return ask1;
	}

	public void setAsk1(int ask1) {
		this.ask1 = ask1;
	}

	public int getAsk2() {
		return ask2;
	}

	public void setAsk2(int ask2) {
		this.ask2 = ask2;
	}

	public int getAsk3() {
		return ask3;
	}

	public void setAsk3(int ask3) {
		this.ask3 = ask3;
	}

	public int getAsk4() {
		return ask4;
	}

	public void setAsk4(int ask4) {
		this.ask4 = ask4;
	}

	public int getAsk5() {
		return ask5;
	}

	public void setAsk5(int ask5) {
		this.ask5 = ask5;
	}

	public List<Chamada> getChamadas() {
		return chamadas;
	}

	public void setChamadas(List<Chamada> chamadas) {
		this.chamadas = chamadas;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

}
