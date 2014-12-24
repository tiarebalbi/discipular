package br.com.discipular.service.impl.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.discipular.repository.RelatorioRepository;
import br.com.discipular.service.impl.RelatorioServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class RelatorioServiceImplTest {

	@Mock
	private RelatorioRepository repository;
	
	private RelatorioServiceImpl service;
	
	@Before
	public void init() {
		service = new RelatorioServiceImpl();
		service.setRepositorio(repository);
	}
	
	@Test
	public void deveTestarGetRepositorio() {
		RelatorioRepository retorno = this.service.getRepositorio();
		assertEquals(repository, retorno);
	}
	
}
