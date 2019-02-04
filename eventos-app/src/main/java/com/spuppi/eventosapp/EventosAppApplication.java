package com.spuppi.eventosapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//public class EventosAppApplication{
public class EventosAppApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EventosAppApplication.class, args);
	}

}

