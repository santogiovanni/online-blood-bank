package com.example.hospital.entities;

import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class HospitalApp {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApp.class, args);
	}

}
