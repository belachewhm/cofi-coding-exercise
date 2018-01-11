package io.github.belachewhm.cofi.coding.exercise.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@Configuration
@EnableSwagger2
public class Swagger2Config
{
	@Autowired
	Environment environment;
	
	@Bean
	public Docket api()
	{
		log.info("Creating Swagger2 Docket Bean...");
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("io.github.belachewhm.cofi.coding.exercise.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo()
	{
		return new ApiInfo(
			"COFI Coding Exercise",
			environment.getProperty("project.description"),
			environment.getProperty("project.version"),
			"4",
			"Belachew Haile-Mariam",
			"http://belachewhm.github.io",
			"7");
	}
}
