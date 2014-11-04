package br.com.discipular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.discipular.context.security.DiscipularUserDetail;
import br.com.discipular.model.Usuario;
import br.com.discipular.predicate.CelulaPredicate;
import br.com.discipular.service.CelulaService;

public abstract class AbstractController {
	
	@Autowired
	private CelulaService celulaService;
	
	protected Usuario getCurrentUser() {
		DiscipularUserDetail userDetail = (DiscipularUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetail.getUsuario();
	}
	
	protected Boolean haveCelula() {
		long total = celulaService.count(CelulaPredicate.buscarPor(getCurrentUser()));
		return total > 0;
	}

}
