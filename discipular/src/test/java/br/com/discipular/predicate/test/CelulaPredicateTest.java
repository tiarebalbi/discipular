package br.com.discipular.predicate.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Pageable;

import br.com.discipular.model.Usuario;
import br.com.discipular.predicate.CelulaPredicate;

import com.mysema.query.types.Predicate;

public class CelulaPredicateTest {

	private Usuario usuario;
	
	@Before
	public void init() {
		usuario = new Usuario();
		usuario.setId(1L);
	}
	
	@Test
	public void deveTestarBuscarPaginacao() {
		Pageable retorno = CelulaPredicate.buscarPaginacao(1, 10);
		assertEquals("Page request [number: 1, size 10, sort: nome: ASC]", retorno.toString());
	}
	
	@Test
	public void deveTestarBuscarPorNomeComFiltro() {
		Predicate retorno = CelulaPredicate.buscarPorNomeComFiltro("Fonte de Graça");
		assertEquals("(startsWithIgnoreCase(celula.nome,Fonte de Graça) || containsIc(celula.nome,Fonte de Graça)) && celula.apagada = false", retorno.toString());
	}
	
	@Test
	public void deveTestarBuscarPorNome() {
		Predicate retorno = CelulaPredicate.buscarPorNome("Fonte de Graça");
		assertEquals("celula.nome = Fonte de Graça", retorno.toString());
	}
	
	@Test
	public void deveTestarBuscarPorSupervisor() {
		Predicate retorno = CelulaPredicate.buscarPorSupervisor(usuario);
		assertEquals("celula.supervisor.id = 1", retorno.toString());
	}
	
	@Test
	public void deveTestarBuscarPorCelulaAtiva() {
		Predicate retorno = CelulaPredicate.buscarPorCelulaAtiva();
		assertEquals("celula.apagada = false", retorno.toString());
	}
	
	@Test
	public void deveTestarBuscarPorLider() {
		Predicate retorno = CelulaPredicate.buscarPorLider(usuario);
		assertEquals("celula.usuario.id = 1", retorno.toString());
	}
	
	@Test
	public void deveTestarBuscarPorCelulaAtivaEArea() {
		Predicate retorno = CelulaPredicate.buscarPorCelulaAtivaEArea(1);
		assertEquals("celula.apagada = false && celula.area = 1", retorno.toString());
	}
	
	@Test
	public void deveTestarBuscarPorNomeComFiltroCelulaAtivaEArea() {
		Predicate retorno = CelulaPredicate.buscarPorNomeComFiltroCelulaAtivaEArea("Fonte de Graça", usuario);
		assertEquals("celula.apagada = false && celula.area = 0 && (startsWithIgnoreCase(celula.nome,Fonte de Graça) || containsIc(celula.nome,Fonte de Graça))", retorno.toString());
	}
	
}
