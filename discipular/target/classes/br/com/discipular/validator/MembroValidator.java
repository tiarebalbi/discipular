package br.com.discipular.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.discipular.model.Membro;

/**
 * Validações realizadas no modelo {@link Membro}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	08/09/2014 
 */
@Component
public class MembroValidator implements Validator {

	private Pattern pattern;
	private Matcher matcher;
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	@Override
	public boolean supports(Class<?> classe) {
		return Membro.class.equals(classe);
	}

	@Override
	public void validate(Object object, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "nome", "campo.obrigatorio","Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "tipo", "campo.obrigatorio","Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "dataNascimento", "campo.obrigatorio","Este campo é obrigatório");
		
		Membro membro = (Membro) object;
		
		if(membro.getEmail() != null && validarEmail(membro)) {
			e.rejectValue("email", "email.invalido", "Email inválido.");
		}
		
	}
	
	private boolean validarEmail(Membro membro) {
		
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(membro.getEmail());
		
		return matcher.matches();
	}

}
