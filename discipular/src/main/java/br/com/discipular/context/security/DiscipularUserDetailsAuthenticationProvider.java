package br.com.discipular.context.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DiscipularUserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	
	@Autowired
	private DiscipularUserDetailsService userDetail;
	
	@Autowired
	private DiscipularMessage message;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		if(username == null) {
			throw new UsernameNotFoundException("Não foi possível identificar o seu usuário");
		}
		
		UserDetails user = this.userDetail.loadUserByUsername(username);
		if(user == null) {
			throw new BadCredentialsException("User " + username + " not found!");
		}
		
		DiscipularPasswordEncoder fitnessPasswordEncoder = new DiscipularPasswordEncoder();
		String origem = authentication.getCredentials().toString();
		if(!fitnessPasswordEncoder.matches(origem, user.getPassword())) {
			throw new BadCredentialsException("Senha Errada");
		}
		
		return user;
	}

}
