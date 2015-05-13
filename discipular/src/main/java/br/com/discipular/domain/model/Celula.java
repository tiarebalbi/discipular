package br.com.discipular.domain.model;

import br.com.discipular.domain.enumetator.DiaSemana;
import br.com.discipular.domain.enumetator.Horario;
import br.com.discipular.domain.enumetator.TipoRede;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


/**
 * Modelo que representa as células da paróquia
 * 
 * @author Lucas Campos
 * @date 08/09/2014
 */
@Document
public class Celula extends AbstractDocument {

	private String nome;

	private String endereco;

	@Enumerated(value = EnumType.STRING)
	private DiaSemana dia;

	@Enumerated(value = EnumType.STRING)
	private Horario horario;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;

	@Enumerated(value = EnumType.STRING)
	private TipoRede tipoRede;

	private Usuario lider;

	private Usuario supervisor;

	private int area;

	@DBRef
	private List<Relatorio> relatorios;

	@DBRef
	private List<Membro> membros;

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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public TipoRede getTipoRede() {
		return tipoRede;
	}

	public void setTipoRede(TipoRede tipoRede) {
		this.tipoRede = tipoRede;
	}

	public Usuario getLider() {
		return lider;
	}

	public void setLider(Usuario lider) {
		this.lider = lider;
	}

	public Usuario getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Usuario supervisor) {
		this.supervisor = supervisor;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public List<Relatorio> getRelatorios() {
		return relatorios;
	}

	public void setRelatorios(List<Relatorio> relatorios) {
		this.relatorios = relatorios;
	}

	public List<Membro> getMembros() {
		return membros;
	}

	public void setMembros(List<Membro> membros) {
		this.membros = membros;
	}
}