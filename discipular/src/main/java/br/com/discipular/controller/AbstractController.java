package br.com.discipular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		long total = celulaService.count(CelulaPredicate.buscarPorLider(getCurrentUser()));
		return total > 0;
	}
	
	protected RedirectAttributes loadRedirectSuccessView(RedirectAttributes redirect, String mensagem) {
		redirect.addFlashAttribute("mensagem", mensagem);
		redirect.addFlashAttribute("status", "success");
		redirect.addFlashAttribute("icon", "check");
		return redirect;
	}
	
	protected ModelAndView loadViewSuccessView(ModelAndView view, String mensagem) {
		view.addObject("mensagem", mensagem);
		view.addObject("status", "success");
		view.addObject("icon", "check");
		return view;
	}
	
	protected ModelAndView loadViewDangerView(ModelAndView view, String mensagem) {
		view.addObject("mensagem", mensagem);
		view.addObject("status", "danger");
		view.addObject("icon", "times");
		return view;
	}
	
	protected RedirectAttributes loadRedirectDangerView(RedirectAttributes redirect, String mensagem) {
		redirect.addFlashAttribute("mensagem", mensagem);
		redirect.addFlashAttribute("status", "danger");
		redirect.addFlashAttribute("icon", "times");
		return redirect;
	}
	

}
