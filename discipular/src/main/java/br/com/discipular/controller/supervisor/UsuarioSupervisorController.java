package br.com.discipular.controller.supervisor;

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

import br.com.discipular.annotations.SupervisorRoles;
import br.com.discipular.context.security.DiscipularPasswordEncoder;
import br.com.discipular.controller.admin.AbstractAdminController;
import br.com.discipular.enumerator.TipoUsuario;
import br.com.discipular.model.Celula;
import br.com.discipular.model.Relatorio;
import br.com.discipular.model.Usuario;
import br.com.discipular.predicate.CelulaPredicate;
import br.com.discipular.predicate.RelatorioPredicate;
import br.com.discipular.predicate.UsuarioPredicate;
import br.com.discipular.service.CelulaService;
import br.com.discipular.service.RelatorioService;
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
@SupervisorRoles
@RequestMapping(value = "/supervisor/lider")
public class UsuarioSupervisorController  extends AbstractAdminController {

	private final static String VIEW_INDEX = "supervisor-usuario/index";
	private final static String VIEW_FORM = "supervisor-usuario/form";
	private final static String VIEW_REDIRECT_INDEX = "redirect:/supervisor/lider";
	private final static int QUANTIDADE_ELEMENTOS_POR_PAGINA = 15;
	private int qtdePaginas;
	private int marker = 0;
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private CelulaService celulaService;
	
	@Autowired
	private RelatorioService relatorioService;
	
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
		
		Page<Usuario> registros = service.getRepositorio().findAll(UsuarioPredicate.buscarTipoEArea(TipoUsuario.LIDER, getCurrentUser()), UsuarioPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		
		registros.getContent().forEach(u ->  {
			List<Celula> celula = (List<Celula>) celulaService.getRepositorio().findAll(CelulaPredicate.buscarPorLider(u));
			if(celula != null && celula.size() > 0) {
				u.setCelula(celula.get(0).getNome());
			}
		});
		
		view.addObject("registros", registros.getContent());
		qtdePaginas = registros.getTotalPages();
		
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
			usuario.setArea(getCurrentUser().getArea());
			usuario.setTipo(TipoUsuario.LIDER);
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
			
			List<Celula> celulas = (List<Celula>) this.celulaService.getRepositorio().findAll(CelulaPredicate.buscarPorLider(usuario));
			celulas.forEach(c -> c.setUsuario(null));
			this.celulaService.getRepositorio().save(celulas);
			
			List<Relatorio> relatorios = (List<Relatorio>) this.relatorioService.getRepositorio().findAll(RelatorioPredicate.buscarPor(usuario));
			relatorios.forEach(r -> r.setUsuario(null));
			this.relatorioService.salvar(relatorios);
			
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
		
		Page<Usuario> registros = service.getRepositorio().findAll(UsuarioPredicate.buscarTipoEArea(TipoUsuario.LIDER, getCurrentUser()), UsuarioPredicate.buscarPaginacao(--marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		registros.getContent().forEach(u -> u.setCelula(((List<Celula>) celulaService.getRepositorio().findAll(CelulaPredicate.buscarPorLider(u))).get(0).getNome()));
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "/next", method = RequestMethod.POST)
	public ModelAndView apiNext() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Page<Usuario> registros = service.getRepositorio().findAll(UsuarioPredicate.buscarTipoEArea(TipoUsuario.LIDER, getCurrentUser()), UsuarioPredicate.buscarPaginacao(++marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		registros.getContent().forEach(u -> u.setCelula(((List<Celula>) celulaService.getRepositorio().findAll(CelulaPredicate.buscarPorLider(u))).get(0).getNome()));
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "/find/{condicao}", method = RequestMethod.POST)
	public ModelAndView apiFind(@PathVariable ("condicao") String nome) {
		ModelAndView view = new ModelAndView();

		Page<Usuario> registros = service.getRepositorio().findAll(UsuarioPredicate.buscarPorNomeComFiltroTipoEArea(nome, TipoUsuario.LIDER, getCurrentUser()), UsuarioPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		
		registros.getContent().forEach(u -> {
			List<Celula> celula = (List<Celula>) celulaService.getRepositorio().findAll(CelulaPredicate.buscarPorLider(u));
			if(celula != null && celula.size() > 0) {
				u.setCelula(celula.get(0).getNome());
			} else {
				u.setCelula("");
			}
		});
		
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
			loadRedirectSuccessView(redirect, "Senha do líder " + usuario.getNome() + " foi alterada com sucesso.");
		} catch (Exception e) {
			loadRedirectDangerView(redirect, e.getMessage());
		}
		return view;
	}
	
}