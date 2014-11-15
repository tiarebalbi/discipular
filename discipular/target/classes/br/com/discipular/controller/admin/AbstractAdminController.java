package br.com.discipular.controller.admin;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.discipular.context.security.DiscipularUserDetail;
import br.com.discipular.model.Usuario;

public abstract class AbstractAdminController {
	
	protected Usuario getCurrentUser() {
		DiscipularUserDetail userDetail = (DiscipularUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetail.getUsuario();
	}

}
