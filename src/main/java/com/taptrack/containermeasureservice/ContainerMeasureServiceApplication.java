package com.taptrack.containermeasureservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ContainerMeasureServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContainerMeasureServiceApplication.class, args);
	}

}
