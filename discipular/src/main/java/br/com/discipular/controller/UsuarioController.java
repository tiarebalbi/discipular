package br.com.discipular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.discipular.model.Usuario;
import br.com.discipular.predicate.UsuarioPredicate;
import br.com.discipular.service.UsuarioService;
import br.com.discipular.validator.UsuarioValidator;

@Controller
@RequestMapping(value = "/admin/usuario")
public class UsuarioController {

	private final static String VIEW_INDEX = "admin-usuario/index";
	private final static String VIEW_FORM = "admin-usuario/form";
	private final static String VIEW_REDIRECT_INDEX = "redirect:/admin/usuario";
	private final static int QTDE_PAGINAS = 7;
	private int marker = 0;
	
	@Autowired
	private UsuarioService service;

	@Autowired
	private UsuarioValidator validator;
	
	@InitBinder("usuario")
	public void a(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		marker = 0;
		
		Page<Usuario> usuarios = service.buscarTodos(UsuarioPredicate.buscarPaginacao(0, QTDE_PAGINAS));
		view.addObject("usuarios", usuarios.getContent());
		view.addObject("pagina", QTDE_PAGINAS);
		
		return view;
	}
	
	@RequestMapping(value = "novo", method = RequestMethod.GET)
	public ModelAndView novo() {
		ModelAndView view = new ModelAndView(VIEW_FORM, "usuario", new Usuario());
		return view;
	}
	
	@RequestMapping(value = "editar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable ("id") Long id) {
		Usuario usuario = service.buscarRegistro(id);
		ModelAndView view = new ModelAndView(VIEW_FORM, "usuario", usuario);
		return view;
	}
	
	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute ("usuario") @Validated Usuario usuario, BindingResult errors, RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView(VIEW_REDIRECT_INDEX);
		if(errors.hasErrors()) {
			view = new ModelAndView(VIEW_FORM, "usuario", usuario);
			view.addObject("mensagem", "Reveja os campos");
			view.addObject("status", "error");
		} else {
			try {
				this.service.salvar(usuario);
				redirect.addFlashAttribute("mensagem", "Registro salvo com sucesso.");
				redirect.addFlashAttribute("status", "success");
			} catch(Exception e) {
				view = new ModelAndView(VIEW_FORM, "usuario", usuario);
				view.addObject("mensagem", e.getMessage());
				view.addObject("status", "error");
			}
		}
		return view;
	}
	
	@RequestMapping(value = "excluir/{id}", method = RequestMethod.GET)
	public ModelAndView excluir(@PathVariable ("id") Long id, RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView(VIEW_REDIRECT_INDEX);
		try {
			this.service.excluir(id);
			redirect.addFlashAttribute("mensagem", "Registro excluído com sucesso.");
			redirect.addFlashAttribute("status", "success");
		} catch(Exception e) {
			redirect.addFlashAttribute("mensagem", e.getMessage());
			redirect.addFlashAttribute("status", "error");
		}
		
		return view;
	}
	
	@RequestMapping(value = "previous", method = RequestMethod.POST)
	public ModelAndView apiPrevious() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Page<Usuario> usuarios = service.buscarTodos(UsuarioPredicate.buscarPaginacao(--marker, QTDE_PAGINAS));
		view.addObject("usuarios", usuarios.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "next", method = RequestMethod.POST)
	public ModelAndView apiNext() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Page<Usuario> usuarios = service.buscarTodos(UsuarioPredicate.buscarPaginacao(++marker, QTDE_PAGINAS));
		view.addObject("usuarios", usuarios.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "find/{condicao}", method = RequestMethod.POST)
	public ModelAndView apiFind(@PathVariable ("condicao") String nome) {
		ModelAndView view = new ModelAndView();
		
		Page<Usuario> users = service.buscarTodos(UsuarioPredicate.buscarPorNome(nome), UsuarioPredicate.buscarPaginacao(0, QTDE_PAGINAS));
		
		view.addObject("usuarios", users.getContent());
		view.addObject("pagina", QTDE_PAGINAS);
		
		return view;
	}
	
}
