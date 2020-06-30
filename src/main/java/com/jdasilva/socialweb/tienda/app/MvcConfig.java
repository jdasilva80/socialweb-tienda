package com.jdasilva.socialweb.tienda.app;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Autowired
	@Qualifier("flashAttributesInterceptorCommon")
	HandlerInterceptor flashAttributesInterceptor;
	
	@Autowired
	LocaleChangeInterceptor localeChangeInterceptor;

	//private static final Logger logger = LoggerFactory.getLogger(MvcConfig.class);

	@Override
	/*
	 * se registra el interceptor
	 */
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(flashAttributesInterceptor).addPathPatterns("/**/tienda/**");
		registry.addInterceptor(localeChangeInterceptor).addPathPatterns("/**/tienda/**");

	}

}
