package com.fpoly;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.fpoly.config.StorageProperties;
import com.fpoly.service.StorageService;



@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableConfigurationProperties(StorageProperties.class)
public class ClothesUd15Application {

	public static void main(String[] args) {
		SpringApplication.run(ClothesUd15Application.class, args);
	}
	
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args ->{
			storageService.init();
		});
	}
}
