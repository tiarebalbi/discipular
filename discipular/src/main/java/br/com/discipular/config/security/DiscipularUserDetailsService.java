package br.com.discipular.config.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.discipular.domain.model.Usuario;
import br.com.discipular.domain.predicate.UsuarioPredicate;
import br.com.discipular.service.UsuarioService;

@Component
public class DiscipularUserDetailsService implements UserDetailsService  {

	@Autowired
	private UsuarioService service;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = this.service.getRepositorio().findOne(UsuarioPredicate.buscarPorLogin(username));
		if(usuario == null) {
			throw new UsernameNotFoundException("");
		}
		
		Collection<GrantedAuthority> permissoes = new ArrayList<>();
		SimpleGrantedAuthority permissao = new SimpleGrantedAuthority(usuario.getTipo().getRegra());
		permissoes.add(permissao);
		
		DiscipularUserDetails userDetail = new DiscipularUserDetails(username, usuario.getSenha(), permissoes);
		
		userDetail.setId(usuario.getId());
		userDetail.setNome(usuario.getNome());
		userDetail.setArea(usuario.getArea());
		userDetail.setLogin(usuario.getLogin());
		
		return userDetail;
		
	}

}
