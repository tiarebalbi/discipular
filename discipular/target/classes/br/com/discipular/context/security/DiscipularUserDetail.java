package br.com.discipular.context.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.discipular.model.Usuario;

public class DiscipularUserDetail implements UserDetails {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private Usuario usuario;
		
		/**
		 * Construtor
		 */
		public DiscipularUserDetail() {
			super();
		}
		
		/**
		 * @param usuario Entidade do usuário a ser logado
		 */
		public DiscipularUserDetail(Usuario usuario) {
			this.usuario = usuario;
		}
		

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			
			Collection<SimpleGrantedAuthority> listAuthorities = new ArrayList<>();
			listAuthorities.add(new SimpleGrantedAuthority(usuario.getTipo().getRegra()));
			return listAuthorities;
		}

		@Override
		public String getPassword() {
			return this.usuario.getSenha();
		}

		@Override
		public String getUsername() {
			return this.usuario.getLogin();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			//return this.usuario.getStatus();
			return true;
		}
		
		/**
		 * @return {@link Usuario}
		 */
		public Usuario getUsuario() {
			return this.usuario;
		}
		
		/**
		 * @param usuario {@link Usuario}
		 */
		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}
	
}
