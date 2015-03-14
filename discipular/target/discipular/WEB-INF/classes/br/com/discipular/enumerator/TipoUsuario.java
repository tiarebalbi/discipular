package br.com.discipular.enumerator;

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
