package br.com.discipular.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.discipular.config.support.anotacao.Administrador;
import br.com.discipular.domain.model.Chamada;
import br.com.discipular.domain.model.Relatorio;
import br.com.discipular.domain.predicate.ChamadaPredicate;
import br.com.discipular.domain.predicate.RelatorioPredicate;
import br.com.discipular.domain.repository.ChamadaRepository;
import br.com.discipular.service.RelatorioService;
import br.com.discipular.web.validator.RelatorioValidator;

@Controller
@Administrador
@RequestMapping(value = "/admin/relatorio")
public class RelatorioAdminController extends AbstractAdminController {

	private final static String VIEW_INDEX = "admin/relatorio/index";
	private final static String VIEW_VISUALIZAR = "admin/relatorio/visualizar";
	
	private final static int QUANTIDADE_ELEMENTOS_POR_PAGINA = 15;
	private int qtdePaginas;
	private int marker = 0;
	
	@Autowired
	private RelatorioService service;
	
	@Autowired
	private ChamadaRepository chamadaRepository;
	
	@Autowired
	private RelatorioValidator validator;
	
	@InitBinder("relatorio")
	public void a(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		marker = 0;
		
		Page<Relatorio> registros = service.getRepositorio().findAll(RelatorioPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		
		qtdePaginas = registros.getTotalPages();
		view.addObject("registros", registros.getContent());
		view.addObject("pagina", qtdePaginas);
		view.addObject("modulo", "admin/relatorio");
		
		return view;
	}
	
	@RequestMapping(value = "/visualizar/{id}", method = RequestMethod.GET)
	public ModelAndView visualizar(@PathVariable ("id") Long id) {
		ModelAndView view = new ModelAndView(VIEW_VISUALIZAR);
		
		Relatorio relatorio = this.service.getRepositorio().findOne(id);
		List<Chamada> chamadas = (List<Chamada>) chamadaRepository.findAll(ChamadaPredicate.buscarPor(relatorio));
		relatorio.setChamada(chamadas);
		view.addObject("modulo", "admin/relatorio");
		view.addObject("relatorio", relatorio);
		view.addObject("chamadas", chamadas);
		
		return view;
	}
	
	@RequestMapping(value = "previous", method = RequestMethod.POST)
	public ModelAndView apiPrevious() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Page<Relatorio> registros = service.getRepositorio().findAll(RelatorioPredicate.buscarPaginacao(--marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "next", method = RequestMethod.POST)
	public ModelAndView apiNext() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Page<Relatorio> registros = service.getRepositorio().findAll(RelatorioPredicate.buscarPaginacao(++marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "find/{condicao}", method = RequestMethod.POST)
	public ModelAndView apiFind(@PathVariable ("condicao") String celula) {
		ModelAndView view = new ModelAndView();
		
		Page<Relatorio> registros = service.getRepositorio().findAll(RelatorioPredicate.buscarPor(celula), RelatorioPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));

		view.addObject("registros", registros.getContent());
		view.addObject("pagina", qtdePaginas);
		
		return view;
	}
	
}
