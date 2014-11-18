package br.com.discipular.controller.supervisor;

import java.util.ArrayList;
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
		
		Page<Celula> registros = service.buscarTodos(CelulaPredicate.buscarPorCelulaAtivaEArea(getCurrentUser().getArea()), CelulaPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		registros.getContent().stream().parallel().forEach(c -> c.setQtdeMembros(membroService.count(MembroPredicate.buscarPor(c))));

		view.addObject("registros", registros.getContent());
		view.addObject("pagina", registros.getTotalPages());
		
		return view;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo() {
		ModelAndView view = new ModelAndView(VIEW_FORM, "celula", new Celula());
		view.addObject("dias", DiaSemana.values());
		view.addObject("horarios", Horario.values());
		view.addObject("usuarios", usuarioService.buscarTodos(UsuarioPredicate.buscarPorTipoSemCelula(TipoUsuario.LIDER)));
		view.addObject("supervisores", usuarioService.buscarTodos(UsuarioPredicate.buscarTipo(TipoUsuario.SUPERVISOR)));
		return view;
	}

	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable ("id") Long id) {
		Celula celula = service.buscarRegistro(id);
		ModelAndView view = new ModelAndView(VIEW_FORM, "celula", celula);
		
		view.addObject("dias", DiaSemana.values());
		view.addObject("horarios", Horario.values());
		view.addObject("usuarios", getLideres(celula));
		view.addObject("supervisores", getSupervisor(celula));

		return view;
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute ("celula") @Validated Celula celula, BindingResult errors, RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView(VIEW_REDIRECT_INDEX);
		if(errors.hasErrors()) {
			view = new ModelAndView(VIEW_FORM, "celula", celula);
			view.addObject("mensagem", "Favor verificar se todos os campos foram preenchidos corretamente, caso o problema insista entre em contato com o administrador do sistema.");
			view.addObject("status", "danger");
			view.addObject("icon", "times");
			view.addObject("dias", DiaSemana.values());
			view.addObject("horarios", Horario.values());
			view.addObject("usuarios", getLideres(celula));
			view.addObject("supervisores", getSupervisor(celula));
			
		} else {
			try {
				this.service.salvar(celula);
				redirect.addFlashAttribute("mensagem", "Registro salvo com sucesso.");
				redirect.addFlashAttribute("status", "success");
				redirect.addFlashAttribute("icon", "check");
			} catch(Exception e) {
				view = new ModelAndView(VIEW_FORM, "celula", celula);
				view.addObject("dias", DiaSemana.values());
				view.addObject("horarios", Horario.values());
				view.addObject("usuarios", getLideres(celula));
				view.addObject("supervisores", getSupervisor(celula));
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
			redirect.addFlashAttribute("mensagem", "Registro excluído com sucesso.");
			redirect.addFlashAttribute("status", "success");
			redirect.addFlashAttribute("icon", "check");
		} catch(Exception e) {
			redirect.addFlashAttribute("mensagem", e.getMessage());
			redirect.addFlashAttribute("status", "error");
			redirect.addFlashAttribute("icon", "times");
		}
		
		return view;
	}
	
	@RequestMapping(value = "/previous", method = RequestMethod.POST)
	public ModelAndView apiPrevious() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Page<Celula> registros = service.buscarTodos(CelulaPredicate.buscarPorCelulaAtivaEArea(getCurrentUser().getArea()), CelulaPredicate.buscarPaginacao(--marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		registros.getContent().stream().parallel().forEach(c -> c.setQtdeMembros(membroService.count(MembroPredicate.buscarPor(c))));
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "next", method = RequestMethod.POST)
	public ModelAndView apiNext() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Page<Celula> registros = service.buscarTodos(CelulaPredicate.buscarPorCelulaAtivaEArea(getCurrentUser().getArea()), CelulaPredicate.buscarPaginacao(++marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		registros.getContent().stream().parallel().forEach(c -> c.setQtdeMembros(membroService.count(MembroPredicate.buscarPor(c))));
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "/find/{condicao}", method = RequestMethod.POST)
	public ModelAndView apiFind(@PathVariable ("condicao") String nome) {
		ModelAndView view = new ModelAndView();
		Page<Celula> registros = service.buscarTodos(CelulaPredicate.buscarPorNomeComFiltroCelulaAtivaEArea(nome, getCurrentUser()), CelulaPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		registros.getContent().stream().parallel().forEach(c -> c.setQtdeMembros(membroService.count(MembroPredicate.buscarPor(c))));
		view.addObject("registros", registros.getContent());
		view.addObject("pagina", qtdePaginas);
		
		return view;
	}
	
	private List<Usuario> getSupervisor(Celula celula) {
		List<Usuario> supervisores = usuarioService.buscarTodos(UsuarioPredicate.buscarTipo(TipoUsuario.SUPERVISOR));
		
		if(celula.getSupervisor() != null) {
			supervisores.removeIf(s -> s.getId() == celula.getSupervisor().getId());
		}
		
		return supervisores;
				
	}
	
	private List<Usuario> getLideres(Celula celula) {
		List<Usuario> lideres = new ArrayList<>();
		
		if(celula.getUsuario() != null) {
			lideres.add(celula.getUsuario());
			lideres.addAll(usuarioService.buscarTodos(UsuarioPredicate.buscarLiderSemCelulaDiferente(celula.getUsuario())));
		} else {
			lideres.addAll(usuarioService.buscarTodos(UsuarioPredicate.buscarPorTipoSemCelula(TipoUsuario.LIDER)));
		}
		return lideres;
	}
	
}