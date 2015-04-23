//package br.com.discipular.service.impl.test;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
//import static org.mockito.Mockito.when;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import br.com.discipular.domain.model.Usuario;
//import br.com.discipular.domain.predicate.UsuarioPredicate;
//import br.com.discipular.domain.repository.UsuarioRepository;
//import br.com.discipular.service.impl.UsuarioServiceImpl;
//
//@RunWith(MockitoJUnitRunner.class)
//public class UsuarioServiceImplTest {
//
//	@Mock
//	private UsuarioRepository repository;
//	
//	private UsuarioServiceImpl service;
//	
//	private Usuario usuario;
//	
//	@Before
//	public void init() {
//		service = new UsuarioServiceImpl();
//		service.setRepositorio(repository);
//	
//		usuario = new Usuario();
//		usuario.setId(1L);
//		usuario.setSenha("senha");
//		usuario.setLogin("login");
//		usuario.setArea(0);
//	}
//	
//	@Test
//	public void deveTestarGetReposoitorio() {
//		UsuarioRepository retorno = this.service.getRepositorio();
//		assertEquals(repository, retorno);
//	}
//	
//	
//	@Test
//	public void deveVerificarSeELancadaUmaExceptionQuandoTentaSalvarUmRegistroComAreaNula() {
//		try {
//			usuario.setArea(null);
//			this.service.salvar(usuario);
//		} catch (Exception e) {
//			assertEquals("Favor preencher o campo área.", e.getMessage());
//		}
//	}
//	
//	@Test(expected = Exception.class)
//	public void deveVerificarSeELancadaUmaExceptionQuandoUmRegistroESalvoComLoginJaExistente() throws Exception {
//		Usuario usuario2 = new Usuario();
//		usuario2.setId(2L);
//		
//		when(repository.count(UsuarioPredicate.buscarPorLogin(usuario.getLogin()))).thenReturn(1L);
//		when(repository.findOne(UsuarioPredicate.buscarPorLogin(usuario.getLogin()))).thenReturn(usuario2);
//		when(repository.save(usuario)).thenReturn(usuario);
//		
//		this.service.salvar(usuario);
//		
//		fail();
//	}
//	
//	@Test 
//	public void deveTestarEditarUmRegistroComValoresValidos() throws Exception {
//		when(repository.count(UsuarioPredicate.buscarPorLogin(usuario.getLogin()))).thenReturn(1L);
//		when(repository.findOne(UsuarioPredicate.buscarPorLogin(usuario.getLogin()))).thenReturn(usuario);
//		when(repository.save(usuario)).thenReturn(usuario);
//		
//		Usuario retorno = this.service.salvar(usuario);
//		
//		assertEquals(usuario, retorno);
//	}
//	
//	@Test
//	public void deveTestarSalvarUmNovoRegistroValido() throws Exception {
//		when(repository.count(UsuarioPredicate.buscarPorLogin(usuario.getLogin()))).thenReturn(0L);
//		when(repository.save(usuario)).thenReturn(usuario);
//		
//		Usuario retorno = this.service.salvar(usuario);
//		
//		assertEquals(usuario, retorno);
//	}
//	
//}
