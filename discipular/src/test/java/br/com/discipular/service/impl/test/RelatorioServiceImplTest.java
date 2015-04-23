//package br.com.discipular.service.impl.test;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import br.com.discipular.domain.model.Relatorio;
//import br.com.discipular.domain.query.RelatorioQuery;
//import br.com.discipular.domain.repository.RelatorioRepository;
//import br.com.discipular.service.impl.RelatorioServiceImpl;
//
//@RunWith(MockitoJUnitRunner.class)
//public class RelatorioServiceImplTest {
//
//	@Mock
//	private RelatorioRepository repository;
//	
//	@Mock
//	private RelatorioQuery query;
//	
//	private RelatorioServiceImpl service;
//	
//	private Relatorio relatorio;
//	
//	@Before
//	public void init() {
//		service = new RelatorioServiceImpl();
//		service.setRepositorio(repository);
//		service.setQuery(query);
//		
//		relatorio = new Relatorio();
//	}
//	
//	@Test
//	public void deveTestarGetRepositorio() {
//		RelatorioRepository retorno = this.service.getRepositorio();
//		assertEquals(repository, retorno);
//	}
//	
//	@Test
//	public void deveTestarGetQuery() {
//		RelatorioQuery retorno = this.service.getQuery();
//		assertEquals(query, retorno);
//	}
//	
//	@Test
//	public void deveTestarSalvarUmRegistro() {
//		this.service.salvar(relatorio);
//		
//		assertNotNull(relatorio.getDataCriacao());
//	}
//	
//	@Test
//	public void deveTestarSalvarUmaListaRegistros() {
//		List<Relatorio> relatorios = new ArrayList<>();
//		
//		relatorios.add(relatorio);
//		relatorios.add(relatorio);
//		
//		this.service.salvar(relatorios);
//		
//		assertNotNull(relatorios.get(0).getDataCriacao());
//		assertNotNull(relatorios.get(1).getDataCriacao());
//	}
//	
//}
