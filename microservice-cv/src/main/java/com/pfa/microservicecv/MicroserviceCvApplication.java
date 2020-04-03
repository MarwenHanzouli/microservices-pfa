package com.pfa.microservicecv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients("com.pfa.microservicecv")
public class MicroserviceCvApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCvApplication.class, args);
	}

}
