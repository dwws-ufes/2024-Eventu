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

public class Initialization {

    public static void setUp(
            UserDAO repositoryUser,
            AttractionDAO repositoryAttractionDAO) throws Exception {

        createUserAdmin(repositoryUser);
        //createAttraction(repositoryAttractionDAO);
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
}
