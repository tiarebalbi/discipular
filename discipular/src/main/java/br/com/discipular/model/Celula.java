package br.com.discipular.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.discipular.enumerator.DiaSemana;
import br.com.discipular.enumerator.Horario;
import br.com.discipular.enumerator.TipoRede;
import br.com.discipular.serializer.LocalDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * Modelo que representa as células da paróquia
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	08/09/2014 
 */
@Entity
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

	@Transient
	private long qtdeMembros;
	
	@ManyToOne
	private Usuario usuario;
	
	private int area;
	
	@Transient
	private String horarioFormatado;

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

	public String getHorarioFormatado() {
		return horarioFormatado;
	}

	public void setHorarioFormatado(String horarioFormatado) {
		this.horarioFormatado = horarioFormatado;
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

	public boolean getApagada() {
		return apagada;
	}

	public void setApagada(boolean apagada) {
		this.apagada = apagada;
	}

	public long getQtdeMembros() {
		return qtdeMembros;
	}

	public void setQtdeMembros(long qtdeMembros) {
		this.qtdeMembros = qtdeMembros;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}
	
}
