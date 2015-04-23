//package br.com.discipular.predicate.test;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import br.com.discipular.domain.enumetator.TipoChamada;
//import br.com.discipular.domain.model.Relatorio;
//import br.com.discipular.domain.predicate.ChamadaPredicate;
//
//import com.mysema.query.types.Predicate;
//
//public class ChamadaPredicateTest {
//
//	private Relatorio relatorio;
//	
//	@Before
//	public void init() {
//		relatorio = new Relatorio(); 
//	}
//	
//	@Test
//	public void deveTestarBuscarPorRelatorio() {
//		Predicate retorno = ChamadaPredicate.buscarPor(relatorio);
//		assertEquals("chamada.relatorio = " + relatorio.toString(), retorno.toString());
//	}
//	
//	@Test
//	public void deveTestarBuscarPorRelatorioEStatus() {
//		Predicate retorno = ChamadaPredicate.buscarPorRelatorioEStatus(relatorio, TipoChamada.PRESENTE);
//		assertEquals("chamada.relatorio = " + relatorio.toString() + " && chamada.tipo = PRESENTE", retorno.toString());
//	}
//	
//}
