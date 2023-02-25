package com.example.Troca.Idiomas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TrocaDeIdiomasApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrocaDeIdiomasApplication.class, args);
	}

}
