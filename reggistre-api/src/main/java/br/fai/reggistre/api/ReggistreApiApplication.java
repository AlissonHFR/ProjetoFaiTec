package br.fai.reggistre.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.fai.reggistre.api", "br.fai.reggistre.db"})
public class ReggistreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReggistreApiApplication.class, args);
	}

}
