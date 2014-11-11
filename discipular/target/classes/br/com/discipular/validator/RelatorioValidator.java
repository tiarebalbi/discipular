package br.com.discipular.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.discipular.model.Relatorio;

/**
 * Validações realizadas no modelo {@link Relatorio}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	12/09/2014 
 */
@Component
public class RelatorioValidator implements Validator {

	@Override
	public boolean supports(Class<?> classe) {
		return Relatorio.class.equals(classe);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ask1", "campo.obrigatorio", "Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ask2", "campo.obrigatorio", "Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ask3", "campo.obrigatorio", "Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ask4", "campo.obrigatorio", "Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ask5", "campo.obrigatorio", "Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tema", "campo.obrigatorio", "Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "data", "campo.obrigatorio", "Este campo é obrigatório");

		Relatorio relatorio = (Relatorio) target;
		
		if(relatorio.getAsk1() > 5 || relatorio.getAsk1() < 0) {
			errors.rejectValue("ask1", "campo.valor.invalido", "Este campo permite valores apenas de 0 a 5.");
		}
		
		if(relatorio.getAsk2() > 5 || relatorio.getAsk2() < 0) {
			errors.rejectValue("ask2", "campo.valor.invalido", "Este campo permite valores apenas de 0 a 5.");
		}
		
		if(relatorio.getAsk3() > 5 || relatorio.getAsk3() < 0) {
			errors.rejectValue("ask3", "campo.valor.invalido", "Este campo permite valores apenas de 0 a 5.");
		}
		
		if(relatorio.getAsk4() > 5 || relatorio.getAsk4() < 0) {
			errors.rejectValue("ask4", "campo.valor.invalido", "Este campo permite valores apenas de 0 a 5.");
		}
		
		if(relatorio.getAsk5() > 5 || relatorio.getAsk5() < 0) {
			errors.rejectValue("ask5", "campo.valor.invalido", "Este campo permite valores apenas de 0 a 5.");
		}	
	
	}

}
