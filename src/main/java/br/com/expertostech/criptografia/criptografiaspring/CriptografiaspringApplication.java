package br.com.expertostech.criptografia.criptografiaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CriptografiaspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CriptografiaspringApplication.class, args);
	}

}
