package br.com.discipular.controller.admin;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.discipular.context.security.DiscipularUserDetail;
import br.com.discipular.model.Usuario;

public abstract class AbstractAdminController {
	
	protected Usuario getCurrentUser() {
		DiscipularUserDetail userDetail = (DiscipularUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetail.getUsuario();
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
