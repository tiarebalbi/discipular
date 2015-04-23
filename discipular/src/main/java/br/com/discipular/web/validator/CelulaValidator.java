package br.com.discipular.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.discipular.domain.model.Celula;

/**
 * Validações realizadas no modelo {@link Celula}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	10/09/2014 
 */
@Component
public class CelulaValidator implements Validator {

	@Override
	public boolean supports(Class<?> classe) {
		return Celula.class.equals(classe);
	}

	@Override
	public void validate(Object object, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "nome", "campo.obrigatorio","Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "endereco", "campo.obrigatorio","Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "dia", "campo.obrigatorio","Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "horario", "campo.obrigatorio","Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "dataNascimento", "campo.obrigatorio","Este campo é obrigatório");
	}

}
