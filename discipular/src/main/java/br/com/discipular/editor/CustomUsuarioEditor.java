package br.com.discipular.editor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.propertyeditors.PropertiesEditor;

import br.com.discipular.model.Usuario;
import br.com.discipular.predicate.UsuarioPredicate;
import br.com.discipular.service.UsuarioService;

public class CustomUsuarioEditor extends PropertiesEditor {
	
	private UsuarioService service;

	public CustomUsuarioEditor(UsuarioService service) {
		this.service = service;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		super.setAsText(text);
		Usuario registro;
		if(StringUtils.isNumeric(text)) {
			Long id = new Long(text);
			registro = this.service.buscarRegistro(id);
			
		}else {
			registro = this.service.buscarRegistro(UsuarioPredicate.buscarPorNome(text));
		}
		setValue(registro);
	}
	
}

