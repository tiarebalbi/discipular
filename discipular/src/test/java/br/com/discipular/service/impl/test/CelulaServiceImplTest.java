package br.com.discipular.service.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import br.com.discipular.domain.model.Lider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.discipular.domain.model.Celula;
import br.com.discipular.domain.model.Usuario;
import br.com.discipular.domain.repository.CelulaRepository;
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
		celula.setLider(new Lider());
		celula.setSupervisor(new Usuario());
		celula.setApagada(false);
	}
	
	@Test
	public void deveTestarExcluirUmaCelula() throws Exception {
		when(this.repository.findOne(1L)).thenReturn(celula);
		when(this.repository.save(celula)).thenReturn(celula);
		
		service.excluir(1L);
		
		assertNull(celula.getLider());
		assertNull(celula.getSupervisor());
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
	}
	
	
}
