package br.com.discipular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.discipular.enumerator.TipoUsuario;
import br.com.discipular.model.Usuario;
import br.com.discipular.service.UsuarioService;

@Controller
public class LoginController {

	private final static String VIEW_LOGIN = "login/index";
	private final static String REDIRECT_VIEW_LOGIN = "redirect:/login";
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView view = new ModelAndView(VIEW_LOGIN);
		return view;
	}
	
	@RequestMapping(value = "/login/install", method = RequestMethod.GET)
	public ModelAndView adm() {
		ModelAndView view = new ModelAndView(REDIRECT_VIEW_LOGIN);
		
		Usuario usuario = new Usuario();
		
		usuario.setNome("Lucas Campos");
		usuario.setLogin("admin");
		usuario.setSenha("123");
		usuario.setArea(0);
		usuario.setTipo(TipoUsuario.ADMINISTRADOR);

		Usuario ademir = new Usuario();
		usuario.setNome("Ademir e Sandra");
		usuario.setLogin("adolivei");
		usuario.setSenha("123");
		usuario.setArea(0);
		usuario.setTipo(TipoUsuario.ADMINISTRADOR);
		
		try {
			usuarioService.salvar(usuario);
			usuarioService.salvar(ademir);
		} catch (Exception e) {
			System.out.println("Deu erro.");
		}
		
		return view;
	}
	
}
