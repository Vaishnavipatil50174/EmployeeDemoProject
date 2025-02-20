package com.example.employeedemo;

import lombok.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class EmployeedemoApplication implements CommandLineRunner {

	public static void main(String[] args) {

		ApplicationContext context=SpringApplication.run(EmployeedemoApplication.class, args);

		Application app=context.getBean(Application.class);
		app.name();

	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println("ths is in command line runner");

	}
}
