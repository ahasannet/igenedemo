package com.igene.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.igene.demo"})
public class IgeneApplication {

	public static void main(String[] args) {
		SpringApplication.run(IgeneApplication.class, args);
	}
}
