package br.com.discipular.domain.model;

import java.time.LocalDate;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.discipular.domain.enumetator.DiaSemana;
import br.com.discipular.domain.enumetator.Horario;
import br.com.discipular.domain.enumetator.TipoRede;
import br.com.discipular.domain.serialize.LocalDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * Modelo que representa as células da paróquia
 * 
 * @author Lucas Campos
 * @date 08/09/2014
 */
//@Entity
public class Celula extends AbstractModel {

	@Column(length = 50)
	private String nome;

	@Column(length = 100)
	private String endereco;

	@Enumerated(EnumType.STRING)
	private DiaSemana dia;

	@Enumerated(EnumType.STRING)
	private Horario horario;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dataNascimento;

	@Enumerated(EnumType.STRING)
	private TipoRede tipoRede;

	@ManyToOne
	private Usuario supervisor;

	private boolean apagada;

	private int area;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public DiaSemana getDia() {
		return dia;
	}

	public void setDia(DiaSemana dia) {
		this.dia = dia;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public TipoRede getTipoRede() {
		return tipoRede;
	}

	public void setTipoRede(TipoRede tipoRede) {
		this.tipoRede = tipoRede;
	}

	public Usuario getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Usuario supervisor) {
		this.supervisor = supervisor;
	}

	public boolean isApagada() {
		return apagada;
	}

	public void setApagada(boolean apagada) {
		this.apagada = apagada;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

}