package com.elk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootElkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootElkApplication.class, args);		
	}

}
