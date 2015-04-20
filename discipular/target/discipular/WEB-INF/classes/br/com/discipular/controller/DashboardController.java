package br.com.discipular.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.discipular.context.security.DiscipularPasswordEncoder;
import br.com.discipular.domain.dto.TemplateGraficoDTO;
import br.com.discipular.enumerator.TipoChamada;
import br.com.discipular.model.Relatorio;
import br.com.discipular.model.Usuario;
import br.com.discipular.predicate.ChamadaPredicate;
import br.com.discipular.predicate.RelatorioPredicate;
import br.com.discipular.repository.ChamadaRepository;
import br.com.discipular.service.RelatorioService;
import br.com.discipular.service.UsuarioService;
import br.com.discipular.utils.DataUtils;

@Controller
public class DashboardController extends AbstractController {
	
	private final static String VIEW_INDEX = "dashboard/index";
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RelatorioService relatorioService;
	
	@Autowired
	private ChamadaRepository chamadaRepository;
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		return view;
	}
	
	@RequestMapping(value = "/trocar-senha/{senha}/{confirm}")
	public ModelAndView trocarSenha(@PathVariable ("senha") String senha, @PathVariable ("confirm") String confirm) {
		ModelAndView view = new ModelAndView();
		Usuario usuario = getCurrentUser();
		try {
			if(senha.equals(confirm)) {
				usuario.setSenha(new DiscipularPasswordEncoder().encode(senha));
				usuarioService.salvar(usuario);
			}
		} catch (Exception e) {
			view.addObject("mensagem", e.getMessage());
		}
		
		return view;
	}

	@RequestMapping(value = "/grafico", method = RequestMethod.GET)
	public ModelAndView graficoFalta() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		
		TemplateGraficoDTO dto = new TemplateGraficoDTO(); 
		List<Relatorio> relatorios = (List<Relatorio>) relatorioService.getRepositorio().findAll(RelatorioPredicate.buscarPorPeriodoE(getCurrentUser(), LocalDate.now().minusMonths(2), LocalDate.now()));
	
		for (Relatorio relatorio : relatorios) {
			long total = chamadaRepository.count(ChamadaPredicate.buscarPor(relatorio));
			long chamadas = chamadaRepository.count(ChamadaPredicate.buscarPorRelatorioEStatus(relatorio, TipoChamada.PRESENTE));
			long porcentagem = chamadas * 100 / total;
			
			dto.getData().add(porcentagem);
			dto.getLabel().add(DataUtils.formatDataPtBr(relatorio.getData()));
			
			view.addObject("dados", dto.getData());
			view.addObject("labels", dto.getLabel());
		}
		
		return view;
	}
	
}