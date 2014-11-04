package br.com.discipular.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

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

	private int area;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	private Usuario usuario;

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
