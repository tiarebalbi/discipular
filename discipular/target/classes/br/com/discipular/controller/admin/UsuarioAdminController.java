package br.com.discipular.controller.admin;

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
import br.com.discipular.editor.CustomCelulaEditor;
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
	private final static int QUANTIDADE_ELEMENTOS_POR_PAGINA = 8;
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
		binder.registerCustomEditor(Celula.class, new CustomCelulaEditor(celulaService));
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		marker = 0;
		
		Page<Usuario> registros = service.buscarTodos(UsuarioPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		qtdePaginas = registros.getTotalPages();
		view.addObject("registros", registros.getContent());
		view.addObject("pagina", qtdePaginas);
		
		return view;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo() {
		ModelAndView view = new ModelAndView(VIEW_FORM, "usuario", new Usuario());
		return carregarDadosForm(view);
	}
	
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable ("id") Long id) {
		Usuario usuario = service.buscarRegistro(id);
		ModelAndView view = new ModelAndView(VIEW_FORM, "usuario", usuario);
		return carregarDadosForm(view);
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute ("usuario") @Validated Usuario usuario, BindingResult errors, RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView(VIEW_REDIRECT_INDEX);
		if(errors.hasErrors()) {
			view = new ModelAndView(VIEW_FORM, "usuario", usuario);
			view = carregarDadosForm(view);
			view.addObject("mensagem", "Favor verificar se todos os campos foram preenchidos corretamente, caso o problema insista entre em contato com o administrador do sistema.");
			view.addObject("status", "danger");
			view.addObject("icon", "times");
		} else {
			try {
				usuario.getCelula().setIdUsuario(this.service.salvar(usuario).getId());
				celulaService.salvar(usuario.getCelula());
				redirect.addFlashAttribute("mensagem", "Registro salvo com sucesso.");
				redirect.addFlashAttribute("status", "success");
				redirect.addFlashAttribute("icon", "check");
			} catch(Exception e) {
				view = new ModelAndView(VIEW_FORM, "usuario", usuario);
				view = carregarDadosForm(view);
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
			this.service.excluir(id);
			Celula celula = celulaService.buscarRegistro(CelulaPredicate.buscarPorIdUsuario(id));
			celula.setIdUsuario(null);
			celulaService.salvar(celula);
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
		
		Page<Usuario> registros = service.buscarTodos(UsuarioPredicate.buscarPaginacao(--marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "/next", method = RequestMethod.POST)
	public ModelAndView apiNext() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Page<Usuario> registros = service.buscarTodos(UsuarioPredicate.buscarPaginacao(++marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
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
	
	private ModelAndView carregarDadosForm(ModelAndView view) {
		view.addObject("tipos", TipoUsuario.values());
		view.addObject("celulas", celulaService.buscarTodos(CelulaPredicate.buscarPorUsuarioNulo()));
		return view;
	}
	
}