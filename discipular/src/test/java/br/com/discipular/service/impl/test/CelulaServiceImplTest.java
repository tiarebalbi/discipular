package br.com.discipular.service.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.discipular.model.Celula;
import br.com.discipular.model.Usuario;
import br.com.discipular.repository.CelulaRepository;
import br.com.discipular.service.impl.CelulaServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CelulaServiceImplTest {

	@Mock
	private CelulaRepository repository;
	
	private CelulaServiceImpl service;
	
	private Celula celula;
	
	@Before
	public void init() {
		service = new CelulaServiceImpl();
		service.setRepositorio(repository);
		
		celula = new Celula();
		celula.setUsuario(new Usuario());
		celula.setSupervisor(new Usuario());
		celula.setApagada(false);
	}
	
	@Test
	public void deveTestarExcluirUmaCelula() throws Exception {
		when(this.repository.findOne(1L)).thenReturn(celula);
		when(this.repository.save(celula)).thenReturn(celula);
		
		service.excluir(1L);
		
		assertNull(celula.getUsuario());
		assertNull(celula.getSupervisor());
		assertTrue(celula.getApagada());
	}
	
	@Test
	public void deveTestarGetRepositorio() {
		CelulaRepository retorno = this.service.getRepositorio();
		assertEquals(repository, retorno);
	}
	
	@Test
	public void deveTestarSalvarUmaCelulaComUmSupervisor() throws Exception {
		celula.getSupervisor().setArea(1);
		when(this.repository.save(celula)).thenReturn(celula);
		
		Celula retorno = this.service.salvar(celula);
		
		assertNotNull(retorno);
		assertEquals(retorno.getArea(), celula.getSupervisor().getArea());
	}
	
	
}
