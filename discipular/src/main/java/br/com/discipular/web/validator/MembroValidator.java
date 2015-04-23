package br.com.discipular.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.discipular.domain.model.Membro;

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

	@Override
	public boolean supports(Class<?> classe) {
		return Membro.class.equals(classe);
	}

	@Override
	public void validate(Object object, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "nome", "campo.obrigatorio","Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "tipo", "campo.obrigatorio","Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "dataNascimento", "campo.obrigatorio","Este campo é obrigatório");
	}
	
}