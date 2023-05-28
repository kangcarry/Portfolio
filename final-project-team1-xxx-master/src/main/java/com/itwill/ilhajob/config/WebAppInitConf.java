package com.itwill.ilhajob.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/*
 * web.xml설정을 대신할 클래스
 * -  톰캣실행시 기본설정
 */
@Configuration
@ComponentScan("com.itwill.ilhajob")
public class WebAppInitConf implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext context = 
				new AnnotationConfigWebApplicationContext();
		context.register(WebConfig.class);
		ServletRegistration.Dynamic dynamic=
				servletContext.addServlet("dispatcherSevlet", new DispatcherServlet(context));
		dynamic.setLoadOnStartup(0);
		dynamic.addMapping("/");
		
	}
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
	
}
