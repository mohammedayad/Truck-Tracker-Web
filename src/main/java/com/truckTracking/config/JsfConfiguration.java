package com.truckTracking.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;

import com.google.common.collect.ImmutableMap;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@Configuration
public class JsfConfiguration implements ServletContextAware {

	/*
	 * Register the JSF servlet and configure it to load on startup (no need of
	 * web.xml) If working with JSF 2.2, your best is to use *.xhtml mapping, at
	 * least when using facelets
	 */

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new FacesServlet(), "*.xhtml");
		servletRegistrationBean.setLoadOnStartup(1);
		return servletRegistrationBean;
	}

	/*
	 * Make your configuration class implement ServletContextAware so that you can
	 * set your init parameters Here you must force JSF to load configuration
	 */
	@Override
	public void setServletContext(ServletContext servletContext) {
		servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
		servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");
		// More parameters...
	}

	/*
	 * register custom View scope in configuration class
	 */
	@Bean
	public static CustomScopeConfigurer viewScope() {
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		configurer.setScopes(new ImmutableMap.Builder<String, Object>().put("view", new ViewScope()).build());
		return configurer;
	}

}
