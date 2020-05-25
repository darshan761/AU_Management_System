package com.accolite.aums.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@ComponentScan("com.accolite.aums.*")
public class AumsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AumsApplication.class, args);
	}

}
