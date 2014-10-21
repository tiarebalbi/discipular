package br.com.discipular.editor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.propertyeditors.PropertiesEditor;

import br.com.discipular.model.Supervisor;
import br.com.discipular.predicate.SupervisorPredicate;
import br.com.discipular.service.SupervisorService;

/**
 * Custom Editor para converter {@link Supervisor} em formulários
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 *        Oct 17, 2014
 */
public class CustomSupervisorEditor extends PropertiesEditor {
	
	private SupervisorService service;

	public CustomSupervisorEditor(SupervisorService service) {
		this.service = service;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Supervisor registro;
		if(StringUtils.isNumeric(text)) {
			Long id = new Long(text);
			registro = this.service.buscarRegistro(id);
			
		}else {
			registro = this.service.buscarRegistro(SupervisorPredicate.buscarPorNome(text));
		}
		setValue(registro);
	}

}
