package com.eventoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ComponentScan(basePackages = {"com.eventoapp.controllers"}) // Versões mais recentes precisam dessa tag para mostra ao main onde procurar certas Beans
public class EventoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventoappApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}
}
