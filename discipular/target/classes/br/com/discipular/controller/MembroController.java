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

import br.com.discipular.model.Membro;
import br.com.discipular.predicate.MembroPredicate;
import br.com.discipular.service.MembroService;
import br.com.discipular.validator.MembroValidator;

/**
 * Controller do modelo {@link Membro}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	08/09/2014 
 * 
 * TODO buscar por usuário logado
 */
@Controller
@RequestMapping(value = "/membro")
public class MembroController {

	private final static String VIEW_INDEX = "membro/index";
	private final static String VIEW_FORM = "membro/form";
	private final static String VIEW_REDIRECT_INDEX = "redirect:/membro";
	private final static int QUANTIDADE_ELEMENTOS_POR_PAGINA = 8;
	private int qtdePaginas;
	private int marker = 0;
	
	@Autowired
	private MembroService service;

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
		
		Page<Membro> registros = service.buscarTodos(MembroPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		qtdePaginas = registros.getTotalPages();
		view.addObject("registros", registros.getContent());
		view.addObject("pagina", qtdePaginas);
		
		return view;
	}
	
	@RequestMapping(value = "novo", method = RequestMethod.GET)
	public ModelAndView novo() {
		ModelAndView view = new ModelAndView(VIEW_FORM, "membro", new Membro());
		return view;
	}
	
	@RequestMapping(value = "editar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable ("id") Long id) {
		Membro membro = service.buscarRegistro(id);
		ModelAndView view = new ModelAndView(VIEW_FORM, "membro", membro);
		return view;
	}
	
	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute ("membro") @Validated Membro membro, BindingResult errors, RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView(VIEW_REDIRECT_INDEX);
		if(errors.hasErrors()) {
			view = new ModelAndView(VIEW_FORM, "membro", membro);
			view.addObject("mensagem", "Reveja os campos");
			view.addObject("status", "error");
		} else {
			try {
				this.service.salvar(membro);
				redirect.addFlashAttribute("mensagem", "Registro salvo com sucesso.");
				redirect.addFlashAttribute("status", "success");
			} catch(Exception e) {
				view = new ModelAndView(VIEW_FORM, "membro", membro);
				view.addObject("mensagem", e.getMessage());
				view.addObject("status", "error");
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
		} catch(Exception e) {
			redirect.addFlashAttribute("mensagem", e.getMessage());
			redirect.addFlashAttribute("status", "error");
		}
		
		return view;
	}
	
	@RequestMapping(value = "previous", method = RequestMethod.POST)
	public ModelAndView apiPrevious() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Page<Membro> registros = service.buscarTodos(MembroPredicate.buscarPaginacao(--marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "next", method = RequestMethod.POST)
	public ModelAndView apiNext() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Page<Membro> registros = service.buscarTodos(MembroPredicate.buscarPaginacao(++marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "find/{condicao}", method = RequestMethod.POST)
	public ModelAndView apiFind(@PathVariable ("condicao") String nome) {
		ModelAndView view = new ModelAndView();
		
		Page<Membro> users = service.buscarTodos(MembroPredicate.buscarPorNomeComFiltro(nome), MembroPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		
		view.addObject("registros", users.getContent());
		view.addObject("pagina", qtdePaginas);
		
		return view;
	}
	
}
