package br.com.discipular.web.controller.lider;

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

import br.com.discipular.config.support.anotacao.Lider;
import br.com.discipular.domain.enumetator.TipoChamada;
import br.com.discipular.domain.model.Celula;
import br.com.discipular.domain.model.Chamada;
import br.com.discipular.domain.model.Membro;
import br.com.discipular.domain.model.Relatorio;
import br.com.discipular.domain.model.Usuario;
import br.com.discipular.domain.predicate.CelulaPredicate;
import br.com.discipular.domain.predicate.ChamadaPredicate;
import br.com.discipular.domain.predicate.MembroPredicate;
import br.com.discipular.domain.predicate.RelatorioPredicate;
import br.com.discipular.domain.repository.ChamadaRepository;
import br.com.discipular.service.CelulaService;
import br.com.discipular.service.MembroService;
import br.com.discipular.service.RelatorioService;
import br.com.discipular.service.UsuarioService;
import br.com.discipular.web.validator.RelatorioValidator;

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
	private ChamadaRepository chamadaRepository;
	
	@Autowired
	private CelulaService celulaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
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
			
			Celula celula = celulaService.getRepositorio().findOne(CelulaPredicate.buscarPorLider(getCurrentUser().getId()));
			
			Page<Relatorio> registros = service.getRepositorio().findAll(RelatorioPredicate.buscarPorUsuarioECelula(celula), RelatorioPredicate.buscarPaginacao(0, QUANTIDADE_ELEMENTOS_POR_PAGINA));
			qtdePaginas = registros.getTotalPages();
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
		
		try {
			Celula celula = this.celulaService.getRepositorio().findOne(CelulaPredicate.buscarPorLider(getCurrentUser().getId()));
			
			long total = membroService.getRepositorio().count(MembroPredicate.buscarPor(celula));

			if (total == 0) {
				throw new Exception("Nenhum membro cadastrado nesta célula, favor cadastrar os membros antes de fazer o relatório.");
			}
			
			if (!haveCelula()) {
				view = new ModelAndView(REDIRECT_INDEX);
				loadRedirectDangerView(redirect, "Seu usuário não tem vínculo com nenhuma célula, favor entrar em contato com o seu supervisor.");
				return view;
			}
			
			carregarContexto(view);
		} catch (Exception e) {
			view = new ModelAndView(VIEW_INDEX);
			loadViewDangerView(view, "Você ainda não cadastrou nenhum membro.");
		}
		return view;
	}
	
	@RequestMapping(value = "editar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable ("id") Long id) {
		Relatorio relatorio = service.getRepositorio().findOne(id);
		ModelAndView view = new ModelAndView(VIEW_FORM, "relatorio", relatorio);
		List<Chamada> chamada = (List<Chamada>) chamadaRepository.findAll(ChamadaPredicate.buscarPor(relatorio));
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

			if(relatorio.getId() != null) {
				List<Chamada> chamada = (List<Chamada>) chamadaRepository.findAll(ChamadaPredicate.buscarPor(relatorio));
				view.addObject("membros", chamada);
				view.addObject("chamadas", TipoChamada.values());
			} else {
				carregarContexto(view);
			}
			
			
			loadViewDangerView(view, "Favor verificar se todos os campos foram preenchidos corretamente, caso o problema insista entre em contato com o administrador do sistema.");
		} else {
			try {
				Assert.notNull(relatorio.getChamada(), "Nenhum membro cadastrado nesta célula, favor cadastrar os membros antes de fazer o relatório.");
				int total = 0;
				for(Chamada chamada : relatorio.getChamada()) {
					if(chamada.getTipo() == TipoChamada.PRESENTE) {total++;}
				};
				
				Assert.isTrue(total > 0, "Nenhum membro esteve presente nesta célula!");
				
				chamadaRepository.save(relatorio.getChamada());
				
				Usuario usuario = this.usuarioService.getRepositorio().findOne(getCurrentUser().getId());
				
				Celula celula = celulaService.getRepositorio().findOne(CelulaPredicate.buscarPorLider(getCurrentUser().getId()));
				relatorio.setCelula(celula);
				relatorio.setUsuario(usuario);
				this.service.salvar(relatorio);
				relatorio.getChamada().stream().parallel().forEach(chamada -> chamada.setRelatorio(relatorio));
				chamadaRepository.save(relatorio.getChamada());
				
				loadRedirectSuccessView(redirect, "Registro salvo com sucesso.");
			} catch(Exception e) {
				view = new ModelAndView(VIEW_FORM, "relatorio", relatorio);
				List<Chamada> chamada = (List<Chamada>) chamadaRepository.findAll(ChamadaPredicate.buscarPor(relatorio));
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
			this.service.getRepositorio().delete(id);
			loadRedirectSuccessView(redirect, "Registro excluído com sucesso.");
		} catch(Exception e) {
			loadRedirectDangerView(redirect, e.getMessage());
		}
		
		return view;
	}
	
	@RequestMapping(value = "previous", method = RequestMethod.POST)
	public ModelAndView apiPrevious() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
	
		Celula celula = celulaService.getRepositorio().findOne(CelulaPredicate.buscarPorLider(getCurrentUser().getId()));
		Page<Relatorio> registros = service.getRepositorio().findAll(RelatorioPredicate.buscarPorUsuarioECelula(celula), RelatorioPredicate.buscarPaginacao(--marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	@RequestMapping(value = "next", method = RequestMethod.POST)
	public ModelAndView apiNext() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		Celula celula = celulaService.getRepositorio().findOne(CelulaPredicate.buscarPorLider(getCurrentUser().getId()));
		Page<Relatorio> registros = service.getRepositorio().findAll(RelatorioPredicate.buscarPorUsuarioECelula(celula), RelatorioPredicate.buscarPaginacao(++marker, QUANTIDADE_ELEMENTOS_POR_PAGINA));
		view.addObject("registros", registros.getContent());
		
		return view;
	}
	
	private ModelAndView carregarContexto(ModelAndView view) {
		List<Celula> celula = (List<Celula>) celulaService.getRepositorio().findAll(CelulaPredicate.buscarPorLider(getCurrentUser().getId()));
		
		List<Membro> membros = (List<Membro>) membroService.getRepositorio().findAll(MembroPredicate.buscarPor(celula.get(0)));
		membros.forEach(m -> m.setId(null));
		
		view.addObject("membros", membros);
		view.addObject("chamadas", TipoChamada.values());
		
		return view;
	}
}