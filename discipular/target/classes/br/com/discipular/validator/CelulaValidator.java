package br.com.discipular.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.discipular.model.Celula;

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
		ValidationUtils.rejectIfEmpty(e, "nome", "campo.nulo","Este campo é obrigatório");
	}

}
