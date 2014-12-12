package br.com.discipular.service.impl.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.discipular.model.Celula;
import br.com.discipular.model.Membro;
import br.com.discipular.predicate.MembroPredicate;
import br.com.discipular.repository.MembroRepository;
import br.com.discipular.service.impl.MembroServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class MembroServiceImplTest {
	
	private MembroServiceImpl serviceImpl;
	
	@Mock
	private MembroRepository repository;
	
	private Membro membro;
	
	@Before
	public void init() {
		serviceImpl = new MembroServiceImpl();
		serviceImpl.setRepository(repository);
		
		membro = new Membro();
		Celula celula = new Celula();
		celula.setId(1l);
		membro.setCelula(celula);
	}
	
	@Test
	public void deveTestarSeOMetodoIsFullRetornaTrueQuandoACelulaEstaLotada() {
		when(repository.count(MembroPredicate.buscarPor(membro.getCelula()))).thenReturn(14l);
		
		boolean resultado = serviceImpl.isFull(membro);
		
		assertTrue(resultado);
	}
	
	@Test
	public void deveTestarSeOMetodoIsFullRetornaFalseQuandoACelulaAindaTemVaga() {
		when(repository.count(MembroPredicate.buscarPor(membro.getCelula()))).thenReturn(13l);
		
		boolean resultado = serviceImpl.isFull(membro);
		
		assertFalse(resultado);
	}
	
}
