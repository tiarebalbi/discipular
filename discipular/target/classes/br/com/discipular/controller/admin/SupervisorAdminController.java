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

import br.com.discipular.context.security.DiscipularPasswordEncoder;
import br.com.discipular.editor.CustomCelulaEditor;
import br.com.discipular.enumerator.TipoUsuario;
import br.com.discipular.model.Celula;
import br.com.discipular.model.Supervisor;
import br.com.discipular.model.Usuario;
import br.com.discipular.predicate.CelulaPredicate;
import br.com.discipular.predicate.SupervisorPredicate;
import br.com.discipular.service.CelulaService;
import br.com.discipular.service.SupervisorService;
import br.com.discipular.service.UsuarioService;
import br.com.discipular.validator.SupervisorValidator;

@Controller
@RequestMapping(value = "/admin/supervisor")
public class SupervisorAdminController {
	
	private final static String VIEW_INDEX = "admin-supervisor/index";
	private final static String REDIRECT_VIEW_INDEX = "redirect:/admin/supervisor";
	private final static String VIEW_FORM = "admin-supervisor/form";
	private final static int QUANTIDADE_ELEMENTOS_POR_PAGINA = 15;
	private int qtdePaginas;
	private int marker = 0;
	
	@Autowired
	private SupervisorService service;
	
	@Autowired
	private CelulaService celulaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private SupervisorValidator validator;
	
	@InitBinder("supervisor")
	public void a(WebDataBinder binder) {
		binder.registerCustomEditor(Celula.class, new CustomCelulaEditor(celulaService));
		binder.setValidator(validator);
	}

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);

		marker = 0;

		Page<Supervisor> registros = service.buscarTodos(SupervisorPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		
		view.addObject("registros", registros.getContent());
		
		view.addObject("pagina", registros.getTotalPages());

		return view;
	}

	@RequestMapping(value = "novo", method = RequestMethod.GET)
	public ModelAndView novo() {
		Supervisor supervisor = new Supervisor();
		supervisor.setUsuario(new Usuario());
		supervisor.getUsuario().setLogin(" ");
		ModelAndView view = new ModelAndView(VIEW_FORM, "supervisor", supervisor);
		view.addObject("celulas", celulaService.buscarTodos(CelulaPredicate.buscarPorUsuarioNulo()));
		return view;
	}

	@RequestMapping(value = "editar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable("id") Long id) {
		Supervisor supervisor = service.buscarRegistro(id);
		ModelAndView view = new ModelAndView(VIEW_FORM, "supervisor", supervisor);
		supervisor.getUsuario().setCelulas(celulaService.buscarTodos(CelulaPredicate.buscarPor(supervisor.getUsuario())));
		view.addObject("celulas", celulaService.buscarTodos(CelulaPredicate.buscarPorUsuarioNulo()));
		return view;
	}

	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute("supervisor") @Validated Supervisor supervisor, BindingResult errors, RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView(REDIRECT_VIEW_INDEX);
		try {
			if (errors.hasErrors()) {
				if(supervisor.getUsuario().getId() == null) {
					supervisor.getUsuario().setSenha("");
				}
				view = new ModelAndView(VIEW_FORM, "supervisor", supervisor);
				view.addObject("celulas", celulaService.buscarTodos(CelulaPredicate.buscarPorUsuarioNulo()));
				view.addObject("mensagem", "Favor verificar se todos os campos foram preenchidos corretamente, caso o problema insista entre em contato com o administrador do sistema.");
				view.addObject("status", "danger");
				view.addObject("icon", "times");
			}
			
			supervisor.getUsuario().setTipo(TipoUsuario.SUPERVISOR);
			usuarioService.salvar(supervisor.getUsuario());
			this.service.salvar(supervisor);
			
			if(supervisor.getUsuario().getCelulas() != null && supervisor.getUsuario().getCelulas().size() > 0) {
				supervisor.getUsuario().getCelulas().forEach(c -> c.setSupervisor(supervisor));
				celulaService.salvar(supervisor.getUsuario().getCelulas());
			}
			
			service.salvar(supervisor);
			
			redirect.addFlashAttribute("mensagem", "Registro salvo com sucesso.");
			redirect.addFlashAttribute("status", "success");
		} catch (Exception e) {
			if(supervisor.getUsuario().getId() == null) {
				supervisor.getUsuario().setSenha("");
			}
			view = new ModelAndView(VIEW_FORM, "supervisor", supervisor);
			view.addObject("celulas", celulaService.buscarTodos(CelulaPredicate.buscarPorUsuarioNulo()));
			view.addObject("mensagem", e.getMessage());
			view.addObject("status", "danger");
			view.addObject("icon", "times");
		}
		return view;
	}

	@RequestMapping(value = "excluir/{id}", method = RequestMethod.GET)
	public ModelAndView excluir(@PathVariable("id") Long id, RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView(REDIRECT_VIEW_INDEX);
		try {
			Supervisor supervisor = service.buscarRegistro(id);
			List<Celula> celulas = celulaService.buscarTodos(CelulaPredicate.buscarPor(supervisor));
			celulas.forEach(c -> c.setSupervisor(null));
			celulaService.salvar(celulas);
			
			this.service.excluir(id);
			redirect.addFlashAttribute("mensagem", "Registro excluído com sucesso.");
			redirect.addFlashAttribute("status", "success");
		} catch (Exception e) {
			redirect.addFlashAttribute("mensagem", e.getMessage());
			redirect.addFlashAttribute("status", "error");
		}

		return view;
	}

	@RequestMapping(value = "previous", method = RequestMethod.POST)
	public ModelAndView apiPrevious() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);

		Page<Supervisor> registros = service.buscarTodos(SupervisorPredicate.buscarPaginacao(--marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		view.addObject("registros", registros.getContent());

		return view;
	}

	@RequestMapping(value = "next", method = RequestMethod.POST)
	public ModelAndView apiNext() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);

		Page<Supervisor> registros = service.buscarTodos(SupervisorPredicate.buscarPaginacao(++marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		view.addObject("registros", registros.getContent());

		return view;
	}

	@RequestMapping(value = "find/{condicao}", method = RequestMethod.POST)
	public ModelAndView apiFind(@PathVariable("condicao") String nome) {
		ModelAndView view = new ModelAndView();

		Page<Supervisor> users = service.buscarTodos(
				SupervisorPredicate.buscarPorNomeComFiltro(nome), 
				SupervisorPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));

		view.addObject("registros", users.getContent());
		view.addObject("pagina", qtdePaginas);

		return view;
	}
	
	@RequestMapping(value = "find/area/{condicao}", method = RequestMethod.POST)
	public ModelAndView apiFind(@PathVariable("condicao") Integer area) {
		ModelAndView view = new ModelAndView();

		Page<Supervisor> users = service.buscarTodos(
				SupervisorPredicate.buscarPorArea(area), 
				SupervisorPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));

		view.addObject("registros", users.getContent());
		view.addObject("pagina", qtdePaginas);

		return view;
	}
	
	@RequestMapping(value = "/alterar-senha/{id}", method = RequestMethod.GET)
	public ModelAndView resetarSenha(@PathVariable ("id") Long id, RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView(REDIRECT_VIEW_INDEX);
		try {
			Usuario usuario = usuarioService.buscarRegistro(id);
			usuario.setSenha(new DiscipularPasswordEncoder().encode(usuario.getLogin() + "123"));
			usuarioService.salvar(usuario);
			redirect.addFlashAttribute("mensagem", "Senha do supervisor " + usuario.getNome() + " foi alterada com sucesso.");
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
