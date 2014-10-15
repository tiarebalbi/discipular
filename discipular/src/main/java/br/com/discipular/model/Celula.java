package br.com.discipular.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.discipular.enumerator.DiaSemana;
import br.com.discipular.enumerator.Horario;
import br.com.discipular.enumerator.TipoRede;


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
	
	@NotNull
	@Column(length = 50)
	private String nome;

	@NotNull
	@Column(length = 100)
	private String endereco;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private DiaSemana dia;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Horario horario;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	private Long idUsuario;
	
	@Enumerated(EnumType.STRING)
	private TipoRede tipoRede;
	
	@ManyToOne
	private Supervisor supervisor;
	
	private boolean apagada;
	
	@Transient
	private long qtdeMembros;
	
	@Transient
	private String lider;
	
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

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public long getQtdeMembros() {
		return qtdeMembros;
	}

	public void setQtdeMembros(long qtdeMembros) {
		this.qtdeMembros = qtdeMembros;
	}

	public TipoRede getTipoRede() {
		return tipoRede;
	}

	public void setTipoRede(TipoRede tipoRede) {
		this.tipoRede = tipoRede;
	}

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}

	public String getLider() {
		return lider;
	}

	public void setLider(String lider) {
		this.lider = lider;
	}

	public boolean isApagada() {
		return apagada;
	}

	public void setApagada(boolean apagada) {
		this.apagada = apagada;
	}
	
}
