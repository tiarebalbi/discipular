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

import br.com.discipular.annotations.Administrador;
import br.com.discipular.model.Relatorio;
import br.com.discipular.predicate.RelatorioPredicate;
import br.com.discipular.service.MembroService;
import br.com.discipular.service.RelatorioService;
import br.com.discipular.utils.DataUtils;
import br.com.discipular.validator.RelatorioValidator;

@Controller
@Administrador
@RequestMapping(value = "/admin/relatorio")
public class RelatorioAdminController extends AbstractAdminController {

	private final static String VIEW_INDEX = "admin/relatorio/index";
	private final static int QUANTIDADE_ELEMENTOS_POR_PAGINA = 15;
	private int qtdePaginas;
	private int marker = 0;
	
	@Autowired
	private RelatorioService service;
	
	@Autowired
	private MembroService membroService;
	
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
		
		Page<Relatorio> registros = service.buscarTodos(RelatorioPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		
		qtdePaginas = registros.getTotalPages();
		registros.getContent().stream().parallel().forEach(relatorio -> relatorio.setDataFormat(DataUtils.formatDataPtBr(relatorio.getData())));
		view.addObject("registros", registros.getContent());
		view.addObject("pagina", qtdePaginas);
		
		return view;
	}
	
	@RequestMapping(value = "previous", method = RequestMethod.POST)
	public ModelAndView apiPrevious() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Page<Relatorio> registros = service.buscarTodos(RelatorioPredicate.buscarPaginacao(--marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		registros.getContent().stream().parallel().forEach(relatorio -> relatorio.setDataFormat(DataUtils.formatDataPtBr(relatorio.getData())));
		
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "next", method = RequestMethod.POST)
	public ModelAndView apiNext() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Page<Relatorio> registros = service.buscarTodos(RelatorioPredicate.buscarPaginacao(++marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		registros.getContent().stream().parallel().forEach(relatorio -> relatorio.setDataFormat(DataUtils.formatDataPtBr(relatorio.getData())));
		
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "find/{condicao}", method = RequestMethod.POST)
	public ModelAndView apiFind(@PathVariable ("condicao") String celula) {
		ModelAndView view = new ModelAndView();
		
		Page<Relatorio> registros = service.buscarTodos(RelatorioPredicate.buscarPor(celula), RelatorioPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		registros.getContent().stream().parallel().forEach(relatorio -> relatorio.setDataFormat(DataUtils.formatDataPtBr(relatorio.getData())));

		view.addObject("registros", registros.getContent());
		view.addObject("pagina", qtdePaginas);
		
		return view;
	}
	
}
