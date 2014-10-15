package br.com.discipular.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Modelo que representa os supervisores
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	06/10/2014 
 */
@Entity
public class Supervisor extends AbstractModel {

	@Column(length = 50)
	private String nome;
	
	private int area;
	
	private Usuario usuario;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
