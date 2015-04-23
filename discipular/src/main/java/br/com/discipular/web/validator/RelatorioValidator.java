package br.com.discipular.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.discipular.domain.model.Relatorio;

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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "geral", "campo.obrigatorio", "Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "e5", "campo.obrigatorio", "Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "participacao", "campo.obrigatorio", "Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tempo", "campo.obrigatorio", "Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "conteudo", "campo.obrigatorio", "Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tema", "campo.obrigatorio", "Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "data", "campo.obrigatorio", "Este campo é obrigatório");

		Relatorio relatorio = (Relatorio) target;
		
		if(relatorio.getGeral() > 5 || relatorio.getGeral() < 0) {
			errors.rejectValue("geral", "campo.valor.invalido", "Este campo permite valores apenas de 0 a 5.");
		}
		
		if(relatorio.getE5() > 5 || relatorio.getE5() < 0) {
			errors.rejectValue("e5", "campo.valor.invalido", "Este campo permite valores apenas de 0 a 5.");
		}
		
		if(relatorio.getParticipacao() > 5 || relatorio.getParticipacao() < 0) {
			errors.rejectValue("participacao", "campo.valor.invalido", "Este campo permite valores apenas de 0 a 5.");
		}
		
		if(relatorio.getTempo() > 5 || relatorio.getTempo() < 0) {
			errors.rejectValue("tempo", "campo.valor.invalido", "Este campo permite valores apenas de 0 a 5.");
		}
		
		if(relatorio.getConteudo() > 5 || relatorio.getConteudo() < 0) {
			errors.rejectValue("conteudo", "campo.valor.invalido", "Este campo permite valores apenas de 0 a 5.");
		}
		
	}

}
