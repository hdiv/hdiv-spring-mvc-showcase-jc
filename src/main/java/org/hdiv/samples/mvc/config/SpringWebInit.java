package org.hdiv.samples.mvc.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.hdiv.filter.ValidatorFilter;
import org.hdiv.listener.InitListener;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringWebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { CoreConfig.class, SecurityConfig.class, HdivSecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringWebMvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	public void onStartup(ServletContext container) throws ServletException {

		super.onStartup(container);

		container.addFilter("ValidatorFilter", ValidatorFilter.class).addMappingForUrlPatterns(
				EnumSet.of(DispatcherType.REQUEST), false, "/*");

		container.addListener(new InitListener());

	}

}
