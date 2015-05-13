package br.com.discipular.web.controller;

import br.com.discipular.domain.enumetator.TipoUsuario;
import br.com.discipular.domain.model.Usuario;
import br.com.discipular.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LoginController {

	private final static String VIEW_LOGIN = "login/index";
	private static final String REDIRECT_VIEW_LOGIN = "redirect:/";

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView index() {
		ModelAndView view = new ModelAndView(VIEW_LOGIN);
		return view;
	}
	

	@RequestMapping(value = "/login/install", method = RequestMethod.GET)
	public ModelAndView adm() {
		ModelAndView view = new ModelAndView(REDIRECT_VIEW_LOGIN);
		try {
			Usuario usuario = new Usuario();
			
			usuario.setNome("Lucas Campos");
			usuario.setLogin("admin");
			usuario.setEmail("teste@teset.com");
			usuario.setSenha("123");
			usuario.setArea(0);
			usuario.setTipo(TipoUsuario.ADMINISTRADOR);
			List<Usuario> usuarios = usuarioService.getRepositorio().findAll();System.out.print(usuarios);
//			usuarioService.salvar(usuario);System.out.print(usuarios);
			System.out.print(usuarios);} catch (Exception e) {
			System.out.println("Deu erro.");
		}
		
		return view;
	}

	
}
