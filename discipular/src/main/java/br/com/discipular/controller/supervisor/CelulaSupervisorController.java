package br.com.discipular.controller.supervisor;

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

import br.com.discipular.annotations.SupervisorRoles;
import br.com.discipular.controller.admin.AbstractAdminController;
import br.com.discipular.editor.CustomUsuarioEditor;
import br.com.discipular.enumerator.DiaSemana;
import br.com.discipular.enumerator.Horario;
import br.com.discipular.enumerator.TipoUsuario;
import br.com.discipular.model.Celula;
import br.com.discipular.model.Usuario;
import br.com.discipular.predicate.CelulaPredicate;
import br.com.discipular.predicate.MembroPredicate;
import br.com.discipular.predicate.UsuarioPredicate;
import br.com.discipular.service.CelulaService;
import br.com.discipular.service.MembroService;
import br.com.discipular.service.UsuarioService;
import br.com.discipular.validator.CelulaValidator;



/**
 * Controller do modelo {@link Celula}
 * 
 * @author Lucas Campos
 * @since 1.0.0
 * @version 1.0.0
 *
 * 	Nov 15, 2014 
 */
@Controller
@SupervisorRoles
@RequestMapping(value = "/supervisor/celula")
public class CelulaSupervisorController extends AbstractAdminController {

	private final static String VIEW_INDEX = "supervisor-celula/index";
	private final static String VIEW_FORM = "supervisor-celula/form";
	private final static String VIEW_REDIRECT_INDEX = "redirect:/supervisor/celula";
	private final static int QUANTIDADE_ELEMENTOS_POR_PAGINA = 15;
	private int qtdePaginas;
	private int marker = 0;
	
	@Autowired
	private CelulaService service;
	
	@Autowired
	private MembroService membroService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private CelulaValidator validator;
	
	@InitBinder("celula")
	public void a(WebDataBinder binder) {
		binder.registerCustomEditor(Usuario.class, new CustomUsuarioEditor(usuarioService));
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		marker = 0;
		
		Page<Celula> registros = service.getRepositorio().findAll(CelulaPredicate.buscarPorCelulaAtivaEArea(getCurrentUser().getArea()), CelulaPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		registros.getContent().stream().parallel().forEach(c -> c.setQtdeMembros(membroService.getRepositorio().count(MembroPredicate.buscarPor(c))));

		view.addObject("registros", registros.getContent());
		view.addObject("pagina", registros.getTotalPages());
		
		return view;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo() {
		ModelAndView view = new ModelAndView(VIEW_FORM, "celula", new Celula());
		view.addObject("dias", DiaSemana.values());
		view.addObject("horarios", Horario.values());
		view.addObject("usuarios", usuarioService.getRepositorio().findAll(UsuarioPredicate.buscarPorTipoSemCelula(TipoUsuario.LIDER)));
		return view;
	}

	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable ("id") Long id) {
		Celula celula = service.getRepositorio().findOne(id);
		ModelAndView view = new ModelAndView(VIEW_FORM, "celula", celula);
		
		view.addObject("dias", DiaSemana.values());
		view.addObject("horarios", Horario.values());
		view.addObject("usuarios", usuarioService.buscarLideresSemCelula(celula));

		return view;
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute ("celula") @Validated Celula celula, BindingResult errors, RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView(VIEW_REDIRECT_INDEX);
		if(errors.hasErrors()) {
			view = new ModelAndView(VIEW_FORM, "celula", celula);
			view.addObject("dias", DiaSemana.values());
			view.addObject("horarios", Horario.values());
			view.addObject("usuarios", usuarioService.buscarLideresSemCelula(celula));
			loadViewDangerView(view, "Favor verificar se todos os campos foram preenchidos corretamente, caso o problema insista entre em contato com o administrador do sistema.");
		} else {
			try {
				Usuario usuario = usuarioService.getRepositorio().findOne(UsuarioPredicate.buscarPorLogin(getCurrentUser().getLogin()));
				celula.setSupervisor(usuario);
				this.service.salvar(celula);
				loadRedirectSuccessView(redirect, "Registro salvo com sucesso.");
			} catch(Exception e) {
				view = new ModelAndView(VIEW_FORM, "celula", celula);
				view.addObject("dias", DiaSemana.values());
				view.addObject("horarios", Horario.values());
				view.addObject("usuarios", usuarioService.buscarLideresSemCelula(celula));
				loadViewDangerView(view, e.getMessage());
			}
		}
		return view;
	}
	
	@RequestMapping(value = "/excluir/{id}", method = RequestMethod.GET)
	public ModelAndView excluir(@PathVariable ("id") Long id, RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView(VIEW_REDIRECT_INDEX);
		try {
			this.service.excluir(id);
			loadRedirectSuccessView(redirect, "Registro excluído com sucesso.");
		} catch(Exception e) {
			loadRedirectDangerView(redirect, e.getMessage());
		}
		
		return view;
	}
	
	@RequestMapping(value = "/previous", method = RequestMethod.POST)
	public ModelAndView apiPrevious() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Page<Celula> registros = service.getRepositorio().findAll(CelulaPredicate.buscarPorCelulaAtivaEArea(getCurrentUser().getArea()), CelulaPredicate.buscarPaginacao(--marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		registros.getContent().stream().parallel().forEach(c -> c.setQtdeMembros(membroService.getRepositorio().count(MembroPredicate.buscarPor(c))));
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "next", method = RequestMethod.POST)
	public ModelAndView apiNext() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Page<Celula> registros = service.getRepositorio().findAll(CelulaPredicate.buscarPorCelulaAtivaEArea(getCurrentUser().getArea()), CelulaPredicate.buscarPaginacao(++marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		registros.getContent().stream().parallel().forEach(c -> c.setQtdeMembros(membroService.getRepositorio().count(MembroPredicate.buscarPor(c))));
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "/find/{condicao}", method = RequestMethod.POST)
	public ModelAndView apiFind(@PathVariable ("condicao") String nome) {
		ModelAndView view = new ModelAndView();
		Page<Celula> registros = service.getRepositorio().findAll(CelulaPredicate.buscarPorNomeComFiltroCelulaAtivaEArea(nome, getCurrentUser()), CelulaPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		registros.getContent().stream().parallel().forEach(c-> {
			c.setHorarioFormatado(c.getHorario().getHorario());
			c.setQtdeMembros(membroService.getRepositorio().count(MembroPredicate.buscarPor(c)));
		});
		
		view.addObject("registros", registros.getContent());
		view.addObject("pagina", qtdePaginas);
		
		return view;
	}
	
}
