package br.com.discipular.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.discipular.model.Usuario;

@Controller
@RequestMapping(value = "usuarios-logados")
public class UsuariosLogadosController {
	
	private final static String VIEW_INDEX = "admin-usuario-logado/index";
	
//	@Autowired
//	private SessionRegistry session;
//	
//	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
//	public ModelAndView index() {
//		ModelAndView view = new ModelAndView(VIEW_INDEX);
//		
//		List<Usuario> usuarios = new ArrayList<>();
//		
//		List<Object> sessoes = session.getAllPrincipals();
//		for (Object object : sessoes) {
//			usuarios.add((Usuario) object);
//		}
//		
//		view.addObject("usuarios", usuarios);
//		
//		return view;
//	}

}
