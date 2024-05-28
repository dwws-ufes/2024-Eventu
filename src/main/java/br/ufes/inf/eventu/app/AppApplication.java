package br.ufes.inf.eventu.app;

import br.ufes.inf.eventu.app.domain.File;
import br.ufes.inf.eventu.app.domain.User;
import br.ufes.inf.eventu.app.domain.Attraction;
import br.ufes.inf.eventu.app.domain.AttractionUser;
import br.ufes.inf.eventu.app.domain.enums.AttachmentType;
import br.ufes.inf.eventu.app.domain.enums.UserRole;
import br.ufes.inf.eventu.app.persistence.FileDAO;
import br.ufes.inf.eventu.app.persistence.UserDAO;
import br.ufes.inf.eventu.app.persistence.AttractionDAO;
import br.ufes.inf.eventu.app.persistence.AttractionUserDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
@EnableAutoConfiguration
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(
		UserDAO repository, 
		AttractionDAO repositoryAttractionDAO,
		AttractionUserDAO repositoryAttractionUserDAO) {
		return (args) -> {
			createAdmin(repository, repositoryAttractionDAO, repositoryAttractionUserDAO);
		};
	}

	public static String MD5(String password) throws Exception{
		var messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(password.getBytes(), 0, password.length());
		return new BigInteger(1, messageDigest.digest()).toString(16);
	}

	public static void createAdmin(
		UserDAO repository, 
		AttractionDAO repositoryAttractionDAO,
		AttractionUserDAO repositoryAttractionUserDAO) throws Exception {
		
		var admin = new User();
		admin.setFullName("Admin");
		admin.setEmail("teste@gmail.com");
		admin.setRole(UserRole.ADMIN);
		admin.setDateBirth(LocalDate.now());
		admin.setInstitution("UFES");
		admin.setPassword(MD5("admin"));
		admin.setPasswordSalt("admin");
		admin = repository.save(admin);

		var attraction = createAttraction(repositoryAttractionDAO);

		var attractionUser = new AttractionUser();
		attractionUser.setAttraction(attraction);
		attractionUser.setUser(admin);
		repositoryAttractionUserDAO.save(attractionUser);
	}

	public static Attraction createAttraction(AttractionDAO repository) throws Exception {
		var attraction = new Attraction();
		attraction.setName("Teste");
		attraction.setDescription("Teste");
		attraction.setCreatedAt(LocalTime.now());
		return repository.save(attraction);
	}
}