package br.com.discipular.context;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.discipular.context.security.DiscipularUserDetailsAuthenticationProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true, jsr250Enabled=true, securedEnabled=true)
@ComponentScan(basePackages={"br.com.discipular.context.security", "br.com.discipular.service"})
public class SecurityApplicationContext extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private DiscipularUserDetailsAuthenticationProvider provider;

	@Autowired
	private AuthenticationFailureHandler failHandler;
	
	@Bean
	public SecurityConfig securityConfigBean() {
		SecurityConfig config = new SecurityConfig("discipular-security");
		return config;
	}
	
	/**
	 * Método para configuração global da autenticação do sistema.
	 * 
	 * @param auth {@link AuthenticationManagerBuilder}
	 * @throws Exception {@link Exception} 
	 */
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(provider);
    }
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return new ProviderManager(authProviders());
	}
	
	private List<AuthenticationProvider> authProviders() {
		ArrayList<AuthenticationProvider> providers = new ArrayList<>();
		provider.setMessageSource(this.messageSource);
		providers.add(provider);
		return providers;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.csrf().disable()
	        .authorizeRequests()
	        	.antMatchers("/resources/**", "/login/install").permitAll()    
	            .anyRequest().authenticated()
	            .and()
	        .formLogin()
	            .loginPage("/login") 
	            .usernameParameter("discipular_username")
	            .passwordParameter("discipular_password")
	            .loginProcessingUrl("/logar")
	            .defaultSuccessUrl("/")
	            .failureUrl("/login?error")
	            .failureHandler(failHandler)
	            .permitAll()
	            .and()
	        .logout()
	        	.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
	        	.and()
	        .rememberMe()
	        	.key("discipular-remember")
	        	.and()
	        .sessionManagement()
	        	.maximumSessions(this.env.getRequiredProperty("security.session.max", Integer.class))
	        	.expiredUrl("/login?expired");
	}
}