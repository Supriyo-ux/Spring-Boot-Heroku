package com.book.config;


import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Webconfiguration {
	
	@Bean
	ServletRegistrationBean  h2servletRegistration() {
		ServletRegistrationBean registrationBean  = new ServletRegistrationBean(new WebServlet()); 
		registrationBean.addUrlMappings("/h2DatabaseView/*");
		return registrationBean;
		
	}
}