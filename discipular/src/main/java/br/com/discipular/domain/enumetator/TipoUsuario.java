package br.com.discipular.domain.enumetator;

/**
 * Tipos de {@link br.com.discipular.domain.model.Usuario} existentes no sistema
 *
 * @author Lucas Campos
 * @date 08/09/2014
 */
public enum TipoUsuario {
	
	LIDER("ROLE_LIDER", "Líder"), 
	SUPERVISOR("ROLE_SUPERVISOR", "Supervisor"),
	ADMINISTRADOR("ROLE_ADMINISTRADOR", "Administrador");

	private String regra;
	
	private String tipo;

	public String getRegra() {
		return regra;
	}

	public void setRegra(String regra) {
		this.regra = regra;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	private TipoUsuario(String regra, String tipo) {
		this.regra = regra;
		this.tipo = tipo;
	}
	
	
}
