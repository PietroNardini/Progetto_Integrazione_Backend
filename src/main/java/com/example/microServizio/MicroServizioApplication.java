package com.example.microServizio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication //indica l'applicazione Spring Boot principale
public class MicroServizioApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroServizioApplication.class, args);
		/*Attiva l'applicazione Springboot
		 e il server embedded che permette all'applicazione di rimanere in ascolto su http://localhost:8080 */
	}
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		/*Questo metodo stampa un avviso quando il server si avvia */
		return args -> {
			System.out.println("Server is listening...");
		};
	}
}
