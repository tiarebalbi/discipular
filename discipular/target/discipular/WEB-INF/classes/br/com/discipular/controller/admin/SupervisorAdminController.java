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
import org.springframework.web.bind.annotation.ResponseBody;
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

@Controller
@Administrador
@RequestMapping(value = "/admin/supervisor")
public class SupervisorAdminController extends AbstractAdminController {
	
	private final static String VIEW_INDEX = "admin-supervisor/index";
	private final static String VIEW_FORM = "admin-supervisor/form";
	private final static String VIEW_REDIRECT_INDEX = "redirect:/admin/supervisor";
	private final static int QUANTIDADE_ELEMENTOS_POR_PAGINA = 15;
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
	public @ResponseBody ModelAndView index() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		marker = 0;
		
		Page<Usuario> registros = service.getRepositorio().findAll(UsuarioPredicate.buscarTipo(TipoUsuario.SUPERVISOR), UsuarioPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		qtdePaginas = registros.getTotalPages();
		
		view.addObject("registros", registros.getContent());
		view.addObject("pagina", qtdePaginas);
		
		return view;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo() {
		Usuario usuario = new Usuario();
		usuario.setLogin(" ");
		ModelAndView view = new ModelAndView(VIEW_FORM, "usuario", usuario);
		return view;
	}
	
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable ("id") Long id) {
		Usuario usuario = service.getRepositorio().findOne(id);
		ModelAndView view = new ModelAndView(VIEW_FORM, "usuario", usuario);
		return view;
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute ("usuario") @Validated Usuario usuario, BindingResult errors, RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView(VIEW_REDIRECT_INDEX);
		try {
			if(errors.hasErrors()) {
				if(usuario.getId() == null) {
					usuario.setSenha("");
				}
				view = new ModelAndView(VIEW_FORM, "usuario", usuario);
				loadViewDangerView(view, "Favor verificar se todos os campos foram preenchidos corretamente, caso o problema insista entre em contato com o administrador do sistema.");
				return view;
			} 
			usuario.setTipo(TipoUsuario.SUPERVISOR);
			this.service.salvar(usuario);
			loadRedirectSuccessView(redirect, "Registro salvo com sucesso.");
		} catch(Exception e) {
			if(usuario.getId() == null) {
				usuario.setSenha("");
			}
			view = new ModelAndView(VIEW_FORM, "usuario", usuario);
			loadViewDangerView(view, e.getMessage());
		}
		return view;
	}
	
	@RequestMapping(value = "/excluir/{id}", method = RequestMethod.GET)
	public ModelAndView excluir(@PathVariable ("id") Long id, RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView(VIEW_REDIRECT_INDEX);
		try {
			Usuario usuario = this.service.getRepositorio().findOne(id);
			List<Celula> celulas = (List<Celula>) this.celulaService.getRepositorio().findAll(CelulaPredicate.buscarPorSupervisor(usuario));
			celulas.forEach(c -> c.setSupervisor(null));
			this.celulaService.getRepositorio().save(celulas);
			this.service.getRepositorio().delete(id);
			loadRedirectSuccessView(redirect, "Registro excluído com sucesso.");
		} catch(Exception e) {
			loadRedirectDangerView(redirect, e.getMessage());
		}
		
		return view;
	}
	
	@RequestMapping(value = "/previous", method = RequestMethod.POST)
	public ModelAndView apiPrevious() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Page<Usuario> registros = service.getRepositorio().findAll(UsuarioPredicate.buscarTipo(TipoUsuario.SUPERVISOR), UsuarioPredicate.buscarPaginacao(--marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "/next", method = RequestMethod.POST)
	public ModelAndView apiNext() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Page<Usuario> registros = service.getRepositorio().findAll(UsuarioPredicate.buscarTipo(TipoUsuario.SUPERVISOR), UsuarioPredicate.buscarPaginacao(++marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "/find/{condicao}", method = RequestMethod.POST)
	public ModelAndView apiFind(@PathVariable ("condicao") String nome) {
		ModelAndView view = new ModelAndView();
		
		Page<Usuario> registros = service.getRepositorio().findAll(UsuarioPredicate.buscarPorNomeComFiltro(nome, TipoUsuario.SUPERVISOR), UsuarioPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		
		view.addObject("registros", registros.getContent());
		view.addObject("pagina", qtdePaginas);
		
		return view;
	}
	
	@RequestMapping(value = "/find/area/{condicao}", method = RequestMethod.POST)
	public ModelAndView apiFind(@PathVariable ("condicao") int area) {
		ModelAndView view = new ModelAndView();
		
		Page<Usuario> registros = service.getRepositorio().findAll(UsuarioPredicate.buscarTipoEArea(TipoUsuario.SUPERVISOR, area), UsuarioPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		
		view.addObject("registros", registros.getContent());
		view.addObject("pagina", qtdePaginas);
		
		return view;
	}
	
	@RequestMapping(value = "/alterar-senha/{id}", method = RequestMethod.GET)
	public ModelAndView resetarSenha(@PathVariable ("id") Long id, RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView(VIEW_REDIRECT_INDEX);
		try {
			Usuario usuario = service.getRepositorio().findOne(id);
			usuario.setSenha(new DiscipularPasswordEncoder().encode(usuario.getLogin() + "123"));
			service.salvar(usuario);
			loadRedirectSuccessView(redirect, "Senha do supervisor " + usuario.getNome() + " foi alterada com sucesso.");
		} catch (Exception e) {
			loadRedirectDangerView(redirect, e.getMessage());
		}
		
		return view;
	}
	
}
