package br.ufes.inf.eventu.app;

import br.ufes.inf.eventu.app.domain.User;
import br.ufes.inf.eventu.app.domain.Attraction;
import br.ufes.inf.eventu.app.domain.enums.UserRole;
import br.ufes.inf.eventu.app.persistence.UserDAO;
import br.ufes.inf.eventu.app.persistence.AttractionDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.StreamSupport;

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

			var user = repositoryUser.findByEmail("admin@gmail.com");
			System.out.println(user.getRegisteredAttractions().stream().findFirst().get().getDescription());
		};
	}

	public static void setUp(
			UserDAO repositoryUser,
			AttractionDAO repositoryAttractionDAO) throws Exception {

		var user = createUserAdmin(repositoryUser);
		var attraction = createAttraction(repositoryAttractionDAO);
	}

	public static User createUserAdmin(UserDAO repository) throws Exception {

		var user = repository.findAll()
				.stream()
				.filter(x -> x.getEmail().equals("admin@gmail.com"))
				.findFirst()
				.orElse(null);

		if(user != null) return user;

		var admin = new User();
		admin.setFullName("Admin");
		admin.setEmail("admin@gmail.com");
		admin.setRole(UserRole.ADMIN);
		admin.setDateBirth(LocalDate.now());
		admin.setInstitution("UFES");
		admin.setPassword(MD5("admin"));
		admin.setPasswordSalt("admin");
		return repository.save(admin);
	}

	public static Attraction createAttraction(AttractionDAO repository) throws Exception {
		var attraction = new Attraction();
		attraction.setName("Palestra IOT");
		attraction.setDescription("Palestra sobre IOT");
		attraction.setCreatedAt(LocalTime.now());
		repository.save(attraction);

		attraction = new Attraction();
		attraction.setName("Palestra aprendendo C#");
		attraction.setDescription("Palestra sobre C#");
		attraction.setCreatedAt(LocalTime.now());
		repository.save(attraction);

		attraction = new Attraction();
		attraction.setName("Palestra Java JPA");
		attraction.setDescription("Palestra sobre Java");
		attraction.setCreatedAt(LocalTime.now());
		return repository.save(attraction);
	}


	public static String MD5(String password) throws Exception{
		var messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(password.getBytes(), 0, password.length());
		return new BigInteger(1, messageDigest.digest()).toString(16);
	}
}