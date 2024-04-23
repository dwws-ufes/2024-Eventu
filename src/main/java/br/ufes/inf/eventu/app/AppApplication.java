package br.ufes.inf.eventu.app;

import br.ufes.inf.eventu.app.domain.File;
import br.ufes.inf.eventu.app.domain.enums.AttachmentType;
import br.ufes.inf.eventu.app.persistence.FileDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;

@SpringBootApplication
@EnableAutoConfiguration
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(FileDAO repository) {
		return (args) -> {
			// save a few customers
			var file = new File();
			file.setName("testfile");
			file.setPath("/test/test");
			file.setAttachmentType(AttachmentType.IMAGE);
			file.setMimetype(MediaType.APPLICATION_JSON_VALUE);
			repository.save(file);

		};
	}
}