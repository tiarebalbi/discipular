package br.com.discipular.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	private Pattern pattern;
	private Matcher matcher;
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	@Override
	public boolean supports(Class<?> classe) {
		return Usuario.class.equals(classe);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "campo.obrigatorio", "haha pegadinha seu bobóca");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "campo.obrigatorio", "Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "senha", "campo.obrigatorio", "Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefone", "campo.obrigatorio", "Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "campo.obrigatorio", "Este campo é obrigatório");
		
		Usuario usuario = (Usuario) target;
		
		if(usuario.getLogin().length() < 5) {
			errors.rejectValue("login", "campo.curto", "O login deve ter no mínimo 5 dígitos.");
		}
		
		if(usuario.getSenha().length() < 6) {
			errors.rejectValue("senha", "campo.pequeno", "A senha deve ter no mínimo 6 dígitos");			
		}
		
		if(usuario.getEmail() != null && validarEmail(usuario)) {
			errors.rejectValue("email", "email.invalido", "Email inválido.");
		}
		
	}
	
	private boolean validarEmail(Usuario usuario) {
		
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(usuario.getEmail());
		
		return matcher.matches();
	}

}
