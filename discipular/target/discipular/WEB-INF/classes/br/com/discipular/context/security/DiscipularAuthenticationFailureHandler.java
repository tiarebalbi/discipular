package br.com.discipular.context.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class DiscipularAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Autowired
	private Environment env;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		response.addHeader("error", exception.getMessage());
		request.setAttribute("error-msg", exception.getMessage());
		super.setDefaultFailureUrl(this.env.getRequiredProperty("security.default.url.failure"));
		
		super.setUseForward(true);
		
        // // Roda o super
        super.onAuthenticationFailure(request, response, exception);
		
	}

}
