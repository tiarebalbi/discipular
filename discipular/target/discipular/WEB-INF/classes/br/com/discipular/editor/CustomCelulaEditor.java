package br.com.discipular.editor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.propertyeditors.PropertiesEditor;

import br.com.discipular.model.Celula;
import br.com.discipular.predicate.CelulaPredicate;
import br.com.discipular.service.CelulaService;

/**
 * Custom Editor para converter {@link Celula} em formulários
 * 
 * @author Lucas Campos
 * @since 1.0.0
 * @version 1.0.0
 *
 * 	12/09/2014 
 */
public class CustomCelulaEditor extends PropertiesEditor {
	
	private CelulaService service;

	public CustomCelulaEditor(CelulaService service) {
		this.service = service;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Celula registro;
		if(StringUtils.isNumeric(text)) {
			Long id = new Long(text);
			registro = this.service.getRepositorio().findOne(id);
			
		}else {
			registro = this.service.getRepositorio().findOne(CelulaPredicate.buscarPorNome(text));
		}
		setValue(registro);
	}

}
