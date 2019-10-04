package com.toad.booksystemserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BookSystemServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookSystemServiceRegistryApplication.class, args);
	}

}
