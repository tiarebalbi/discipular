package br.com.discipular.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.discipular.model.Usuario;

/**
 * Validações realizadas no modelo {@link Usuario}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	10/09/2014 
 */
@Component
public class UsuarioValidator implements Validator {

	@Override
	public boolean supports(Class<?> classe) {
		return Usuario.class.equals(classe);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "campo.vazio", "Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "campo.vazio", "Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "senha", "campo.vazio", "Este campo é obrigatório");
		
		Usuario usuario = (Usuario) target;
		
		if(usuario.getLogin().length() < 5) {
			errors.rejectValue("login", "campo.curto", "O login deve ter no mínimo 5 dígitos.");
		}
		
		if(usuario.getSenha().length() < 6) {
			errors.rejectValue("senha", "campo.pequeno", "A senha deve ter no mínimo 6 dígitos");			
		}
	}

}
