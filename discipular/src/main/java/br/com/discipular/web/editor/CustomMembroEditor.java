package br.com.discipular.web.editor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.PropertiesEditor;

import br.com.discipular.domain.model.Membro;
import br.com.discipular.domain.predicate.MembroPredicate;
import br.com.discipular.service.MembroService;

/**
 * Custom Editor para converter {@link Membro} em formulários
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	08/09/2014 
 */
public class CustomMembroEditor extends PropertiesEditor {
	
	private MembroService service;

	public CustomMembroEditor(MembroService service) {
		this.service = service;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		super.setAsText(text);
		Membro registro;
		if(StringUtils.isNumeric(text)) {
			Long id = new Long(text);
			registro = this.service.getRepositorio().findOne(id);
			
		}else {
			registro = this.service.getRepositorio().findOne(MembroPredicate.buscarPorNome(text));
		}
		setValue(registro);
	}
	
}

