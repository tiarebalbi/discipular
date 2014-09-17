package br.com.discipular.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import br.com.discipular.enumerator.DiaSemana;
import br.com.discipular.enumerator.Horario;

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
	private LocalDate nascimento;
	
	@NotNull
	@Column(length = 100)
	private String endereco;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private DiaSemana dia;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Horario horario;
	
	@OneToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Membro> membros;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
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

	public List<Membro> getMembros() {
		return membros;
	}

	public void setMembros(List<Membro> membros) {
		this.membros = membros;
	}

}
