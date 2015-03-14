package br.com.discipular.enumerator;

/**
 * Possíveis horários de célula
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	08/09/2014 
 */
public enum Horario {
	OITO_E_MEIA("08:30"),
	DUAS_E_MEIA("14:30"),
	TRES_HORAS("15:00"),
	SETE_E_MEIA("19:30"), 
	OITO_HORAS("20:00");
	
	private Horario(String horario) {
		this.horario = horario;
	}
	
	private String horario;

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
}
