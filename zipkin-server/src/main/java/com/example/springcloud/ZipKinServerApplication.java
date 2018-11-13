package com.example.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * @author Yu Mengyao
 */
@EnableZipkinServer
@EnableEurekaClient
@SpringBootApplication
public class ZipKinServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipKinServerApplication.class, args);
	}
}
