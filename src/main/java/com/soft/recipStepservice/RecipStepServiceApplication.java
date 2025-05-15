package com.soft.recipStepservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RecipStepServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipStepServiceApplication.class, args);
	}

}
