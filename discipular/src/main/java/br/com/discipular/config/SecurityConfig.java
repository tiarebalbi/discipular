package br.com.discipular.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.discipular.config.security.DiscipularAuthenticationSuccessHandler;
import br.com.discipular.config.security.DiscipularUserDetailsService;

/**
 * Configuração do Spring Security
 *
 * @author Tiarê Balbi Bonamini
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DiscipularUserDetailsService userDetailsService;

    @Autowired
    private DiscipularAuthenticationSuccessHandler suporteAuthenticationSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/compartilhado/**").permitAll()
                .antMatchers("/membro").authenticated()
                .anyRequest().authenticated()
                .and()
	                .formLogin()
	                .successHandler(suporteAuthenticationSuccessHandler)
	                .loginProcessingUrl("/login/processing")
	                .loginPage("/login")
	                .passwordParameter("password")
	                .usernameParameter("username")
                .and()
	                .userDetailsService(userDetailsService)
	                .logout()
	                .logoutUrl("/logout")
	                .logoutSuccessUrl("/login?event=logout-success")
	                .deleteCookies("JSESSIONID")
	                .permitAll()
                .and()
	                .csrf().disable()
	                .headers()
	                .frameOptions()
	                .disable()
	                .exceptionHandling()
	                .accessDeniedPage("/login?access-denied")
                .and()
	                .sessionManagement()
	                .maximumSessions(1)
	                .expiredUrl("/?expired")
        ;
    }

}
