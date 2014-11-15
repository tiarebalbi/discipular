package br.com.discipular.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.discipular.annotations.SupervisorRoles;
import br.com.discipular.model.Membro;
import br.com.discipular.model.Supervisor;
import br.com.discipular.model.Usuario;
import br.com.discipular.predicate.MembroPredicate;
import br.com.discipular.predicate.SupervisorPredicate;
import br.com.discipular.service.CelulaService;
import br.com.discipular.service.MembroService;
import br.com.discipular.service.SupervisorService;
import br.com.discipular.utils.DataUtils;
import br.com.discipular.validator.MembroValidator;

@Controller
@SupervisorRoles
@RequestMapping(value = "admin/membro")
public class MembroAdminController extends AbstractAdminController {
	
	private final static String VIEW_INDEX = "admin/membro/index";
	private final static int QUANTIDADE_ELEMENTOS_POR_PAGINA = 15;
	private int qtdePaginas;
	private int marker = 0;
	
	@Autowired
	private MembroService service;
	
	@Autowired
	private CelulaService celulaService;
	
	@Autowired
	private SupervisorService supervisorService;

	@Autowired
	private MembroValidator validator;
	
	@InitBinder("membro")
	public void a(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		marker = 0;
		Page<Membro> registros = null;
		Usuario usuario = getCurrentUser();
		if(usuario.getLogin().equals("admin")) {
			registros = service.buscarTodos(MembroPredicate.buscarPaginacaoAdmin(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		} else {
			Supervisor supervisor = supervisorService.buscarRegistro(SupervisorPredicate.buscarPor(usuario));
			registros = service.buscarTodos(MembroPredicate.buscarPorArea(supervisor.getArea()), MembroPredicate.buscarPaginacaoAdmin(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		}
		registros.getContent().stream().parallel().forEach(membro -> membro.setData(DataUtils.formatDataPtBr(membro.getDataNascimento())));
		
		view.addObject("registros", registros.getContent());
		view.addObject("pagina", registros.getTotalPages());
		
		return view;
	}
	
	@RequestMapping(value = "previous", method = RequestMethod.POST)
	public ModelAndView apiPrevious() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Page<Membro> registros = service.buscarTodos(MembroPredicate.buscarPaginacaoAdmin(--marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		registros.getContent().stream().parallel().forEach(membro -> membro.setData(DataUtils.formatDataPtBr(membro.getDataNascimento())));
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "next", method = RequestMethod.POST)
	public ModelAndView apiNext() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Page<Membro> registros = service.buscarTodos(MembroPredicate.buscarPaginacaoAdmin(++marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		registros.getContent().stream().parallel().forEach(membro -> membro.setData(DataUtils.formatDataPtBr(membro.getDataNascimento())));
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "find/{condicao}", method = RequestMethod.POST)
	public ModelAndView apiFind(@PathVariable ("condicao") String nome) {
		ModelAndView view = new ModelAndView();
		
		Page<Membro> registros = service.buscarTodos(MembroPredicate.buscarPorCelulaComFiltro(nome), MembroPredicate.buscarPaginacaoAdmin(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		registros.getContent().stream().parallel().forEach(membro -> membro.setData(DataUtils.formatDataPtBr(membro.getDataNascimento())));
		view.addObject("registros", registros.getContent());
		view.addObject("pagina", qtdePaginas);
		
		return view;
	}

}
