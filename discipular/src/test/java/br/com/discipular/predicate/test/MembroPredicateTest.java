//package br.com.discipular.predicate.test;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.Test;
//import org.springframework.data.domain.PageRequest;
//
//import br.com.discipular.domain.model.Celula;
//import br.com.discipular.domain.predicate.MembroPredicate;
//
//import com.mysema.query.types.Predicate;
//
//public class MembroPredicateTest {
//
//	@Test
//	public void deveTestarBuscarPaginacao() {
//		PageRequest retorno = MembroPredicate.buscarPaginacao(1, 10);
//		assertEquals("Page request [number: 1, size 10, sort: nome: ASC,dataNascimento: ASC]", retorno.toString());
//	}
//	
//	@Test
//	public void deveTestarBuscarPorCelulaComFiltro() {
//		Predicate retorno = MembroPredicate.buscarPorCelulaComFiltro("Lucas");
//		assertEquals("startsWithIgnoreCase(membro.celula.nome,Lucas) || containsIc(membro.celula.nome,Lucas)", retorno.toString());
//	}
//	
//	@Test
//	public void deveTestarBuscarPorNome() {
//		Predicate retorno = MembroPredicate.buscarPorNome("Lucas");
//		assertEquals("membro.nome = Lucas", retorno.toString());
//	}
//	
//	@Test
//	public void deveTestarBuscarPorCelula() {
//		Celula celula = new Celula();
//		celula.setId(1l);
//		Predicate retorno = MembroPredicate.buscarPor(celula);
//		assertEquals("membro.celula.id = 1", retorno.toString());
//	}
//	
//	@Test
//	public void deveTestarBuscarPorArea() {
//		Predicate retorno = MembroPredicate.buscarPorArea(1);
//		assertEquals("membro.celula.area = 1", retorno.toString());
//	}
//	
//	@Test
//	public void deveTestarBuscarPorAreaECelulaFiltro() {
//		Predicate retorno = MembroPredicate.buscarPorAreaECelulaFiltro("Lucas", 1);
//		assertEquals("(startsWithIgnoreCase(membro.celula.nome,Lucas) || containsIc(membro.celula.nome,Lucas)) && membro.celula.area = 1", retorno.toString());
//	}
//	
//}
