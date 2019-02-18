package com.spuppi.apirestfilmes.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Sullyvan Puppi
 * spuppi.com - spuppi@gmail.com
 * 15 de fev de 2019
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket productApi() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.spuppi.apirestfilmes"))
				.paths(regex("/.*"))
				.build()
				.apiInfo(metaInfo());		
	}
	
	private ApiInfo metaInfo() {
		
		ApiInfo apiInfo = new ApiInfo(
				"API Rest Filmes",
				"API Demonstrativa para cadastro e consulta de Filmes",
				"1.0",
				"Terms of Service",
				new Contact("Sullyvan Puppi","https://spuppi.com/","spuppi@gmail.com"),
				"Apache Licence Version 2.0",
				"https://www.apache.org/licence.html",
				new ArrayList<>()
				);
		return apiInfo;
	}

}
