package br.com.discipular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.discipular.service.UsuarioService;
import br.com.discipular.validator.UsuarioValidator;

@Controller
public class LoginController {

	private final static String VIEW_LOGIN = "login/index";
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioValidator validator;
	
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView view = new ModelAndView(VIEW_LOGIN);
		return view;
	}
	
}
