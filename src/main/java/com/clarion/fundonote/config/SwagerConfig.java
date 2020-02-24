package com.clarion.fundonote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2//Loads all required beans defined in @see SpringSwaggerConfig
public class SwagerConfig {
	
	@Bean
	public Docket api() {  //Provides sensible defaults and convenience methods for configuration.             
	    return new Docket(DocumentationType.SWAGGER_2)          
	      .select()  //Initiates a builder for api selection                                     
	      .apis(RequestHandlerSelectors.basePackage("com.clarion.fundonote.controller"))                  
	      .build();
	}
}
