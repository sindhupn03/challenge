package com.lowes.interview.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 Create a Spring boot application as a REST service with following
  endpoint
GET /coding/exercise/quizUse the following 2 services as downstream
 services (data source) for fetching response and simplifying 
 to a single response for above endpoint
https://opentdb.com/api.php?amount=5&category=11
https://opentdb.com/api.php?amount=5&category=12
Create POJO 
for the response from data sources and transform the response 
format of JSON to following expected format to respond back for 
REST endpoint (Attached)



Tech Stack:

Spring Boot, Java/Kotlin



Embedded Servers:

Tomcat/Netty



Expectations:

SOLID principles (How it is applied on Code) - 
MandatoryConcurrent programming (Asynchronous) - 
MandatoryResponse transformation to abide the contract - 
MandatoryDomain driven design package structure - OptionalReactive programming - Optional



NOTE:- Any dependencies can be added, but the dependency should be 
simplifying the structure instead of having overhead in configurations
  STaus Code -200 Ok Just give one Reponse 
  
 */
@EnableSwagger2
@SpringBootApplication
public class SampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.lowes.interview.sample.controller")).build();
	}
}
