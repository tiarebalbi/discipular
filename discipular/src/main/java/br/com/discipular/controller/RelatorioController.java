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
import br.com.discipular.enumerator.TipoChamada;
import br.com.discipular.model.Celula;
import br.com.discipular.model.Chamada;
import br.com.discipular.model.Relatorio;
import br.com.discipular.predicate.CelulaPredicate;
import br.com.discipular.predicate.ChamadaPredicate;
import br.com.discipular.predicate.MembroPredicate;
import br.com.discipular.predicate.RelatorioPredicate;
import br.com.discipular.service.CelulaService;
import br.com.discipular.service.ChamadaService;
import br.com.discipular.service.MembroService;
import br.com.discipular.service.RelatorioService;
import br.com.discipular.utils.DataUtils;
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
 */ 
@Lider
@Controller
@RequestMapping(value = "/relatorio")
public class RelatorioController extends AbstractController {

	private final static String VIEW_INDEX = "relatorio/index";
	private final static String VIEW_FORM = "relatorio/form";
	private final static String VIEW_REDIRECT_INDEX = "redirect:/relatorio";
	private final static int QUANTIDADE_ELEMENTOS_POR_PAGINA = 15;
	private static final String REDIRECT_INDEX = "redirect:/";
	private int qtdePaginas;
	private int marker = 0;
	
	@Autowired
	private RelatorioService service;
	
	@Autowired
	private MembroService membroService;

	@Autowired
	private ChamadaService chamadaService;
	
	@Autowired
	private CelulaService celulaService;
	
	@Autowired
	private RelatorioValidator validator;
	
	@InitBinder("relatorio")
	public void a(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public ModelAndView index(RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		try {
			Assert.state(haveCelula(), "Seu usuário não tem vínculo com nenhuma célula, favor entrar em contato com o seu supervisor.");
			
			marker = 0;
			
			Celula celula = celulaService.buscarRegistro(CelulaPredicate.buscarPorLider(getCurrentUser()));
			
			Page<Relatorio> registros = service.buscarTodos(RelatorioPredicate.buscarPorUsuarioECelula(celula), RelatorioPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
			qtdePaginas = registros.getTotalPages();
			registros.getContent().stream().parallel().forEach(relatorio -> relatorio.setDataFormat(DataUtils.formatDataPtBr(relatorio.getData())));
			view.addObject("registros", registros.getContent());
			view.addObject("pagina", qtdePaginas);
		} catch (Exception e) {
			view = new ModelAndView(REDIRECT_INDEX);
			loadRedirectDangerView(redirect, e.getMessage());
		}
		
		return view;
		
	}
	
	@RequestMapping(value = "novo", method = RequestMethod.GET)
	public ModelAndView novo(RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView(VIEW_FORM, "relatorio", new Relatorio());
		
		if(!haveCelula()) {
			view = new ModelAndView(REDIRECT_INDEX);
			loadRedirectDangerView(redirect, "Seu usuário não tem vínculo com nenhuma célula, favor entrar em contato com o seu supervisor.");
			return view;
		}
		
		carregarContexto(view);
		return view;
	}
	
	@RequestMapping(value = "editar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable ("id") Long id) {
		Relatorio relatorio = service.buscarRegistro(id);
		ModelAndView view = new ModelAndView(VIEW_FORM, "relatorio", relatorio);
		List<Chamada> chamada = chamadaService.buscarTodos(ChamadaPredicate.buscarPor(relatorio));
		relatorio.setChamada(chamada);
		view.addObject("membros", chamada);
		view.addObject("chamadas", TipoChamada.values());
		return view;
	}
	
	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute ("relatorio") @Validated Relatorio relatorio, BindingResult errors, RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView(VIEW_REDIRECT_INDEX);
		if(errors.hasErrors()) {
			view = new ModelAndView(VIEW_FORM, "relatorio", relatorio);
			List<Chamada> chamada = chamadaService.buscarTodos(ChamadaPredicate.buscarPor(relatorio));
			view.addObject("membros", chamada);
			view.addObject("chamadas", TipoChamada.values());
			loadViewDangerView(view, "Favor verificar se todos os campos foram preenchidos corretamente, caso o problema insista entre em contato com o administrador do sistema.");
		} else {
			try {
				Assert.notNull(relatorio.getChamada(), "Nenhum membro cadastrado nesta célula, favor cadastrar os membros antes de fazer o relatório.");
				int total = 0;
				for(Chamada chamada : relatorio.getChamada()) {
					if(chamada.getTipo() == TipoChamada.PRESENTE) {total++;}
				};
				
				Assert.isTrue(total > 0, "Nenhum membro esteve presente nesta célula!");
				
				chamadaService.salvar(relatorio.getChamada());
				
				Celula celula = celulaService.buscarRegistro(CelulaPredicate.buscarPorLider(getCurrentUser()));
				
				relatorio.setCelula(celula);
				relatorio.setUsuario(getCurrentUser());
				this.service.salvar(relatorio);
				relatorio.getChamada().stream().parallel().forEach(chamada -> chamada.setRelatorio(relatorio));
				chamadaService.salvar(relatorio.getChamada());
				
				loadRedirectSuccessView(redirect, "Registro salvo com sucesso.");
			} catch(Exception e) {
				view = new ModelAndView(VIEW_FORM, "relatorio", relatorio);
				List<Chamada> chamada = chamadaService.buscarTodos(ChamadaPredicate.buscarPor(relatorio));
				view.addObject("membros", chamada);
				view.addObject("chamadas", TipoChamada.values());
				loadViewDangerView(view, e.getMessage());
			}
		}
		return view;
	}
	
	@RequestMapping(value = "excluir/{id}", method = RequestMethod.GET)
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
	
	@RequestMapping(value = "previous", method = RequestMethod.POST)
	public ModelAndView apiPrevious() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
	
		Celula celula = celulaService.buscarRegistro(CelulaPredicate.buscarPorLider(getCurrentUser()));
		Page<Relatorio> registros = service.buscarTodos(RelatorioPredicate.buscarPorUsuarioECelula(celula), RelatorioPredicate.buscarPaginacao(--marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		registros.getContent().stream().parallel().forEach(relatorio -> relatorio.setDataFormat(DataUtils.formatDataPtBr(relatorio.getData())));
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "next", method = RequestMethod.POST)
	public ModelAndView apiNext() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Celula celula = celulaService.buscarRegistro(CelulaPredicate.buscarPorLider(getCurrentUser()));
		Page<Relatorio> registros = service.buscarTodos(RelatorioPredicate.buscarPorUsuarioECelula(celula), RelatorioPredicate.buscarPaginacao(++marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		registros.getContent().stream().parallel().forEach(relatorio -> relatorio.setDataFormat(DataUtils.formatDataPtBr(relatorio.getData())));
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	private ModelAndView carregarContexto(ModelAndView view) {
		List<Celula> celula = celulaService.buscarTodos(CelulaPredicate.buscarPorLider(getCurrentUser()));
		
		view.addObject("membros", membroService.buscarTodos(MembroPredicate.buscarPor(celula.get(0))));
		view.addObject("chamadas", TipoChamada.values());
		
		return view;
	}
}