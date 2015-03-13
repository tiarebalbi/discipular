package br.com.discipular.context.security;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;

import br.com.discipular.model.Usuario;
import br.com.discipular.predicate.UsuarioPredicate;
import br.com.discipular.service.UsuarioService;

@Service
public class DiscipularUserDetailsService implements UserDetailsService {
	
	@Autowired
    private MessageSource messageSource;
	
	@Autowired(required=false)
	private LocaleResolver localeResolver;
	
	@Autowired(required=false)
    private HttpServletRequest request;

	@Autowired
	private UsuarioService service;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = this.service.getRepositorio().findOne(UsuarioPredicate.buscarPorLogin(username));
		if(usuario == null) {
			String msg = getMessage("validation.auth.username.notfound", "Não foi possível identificar nenhum usuário com o login '{0}'", username);
			throw new UsernameNotFoundException(msg);
		}

		DiscipularUserDetail userDetail = new DiscipularUserDetail(usuario);
		
		if(!userDetail.isEnabled()) {
			throw new BadCredentialsException(getMessage("validation.auth.account.disabled", "Sua conta de acesso esta inativa, entre em contato com o setor administrativo"));
		}
		
		if(!userDetail.isAccountNonLocked()) {
			throw new BadCredentialsException(getMessage("validation.auth.account.analyzing", "Sua conta esta em processo de análise, para maiores informações entre em contato com o setor administrativo"));
		}
		
		return userDetail;
	}

	/**
	 * @param code chave do {@link MessageSource}
	 * @param defaultMessage Mensagem padrão a ser exibida caso não identifique a chave
	 * @param params parametros da mensagem
	 * @return String
	 */
	public String getMessage(String code, String defaultMessage, Object... params) {
		Locale requestLocale = Locale.getDefault();
		
		if(localeResolver != null) {
			requestLocale = localeResolver.resolveLocale(request);
		}
        return messageSource.getMessage(code, params, defaultMessage, requestLocale);
	}

}
