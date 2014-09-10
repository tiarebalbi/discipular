package br.com.discipular.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Modelo que representa os usuários do sistema
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	08/09/2014 
 */
@Entity
public class Usuario extends AbstractModel {

	@NotNull
	@Column(length = 22)
	private String login;
	
	@NotNull
	@Column(length = 50)
	private String senha;

}
