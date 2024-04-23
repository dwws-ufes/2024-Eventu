package br.ufes.inf.eventu.app;

import br.ufes.inf.eventu.app.domain.File;
import br.ufes.inf.eventu.app.domain.enums.AttachmentType;
import br.ufes.inf.eventu.app.persistence.FileDAO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;

@SpringBootTest
class AppApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void saveFile(FileDAO repository) {


	}

}
