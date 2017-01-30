package com.dodo;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@SpringBootApplication
@Configuration
@EnableEurekaClient
public class LocationApplication implements ApplicationRunner  {
	private static final String CAMEL_URL_MAPPING = "/Location/*";
	private static final String CAMEL_SERVLET_NAME = "CamelServlet";
	
	public static void main(String[] args) {
		SpringApplication.run(LocationApplication.class, args);
	}
	
	@Autowired
	void setEnvironment(Environment e){
		System.out.println("  #### env:"+e.getProperty("broker.url"));
	}

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		// TODO Auto-generated method stub
	}
	
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(), CAMEL_URL_MAPPING);
        registration.setName(CAMEL_SERVLET_NAME);
        return registration;
    }	
}
