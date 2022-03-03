package com.ibm.academia.restapi.ipLocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class RestIpLocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestIpLocationApplication.class, args);
	}

}
