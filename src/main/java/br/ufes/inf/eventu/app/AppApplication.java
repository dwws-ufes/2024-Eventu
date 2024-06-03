package br.ufes.inf.eventu.app;

import br.ufes.inf.eventu.app.persistence.UserDAO;
import br.ufes.inf.eventu.app.persistence.AttractionDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static br.ufes.inf.eventu.app.Initialization.setUp;

@SpringBootApplication
@EnableAutoConfiguration
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(
		UserDAO repositoryUser,
		AttractionDAO repositoryAttractionDAO) {
		return (args) -> {
			setUp(repositoryUser, repositoryAttractionDAO);
		};
	}
}