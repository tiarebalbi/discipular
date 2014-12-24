package br.com.discipular.predicate.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Pageable;

import com.mysema.query.types.Predicate;

import br.com.discipular.model.Celula;
import br.com.discipular.model.Usuario;
import br.com.discipular.predicate.RelatorioPredicate;

public class RelatorioPredicateTest {

	private Usuario usuario;
	
	private Celula celula;
	
	@Before
	public void init() {
		usuario = new Usuario();
		usuario.setId(1L);
		usuario.setArea(1);
		
		celula = new Celula();
		celula.setUsuario(usuario);
	}
	
	@Test
	public void deveTestarBuscarPaginacao() {
		Pageable retorno = RelatorioPredicate.buscarPaginacao(1, 10);
		assertEquals("Page request [number: 1, size 10, sort: data: DESC]", retorno.toString());
	}
	
	@Test
	public void deveTestarBuscarPorCelula() {
		Predicate retorno = RelatorioPredicate.buscarPor("celula");
		assertEquals("startsWithIgnoreCase(relatorio.celula.nome,celula) || containsIc(relatorio.celula.nome,celula)", retorno.toString());
	}
	
	@Test
	public void deveTestarBuscarPorUsuario() {
		Predicate retorno = RelatorioPredicate.buscarPor(usuario);
		assertEquals("relatorio.usuario.id = 1", retorno.toString());
	}
	
	@Test
	public void deveTestarBuscarPorUsuarioECelula() {
		Predicate retorno = RelatorioPredicate.buscarPorUsuarioECelula(celula);
		assertEquals("relatorio.usuario.id = 1 && relatorio.celula = " + celula.toString(), retorno.toString());
	}
	
	@Test
	public void deveTestarBuscarPorPeriodoEUsuario() {
		LocalDate inicio = LocalDate.now();
		LocalDate fim = LocalDate.now().minusDays(10);
		Predicate retorno = RelatorioPredicate.buscarPorPeriodoE(usuario, inicio, fim);
		assertEquals("relatorio.usuario.id = 1 && relatorio.data between 2014-12-23 and 2014-12-13", retorno.toString());
	}
	
	@Test
	public void deveTestarBuscarPorSupervisor() {
		Predicate retorno = RelatorioPredicate.buscarPorSupervisor(usuario);
		assertEquals("relatorio.celula.area = 1", retorno.toString());
	}
	
	@Test
	public void deveTestarBuscarPorSupervisorECelula() {
		Predicate retorno = RelatorioPredicate.buscarPorSupervisorECelula("celula", usuario);
		assertEquals("relatorio.celula.area = 1 && (startsWithIgnoreCase(relatorio.celula.nome,celula) || containsIc(relatorio.celula.nome,celula))", retorno.toString());
	}
	
}
