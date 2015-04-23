//package br.com.discipular.service.impl.test;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
//import static org.mockito.Mockito.when;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import br.com.discipular.domain.model.Celula;
//import br.com.discipular.domain.model.Membro;
//import br.com.discipular.domain.predicate.MembroPredicate;
//import br.com.discipular.domain.repository.MembroRepository;
//import br.com.discipular.service.impl.MembroServiceImpl;
//
//@RunWith(MockitoJUnitRunner.class)
//public class MembroServiceImplTest {
//	
//	@Mock
//	private MembroRepository repository;
//	
//	private MembroServiceImpl serviceImpl;
//	
//	private Membro membro;
//	
//	@Before
//	public void init() {
//		serviceImpl = new MembroServiceImpl();
//		serviceImpl.setRepository(repository);
//		
//		membro = new Membro();
//		membro.setNome("Lucas");
//		Celula celula = new Celula();
//		celula.setId(1l);
//		membro.setCelula(celula);
//	}
//	
//	@Test
//	public void deveTestarSeOMetodoIsFullRetornaTrueQuandoACelulaEstaLotada() {
//		when(repository.count(MembroPredicate.buscarPor(membro.getCelula()))).thenReturn(14l);
//		
//		boolean resultado = serviceImpl.isFull(membro);
//		
//		assertTrue(resultado);
//	}
//	
//	@Test
//	public void deveTestarSeOMetodoIsFullRetornaFalseQuandoACelulaAindaTemVaga() {
//		when(repository.count(MembroPredicate.buscarPor(membro.getCelula()))).thenReturn(13l);
//		
//		boolean resultado = serviceImpl.isFull(membro);
//		
//		assertFalse(resultado);
//	}
//	
//	@Test(expected = Exception.class)
//	public void deveTestarSalvarUmRegistroComACelulaCheia() throws Exception {
//		when(repository.count(MembroPredicate.buscarPor(membro.getCelula()))).thenReturn(14l);
//		
//		serviceImpl.salvar(membro);
//		
//		fail();
//	}
//	
//	@Test
//	public void deveTestarSalvarUmRegistroComACelulaValida() throws Exception {
//		when(repository.count(MembroPredicate.buscarPor(membro.getCelula()))).thenReturn(13l);
//		when(repository.save(membro)).thenReturn(membro);
//		
//		Membro retorno = serviceImpl.salvar(membro);
//		
//		assertEquals("Lucas", retorno.getNome());
//		assertNotNull(retorno);
//		
//	}
//	
//}
