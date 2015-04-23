package br.com.discipular.web.controller.admin;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.discipular.config.security.DiscipularUserDetails;

public abstract class AbstractAdminController {
	
	protected DiscipularUserDetails getCurrentUser() {
		DiscipularUserDetails userDetail = (DiscipularUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetail;
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
