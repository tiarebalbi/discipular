package br.com.discipular.context;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * Configuração da parte WEB da sistema
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	08/09/2014 
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="br.com.discipular.controller")
@PropertySource({"classpath:application.properties", "classpath:i18n/messages_pt_BR.properties"})
public class WebApplicationContext extends WebMvcConfigurerAdapter {

	private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/views/";
	private static final String VIEW_RESOLVER_SUFFIX = ".jsp";

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Implementar
		// 16.16.6 Configuring Serving of Resources
		// http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	/**
	 * Definições do Apache Tiles
	 * 
	 * @return {@link TilesConfigurer}
	 */
	@Bean
	public TilesConfigurer configuracaoTiles() {
		TilesConfigurer config = new TilesConfigurer();
		String[] definicoes = new String[2];
		definicoes[0] = "/WEB-INF/views/web-tiles.xml";
		definicoes[1] = "/WEB-INF/views/**/discipular-*.xml";

		config.setDefinitions(definicoes);
		return config;
	}
	
	@Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
        viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);
        viewResolver.setOrder(2);

        return viewResolver;
    }

	/**
	 * Configuração do viewResolver do Apache Tiles
	 * 
	 * @return {@link TilesViewResolver} 
	 */
	@Bean
	public TilesViewResolver tilesViewResolver() {
		TilesViewResolver view = new TilesViewResolver();
		view.setContentType("text/html");
		view.setOrder(1);
		return view;
	}

	@Override
	public void configureContentNegotiation( ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false)
				  .favorParameter(false)
				  .defaultContentType(MediaType.TEXT_HTML)
				  .useJaf(false)
				  .mediaType("html", MediaType.TEXT_HTML)
				  .mediaType("json", MediaType.APPLICATION_JSON);
	}
	
	/**
	 * Negociador do ViewResolver
	 * @param manager {@link ContentNegotiationManager}
	 * 
	 * @return {@link ContentNegotiatingViewResolver}
	 */
	@Bean
	public ContentNegotiatingViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();

		viewResolvers.add(this.tilesViewResolver());
		viewResolvers.add(this.viewResolver());
		viewResolvers.add(this.jsonViewResolver());

		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);
        resolver.setViewResolvers(viewResolvers);
        return resolver;
	}
	
	@Bean
	public JsonViewResolver jsonViewResolver() {
		return new JsonViewResolver();
	}
	
	/**
	 * Bean de configuração da internacionalização
	 * @return {@link MessageSource}
	 */
	@Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("i18n/messages_pt_BR", "i18n/messages_en_US");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
	
}
