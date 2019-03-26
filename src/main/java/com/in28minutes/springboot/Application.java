package com.in28minutes.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.*;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@ComponentScan("com.in28minutes.springboot")
public class Application {

	public static final Contact DEFAULT_CONTACT = new Contact(
			"sudhir", 
			"https://www.facebook.com/kushwahasudhir", 
			"sudhirkk007@hotmail.com");
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
			  "Awesome API Title", 
			  "Awesome API Documentation", "1.0", 
			  "urn:tos", DEFAULT_CONTACT, 
			  "Apache 2.0", 
			  "http://www.apache.org/licenses/LICENSE-2.0");
	public static final Set<String> DEFAULT_PRODUCE_AND_CONSUME=new HashSet<>(Arrays.asList("application/json","application/xml"));

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

	}
	@Bean
    public Docket restApi() {
		//select().paths(regex("/users.*")).build();
        return new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(DEFAULT_API_INFO)
        		.produces(DEFAULT_PRODUCE_AND_CONSUME)
        		.consumes(DEFAULT_PRODUCE_AND_CONSUME)
        		.select().paths(regex("/jpa/users.*")).build();
                
    }
	
	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver=new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	
/*	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource bundleMessageSource=new ResourceBundleMessageSource();
		bundleMessageSource.setBasename("message");
		return bundleMessageSource;
	}*/

}

