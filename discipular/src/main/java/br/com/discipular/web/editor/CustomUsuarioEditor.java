package br.com.discipular.web.editor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.PropertiesEditor;

import br.com.discipular.domain.model.Usuario;
import br.com.discipular.domain.predicate.UsuarioPredicate;
import br.com.discipular.service.UsuarioService;

/**
 * Custom Editor para converter {@link Usuario} em formulários
 * 
 * @author Lucas Campos
 * @since 1.0.0
 * @version 1.0.0
 *
 * 	12/09/2014 
 */
public class CustomUsuarioEditor extends PropertiesEditor {
	
	private UsuarioService service;

	public CustomUsuarioEditor(UsuarioService service) {
		this.service = service;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Usuario registro;
		if(StringUtils.isNumeric(text)) {
			Long id = new Long(text);
			registro = this.service.getRepositorio().findOne(id);
			
		}else {
			registro = this.service.getRepositorio().findOne(UsuarioPredicate.buscarPorLogin(text));
		}
		setValue(registro);
	}

}
