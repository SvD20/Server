package com.webservice.authors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;



@EnableDiscoveryClient//позволяет использовать класс службу обнаружения Eureka, так как другая не настроена
@SpringBootApplication
public class WebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebServiceApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}


}
