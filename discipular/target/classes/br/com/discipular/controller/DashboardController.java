package br.com.discipular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.discipular.model.Usuario;
import br.com.discipular.service.UsuarioService;

@Controller
public class DashboardController extends AbstractController {
	
	private final static String VIEW_INDEX = "dashboard/index";
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		return view;
	}
	
	@RequestMapping(value = "/trocar-senha/{senha}/{confirm}")
	public ModelAndView trocarSenha(@PathVariable ("senha") String senha, @PathVariable ("confirm") String confirm) {
		ModelAndView view = new ModelAndView();
		Usuario usuario = getCurrentUser();
		
		try {
			if(senha.equals(confirm)) {
				usuario.setSenha(senha);
				usuarioService.salvar(usuario);
			}
		} catch (Exception e) {
			
		}
		
		return view;
	}

}
