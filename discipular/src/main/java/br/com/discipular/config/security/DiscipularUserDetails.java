package br.com.discipular.config.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class DiscipularUserDetails extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;
	
	private String login;
	
	private int area;
	
	public DiscipularUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
	}

	public DiscipularUserDetails(String username, String senha, Collection<GrantedAuthority> permissoes) {
		super(username, senha, permissoes);
	}

	public boolean equals(Object o) {
		if (this == o){
			return true;
		}
		
		if(!(o instanceof DiscipularUserDetails)) {
			return false;
		}
		
		if(!super.equals(o)) {
			return false;
		}
		
		DiscipularUserDetails that = (DiscipularUserDetails) o;
		
		if(that.getId().equals(this.getId())) {
			return true;
		}
		
		return false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}
	
}
