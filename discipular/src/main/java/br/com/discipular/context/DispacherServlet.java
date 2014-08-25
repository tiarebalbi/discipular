package br.com.discipular.context;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispacherServlet extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { ApplicationContext.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebApplicationContext.class};    
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setInitParameter("dispatchOptionsRequest", "true");
        registration.setAsyncSupported(true);
        registration.addMapping("/");
	}
	
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

        FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter());
        characterEncodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        characterEncodingFilter.setInitParameter("encoding", "UTF-8");
        
		super.onStartup(servletContext);
	}	
	
}
