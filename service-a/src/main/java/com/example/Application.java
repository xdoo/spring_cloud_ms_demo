package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;
import com.example.services.CustomerServiceAlternativeClient;

@SpringBootApplication
@EnableEurekaClient
@EnableSchemaRegistryClient
@EnableFeignClients(basePackageClasses = CustomerServiceAlternativeClient.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
