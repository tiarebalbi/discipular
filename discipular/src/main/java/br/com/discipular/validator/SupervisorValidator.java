package br.com.discipular.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.discipular.model.Supervisor;
import br.com.discipular.predicate.SupervisorPredicate;
import br.com.discipular.service.SupervisorService;

/**
 * Validações realizadas no modelo {@link Supervisor}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 *        Oct 7, 2014
 */
@Component
public class SupervisorValidator implements Validator {

	@Autowired
	private SupervisorService service;
	
	@Override
	public boolean supports(Class<?> classe) {
		return Supervisor.class.equals(classe);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "area", "campo.vazio", "Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.nome", "campo.vazio", "Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.senha", "campo.vazio", "Este campo é obrigatório");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.login", "campo.vazio", "Este campo é obrigatório");
		
		Supervisor supervisor = (Supervisor) target;
		
		long total = service.count(SupervisorPredicate.buscarPorArea(supervisor.getArea()));
		
		if(total > 0) {
			errors.rejectValue("area", "campo.duplicado", "Já existe um supervisor responsável por esta área, tente outra.");
		}
		
	}
	
}
