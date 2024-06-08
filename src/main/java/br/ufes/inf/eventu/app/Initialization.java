package br.ufes.inf.eventu.app;

import br.ufes.inf.eventu.app.domain.Attraction;
import br.ufes.inf.eventu.app.domain.User;
import br.ufes.inf.eventu.app.domain.enums.UserRole;
import br.ufes.inf.eventu.app.persistence.AttractionDAO;
import br.ufes.inf.eventu.app.persistence.UserDAO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Initialization {

    public static void setUp(
            UserDAO repositoryUser,
            AttractionDAO repositoryAttractionDAO) throws Exception {

        createUserAdmin(repositoryUser);
        createAttraction(repositoryAttractionDAO);
    }

    public static void createUserAdmin(UserDAO repository) throws Exception {

        var emails = new ArrayList<String>();
        emails.add("admin@gmail.com");
        emails.add("user@gmail.com");

        var users = repository.findAll()
                .stream()
                .filter(x -> emails.contains(x.getEmail()))
                .toList();

        if(users.size() == 2) return;

        var user = new User();
        user.setFullName("Admin");
        user.setEmail("admin@gmail.com");
        user.setRole(UserRole.ADMIN);
        user.setDateBirth(LocalDate.now());
        user.setInstitution("UFES");
        user.setPassword(new BCryptPasswordEncoder().encode("admin"));
        user.setPasswordSalt("admin");
        repository.save(user);

        user = new User();
        user.setFullName("User");
        user.setEmail("user@gmail.com");
        user.setRole(UserRole.USER);
        user.setDateBirth(LocalDate.now());
        user.setInstitution("UFES");
        user.setPassword(new BCryptPasswordEncoder().encode("user"));
        user.setPasswordSalt("user");
        repository.save(user);
    }

    public static void createAttraction(AttractionDAO repository) throws Exception {

        var attractionNames = Arrays.asList(
                "Palestra IOT",
                "Workshop de Machine Learning",
                "Introdução ao Desenvolvimento Web",
                "Hackathon de Segurança Cibernética",
                "Palestra sobre Blockchain",
                "Maratona de Programação",
                "Workshop de Desenvolvimento Mobile",
                "Palestra sobre Inteligência Artificial",
                "Seminário de DevOps",
                "Curso de Programação Funcional",
                "Introdução ao Big Data",
                "Oficina de Realidade Aumentada",
                "Curso de Programação em Python",
                "Palestra sobre Computação Quântica",
                "Workshop de Design UX/UI",
                "Maratona de Robótica",
                "Seminário de Cloud Computing",
                "Palestra sobre Ética na IA",
                "Hackathon de Jogos Digitais",
                "Oficina de Testes Automatizados"
        );

        if(repository.findAll().stream().anyMatch(x -> attractionNames.contains(x.getName()))) return;

        for (var item : attractionNames){
            var attractionModel  = new Attraction();
            attractionModel.setName(item);
            attractionModel.setDescription(item);
            attractionModel.setCreatedAt(LocalTime.now());
            repository.save(attractionModel);
        }
    }
}
