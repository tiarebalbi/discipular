package br.com.discipular.controller.admin;

import java.util.List;

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

import br.com.discipular.annotations.Administrador;
import br.com.discipular.context.security.DiscipularPasswordEncoder;
import br.com.discipular.enumerator.TipoUsuario;
import br.com.discipular.model.Celula;
import br.com.discipular.model.Usuario;
import br.com.discipular.predicate.CelulaPredicate;
import br.com.discipular.predicate.UsuarioPredicate;
import br.com.discipular.service.CelulaService;
import br.com.discipular.service.UsuarioService;
import br.com.discipular.validator.UsuarioValidator;

/**
 * Controller do modelo {@link Usuario}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	10/09/2014 
 */
@Controller
@Administrador
@RequestMapping(value = "/admin/usuario")
public class UsuarioAdminController {

	private final static String VIEW_INDEX = "admin-usuario/index";
	private final static String VIEW_FORM = "admin-usuario/form";
	private final static String VIEW_REDIRECT_INDEX = "redirect:/admin/usuario";
	private final static int QUANTIDADE_ELEMENTOS_POR_PAGINA = 3;
	private int qtdePaginas;
	private int marker = 0;
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private CelulaService celulaService;
	
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
		
		Page<Usuario> registros = service.buscarTodos(UsuarioPredicate.buscarTipo(TipoUsuario.LIDER), UsuarioPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		qtdePaginas = registros.getTotalPages();
		view.addObject("registros", registros.getContent());
		view.addObject("pagina", qtdePaginas);
		
		return view;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo() {
		ModelAndView view = new ModelAndView(VIEW_FORM, "usuario", new Usuario());
		view.addObject("tipos", TipoUsuario.values());
		return view;
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute ("usuario") @Validated Usuario usuario, BindingResult errors, RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView(VIEW_REDIRECT_INDEX);
		if(errors.hasErrors()) {
			usuario.setSenha("");
			view = new ModelAndView(VIEW_FORM, "usuario", usuario);
			view.addObject("tipos", TipoUsuario.values());
			view.addObject("mensagem", "Favor verificar se todos os campos foram preenchidos corretamente, caso o problema insista entre em contato com o administrador do sistema.");
			view.addObject("status", "danger");
			view.addObject("icon", "times");
		} else {
			try {
				this.service.salvar(usuario);
				redirect.addFlashAttribute("mensagem", "Registro salvo com sucesso.");
				redirect.addFlashAttribute("status", "success");
				redirect.addFlashAttribute("icon", "check");
			} catch(Exception e) {
				usuario.setSenha("");
				view = new ModelAndView(VIEW_FORM, "usuario", usuario);
				view.addObject("tipos", TipoUsuario.values());
				view.addObject("mensagem", e.getMessage());
				view.addObject("status", "danger");
				view.addObject("icon", "times");
			}
		}
		return view;
	}
	
	@RequestMapping(value = "/excluir/{id}", method = RequestMethod.GET)
	public ModelAndView excluir(@PathVariable ("id") Long id, RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView(VIEW_REDIRECT_INDEX);
		try {
			Usuario usuario = this.service.buscarRegistro(id);
			List<Celula> celulas = this.celulaService.buscarTodos(CelulaPredicate.buscarPor(usuario));
			celulas.forEach(c -> c.setUsuario(null));
			this.celulaService.salvar(celulas);
			
			this.service.excluir(id);
			redirect.addFlashAttribute("mensagem", "Registro excluído com sucesso.");
			redirect.addFlashAttribute("status", "success");
			redirect.addFlashAttribute("icon", "success");
		} catch(Exception e) {
			redirect.addFlashAttribute("mensagem", e.getMessage());
			redirect.addFlashAttribute("status", "error");
			redirect.addFlashAttribute("icon", "danger");
		}
		
		return view;
	}
	
	@RequestMapping(value = "/previous", method = RequestMethod.POST)
	public ModelAndView apiPrevious() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Page<Usuario> registros = service.buscarTodos(UsuarioPredicate.buscarTipo(TipoUsuario.LIDER), UsuarioPredicate.buscarPaginacao(--marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "/next", method = RequestMethod.POST)
	public ModelAndView apiNext() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Page<Usuario> registros = service.buscarTodos(UsuarioPredicate.buscarTipo(TipoUsuario.LIDER), UsuarioPredicate.buscarPaginacao(++marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "/find/{condicao}", method = RequestMethod.POST)
	public ModelAndView apiFind(@PathVariable ("condicao") String nome) {
		ModelAndView view = new ModelAndView();
		
		Page<Usuario> registros = service.buscarTodos(UsuarioPredicate.buscarPorNomeComFiltro(nome), UsuarioPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		
		view.addObject("registros", registros.getContent());
		view.addObject("pagina", qtdePaginas);
		
		return view;
	}
	
	@RequestMapping(value = "/alterar-senha/{id}", method = RequestMethod.GET)
	public ModelAndView resetarSenha(@PathVariable ("id") Long id, RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView(VIEW_REDIRECT_INDEX);
		try {
			Usuario usuario = service.buscarRegistro(id);
			usuario.setSenha(new DiscipularPasswordEncoder().encode(usuario.getLogin() + "123"));
			service.salvar(usuario);
			redirect.addFlashAttribute("mensagem", "Senha do líder " + usuario.getNome() + " foi alterada com sucesso.");
			redirect.addFlashAttribute("status", "success");
			redirect.addFlashAttribute("icon", "check");
		} catch (Exception e) {
			redirect.addFlashAttribute("mensagem", e.getMessage());
			redirect.addFlashAttribute("status", "danger");
			redirect.addFlashAttribute("icon", "times");
		}
		return view;
	}
	
}
