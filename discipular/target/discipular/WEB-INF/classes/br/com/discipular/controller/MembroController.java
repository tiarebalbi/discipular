package br.com.discipular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
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

import br.com.discipular.annotations.Lider;
import br.com.discipular.enumerator.TipoMembro;
import br.com.discipular.model.Celula;
import br.com.discipular.model.Membro;
import br.com.discipular.predicate.CelulaPredicate;
import br.com.discipular.predicate.MembroPredicate;
import br.com.discipular.service.CelulaService;
import br.com.discipular.service.MembroService;
import br.com.discipular.utils.DataUtils;
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
 */
@Lider
@Controller
@RequestMapping(value = "/membro")
public class MembroController extends AbstractController {

	private final static String VIEW_INDEX = "membro/index";
	private final static String VIEW_FORM = "membro/form";
	private final static String VIEW_REDIRECT_INDEX = "redirect:/membro";
	private final static String REDIRECT_INDEX = "redirect:/";
	private final static int QUANTIDADE_ELEMENTOS_POR_PAGINA = 15;
	
	@Autowired
	private MembroService service;
	
	@Autowired
	private CelulaService celulaService;

	@Autowired
	private MembroValidator validator;
	
	@InitBinder("membro")
	public void a(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public ModelAndView index(RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		try {
			List<Celula> celula = celulaService.buscarTodos(CelulaPredicate.buscarPor(getCurrentUser()));
			
			Assert.notEmpty(celula, "Seu usuário não tem vínculo com nenhuma célula, favor entrar em contato com o seu supervisor.");
			
			Page<Membro> registros = service.buscarTodos(MembroPredicate.buscarPor(celula.get(0)), MembroPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
			
			registros.getContent().stream().parallel().forEach(membro -> membro.setData(DataUtils.formatDataPtBr(membro.getDataNascimento())));
			
			view.addObject("registros", registros.getContent());
			view.addObject("celula", celula.get(0).getNome());
		} catch (Exception e) {
			view = new ModelAndView(REDIRECT_INDEX);
			redirect.addFlashAttribute("mensagem", e.getMessage());
			redirect.addFlashAttribute("status", "danger");
			redirect.addFlashAttribute("icon", "times");
		}
		
		return view;
	}
	
	@RequestMapping(value = "novo", method = RequestMethod.GET)
	public ModelAndView novo(RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView(VIEW_FORM, "membro", new Membro());

		if(!haveCelula()) {
			view = new ModelAndView(REDIRECT_INDEX);
			redirect.addFlashAttribute("mensagem", "Seu usuário não tem vínculo com nenhuma célula, favor entrar em contato com o seu supervisor.");
			redirect.addFlashAttribute("status", "danger");
			redirect.addFlashAttribute("icon", "times");
			return view;
		}
		
		view.addObject("tipos", TipoMembro.values());	

		return view;
	}
	
	@RequestMapping(value = "editar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable ("id") Long id) {
		Membro membro = service.buscarRegistro(id);
		ModelAndView view = new ModelAndView(VIEW_FORM, "membro", membro);
		view.addObject("tipos", TipoMembro.values());
		return view;
	}
	
	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute ("membro") @Validated Membro membro, BindingResult errors, RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView(VIEW_REDIRECT_INDEX);
		if(errors.hasErrors()) {
			view = new ModelAndView(VIEW_FORM, "membro", membro);
			view.addObject("mensagem", "Favor verificar se todos os campos foram preenchidos corretamente, caso o problema insista entre em contato com o administrador do sistema.");
			view.addObject("status", "danger");
			view.addObject("icon", "times");
			view.addObject("tipos", TipoMembro.values());
		} else {
			try {
				List<Celula> celula = celulaService.buscarTodos(CelulaPredicate.buscarPor(getCurrentUser()));
				membro.setCelula(celula.get(0));
				this.service.salvar(membro);
				redirect.addFlashAttribute("mensagem", "Registro salvo com sucesso.");
				redirect.addFlashAttribute("status", "success");
				redirect.addFlashAttribute("icon", "check");
			} catch(Exception e) {
				view = new ModelAndView(VIEW_FORM, "membro", membro);
				view.addObject("tipos", TipoMembro.values());
				view.addObject("mensagem", e.getMessage());
				view.addObject("status", "danger");
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
			redirect.addFlashAttribute("status", "danger");
			redirect.addFlashAttribute("icon", "times");
		}
		
		return view;
	}
		
}
