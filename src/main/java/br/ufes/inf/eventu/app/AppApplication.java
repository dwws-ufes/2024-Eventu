package br.ufes.inf.eventu.app;

import br.ufes.inf.eventu.app.domain.File;
import br.ufes.inf.eventu.app.domain.User;
import br.ufes.inf.eventu.app.domain.enums.AttachmentType;
import br.ufes.inf.eventu.app.domain.enums.UserRole;
import br.ufes.inf.eventu.app.persistence.FileDAO;
import br.ufes.inf.eventu.app.persistence.UserDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.time.LocalDate;

@SpringBootApplication
@EnableAutoConfiguration
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserDAO repository) {
		return (args) -> {

		};
	}

	public static String MD5(String password) throws Exception{
		var messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(password.getBytes(), 0, password.length());
		return new BigInteger(1, messageDigest.digest()).toString(16);
	}
}