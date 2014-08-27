package br.com.discipular.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.discipular.model.Usuario;

@Component
public class UsuarioValidator implements Validator {

	@Override
	public boolean supports(Class<?> classe) {
		return Usuario.class.equals(classe);
	}

	@Override
	public void validate(Object object, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "nome", "campo.nulo","Este campo é obrigatório");
		ValidationUtils.rejectIfEmpty(e, "email", "campo.nulo", "Este campo é obrigatório");
		ValidationUtils.rejectIfEmpty(e, "celular", "campo.nulo", "Este campo é obrigatório");
		ValidationUtils.rejectIfEmpty(e, "endereco", "campo.nulo", "Este campo é obrigatório");
	}

}
