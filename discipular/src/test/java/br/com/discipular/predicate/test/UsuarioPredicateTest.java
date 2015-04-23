//package br.com.discipular.predicate.test;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.data.domain.PageRequest;
//
//import br.com.discipular.domain.enumetator.TipoUsuario;
//import br.com.discipular.domain.model.Usuario;
//import br.com.discipular.domain.predicate.UsuarioPredicate;
//
//import com.mysema.query.types.Predicate;
//
//public class UsuarioPredicateTest {
//	
//	private Usuario usuario;
//	
//	@Before
//	public void init() {
//		usuario = new Usuario();
//		usuario.setId(1L);
//		usuario.setTipo(TipoUsuario.LIDER);
//		usuario.setArea(0);
//	}
//	
//	@Test
//	public void deveTestarBuscarPaginacao() {
//		PageRequest retorno = UsuarioPredicate.buscarPaginacao(1, 10);
//		assertEquals("Page request [number: 1, size 10, sort: area: ASC]", retorno.toString());
//	}
//	
//	@Test
//	public void deveTestarBuscarPorNomeComFiltro() {
//		Predicate retorno = UsuarioPredicate.buscarPorNomeComFiltro("Usuario", TipoUsuario.LIDER);
//		assertEquals("(startsWithIgnoreCase(usuario.nome,Usuario) || containsIc(usuario.nome,Usuario)) && usuario.tipo = LIDER", retorno.toString());
//	}
//	
//	@Test
//	public void deveTestarBuscarPorLogin() {
//		Predicate retorno = UsuarioPredicate.buscarPorLogin("login");
//		assertEquals("usuario.login = login", retorno.toString());
//	}
//	
//	@Test
//	public void deveTestarBuscarTipo() {
//		Predicate retorno = UsuarioPredicate.buscarTipo(TipoUsuario.SUPERVISOR);
//		assertEquals("usuario.tipo = SUPERVISOR", retorno.toString());
//	}
//	
//	@Test
//	public void deveTestarBuscarLiderSemCelulaDiferente() {
//		Predicate retorno = UsuarioPredicate.buscarLiderSemCelulaDiferente(usuario);
//		assertEquals("usuario.tipo = LIDER && usuario.id != 1 && empty(usuario.celulas)", retorno.toString());
//	}
//	
//	@Test
//	public void deveTestarBuscarPorTipoSemCelula() {
//		Predicate retorno = UsuarioPredicate.buscarPorTipoSemCelula(TipoUsuario.ADMINISTRADOR);
//		assertEquals("usuario.tipo = ADMINISTRADOR && empty(usuario.celulas)", retorno.toString());
//	}
//	
//	@Test
//	public void deveTestarBuscarTipoEUsuario() {
//		Predicate retorno = UsuarioPredicate.buscarTipoEArea(TipoUsuario.LIDER, usuario);
//		assertEquals("usuario.tipo = LIDER && usuario.area = 0", retorno.toString());
//	}
//	
//	@Test
//	public void deveTestarBuscarPorNomeComFiltroTipoEArea() {
//		Predicate retorno = UsuarioPredicate.buscarPorNomeComFiltroTipoEArea("usuario", TipoUsuario.SUPERVISOR, usuario);
//		assertEquals("usuario.tipo = SUPERVISOR && usuario.area = 0 && startsWithIgnoreCase(usuario.nome,usuario) && containsIc(usuario.nome,usuario)", retorno.toString());
//	}
//	
//	@Test
//	public void deveTestarBuscarTipoEArea() {
//		Predicate retorno = UsuarioPredicate.buscarTipoEArea(TipoUsuario.ADMINISTRADOR, 1);
//		assertEquals("usuario.tipo = ADMINISTRADOR && usuario.area = 1", retorno.toString());
//	}
//	
//}
