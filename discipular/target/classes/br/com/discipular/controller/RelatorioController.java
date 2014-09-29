package br.com.discipular.controller;

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

import br.com.discipular.enumerator.TipoChamada;
import br.com.discipular.model.Relatorio;
import br.com.discipular.predicate.MembroPredicate;
import br.com.discipular.predicate.RelatorioPredicate;
import br.com.discipular.service.MembroService;
import br.com.discipular.service.RelatorioService;
import br.com.discipular.validator.RelatorioValidator;

/**
 * Controller do modelo {@link Relatorio}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	12/09/2014
 * 
 *  TODO buscar pelo Usuário logado
 */ 
@Controller
@RequestMapping(value = "/relatorio")
public class RelatorioController extends AbstractController {

	private final static String VIEW_INDEX = "relatorio/index";
	private final static String VIEW_FORM = "relatorio/form";
	private final static String VIEW_REDIRECT_INDEX = "redirect:/relatorio";
	private final static int QUANTIDADE_ELEMENTOS_POR_PAGINA = 8;
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
		view.addObject("registros", registros.getContent());
		view.addObject("pagina", qtdePaginas);
		
		return view;
	}
	
	@RequestMapping(value = "novo", method = RequestMethod.GET)
	public ModelAndView novo() {
		ModelAndView view = new ModelAndView(VIEW_FORM, "relatorio", new Relatorio());
		view.addObject("membros", membroService.buscarTodos(MembroPredicate.buscarPorCelula(getCurrentUser().getCelula())));
		view.addObject("chamadas", TipoChamada.values());
		return view;
	}
	
	@RequestMapping(value = "editar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable ("id") Long id) {
		Relatorio relatorio = service.buscarRegistro(id);
		ModelAndView view = new ModelAndView(VIEW_FORM, "relatorio", relatorio);
		return view;
	}
	
	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute ("relatorio") @Validated Relatorio relatorio, BindingResult errors, RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView(VIEW_REDIRECT_INDEX);
		if(errors.hasErrors()) {
			view = new ModelAndView(VIEW_FORM, "relatorio", relatorio);
			view.addObject("mensagem", "Reveja os campos");
			view.addObject("status", "danger");
			view.addObject("icon", "times");
		} else {
			try {
				relatorio.setCelula(getCurrentUser().getCelula());
				this.service.salvar(relatorio);
				redirect.addFlashAttribute("mensagem", "Registro salvo com sucesso.");
				redirect.addFlashAttribute("status", "success");
				redirect.addFlashAttribute("icon", "check");
			} catch(Exception e) {
				view = new ModelAndView(VIEW_FORM, "relatorio", relatorio);
				view.addObject("mensagem", e.getMessage());
				view.addObject("status", "error");
				view.addObject("icon", "times");
			}
		}
		return view;
	}
	
	@RequestMapping(value = "excluir/{id}", method = RequestMethod.GET)
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
	
	@RequestMapping(value = "previous", method = RequestMethod.POST)
	public ModelAndView apiPrevious() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Page<Relatorio> registros = service.buscarTodos(RelatorioPredicate.buscarPaginacao(--marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "next", method = RequestMethod.POST)
	public ModelAndView apiNext() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Page<Relatorio> registros = service.buscarTodos(RelatorioPredicate.buscarPaginacao(++marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "find/{condicao}", method = RequestMethod.POST)
	public ModelAndView apiFind(@PathVariable ("condicao") String nome) {
		ModelAndView view = new ModelAndView();
		
		Page<Relatorio> users = service.buscarTodos(RelatorioPredicate.buscarPorNomeComFiltro(nome), RelatorioPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		
		view.addObject("registros", users.getContent());
		view.addObject("pagina", qtdePaginas);
		
		return view;
	}
	
}