package br.ufes.inf.eventu.app;

import br.ufes.inf.eventu.app.domain.Attraction;
import br.ufes.inf.eventu.app.domain.AttractionType;
import br.ufes.inf.eventu.app.domain.Location;
import br.ufes.inf.eventu.app.domain.Speaker;
import br.ufes.inf.eventu.app.domain.User;
import br.ufes.inf.eventu.app.domain.enums.UserRole;
import br.ufes.inf.eventu.app.persistence.AttractionDAO;
import br.ufes.inf.eventu.app.persistence.AttractionTypeDAO;
import br.ufes.inf.eventu.app.persistence.LocationDAO;
import br.ufes.inf.eventu.app.persistence.SpeakerDAO;
import br.ufes.inf.eventu.app.persistence.UserDAO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Initialization {

    public static void setUp(
            UserDAO repositoryUser,
            AttractionDAO repositoryAttractionDAO,
            AttractionTypeDAO repositoryAttractionTypeDAO,
            SpeakerDAO speakerDAO,
            LocationDAO locationDAO ) throws Exception {

        createUserAdmin(repositoryUser);
        createAttraction(repositoryAttractionDAO, repositoryAttractionTypeDAO);
        createSpeaker(speakerDAO);
        createLocation(locationDAO);
    }

    public static void createUserAdmin(UserDAO repository) throws Exception {

        var emails = new ArrayList<String>();
        emails.add("admin@gmail.com");
        emails.add("user@gmail.com");

        var users = repository.findAll()
                .stream()
                .filter(x -> emails.contains(x.getEmail()))
                .toList();

        if (users.size() == 2)
            return;

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

    public static void createAttraction(AttractionDAO repository, AttractionTypeDAO typeRepository) throws Exception {


        var palestraAttractionNames = Arrays.asList(
                "Palestra IOT",
                "Palestra sobre Blockchain",
                "Palestra sobre Inteligência Artificial",
                "Seminário de DevOps",
                "Palestra sobre Computação Quântica",
                "Seminário de Cloud Computing",
                "Palestra sobre Ética na IA");

        if (repository.findAll().stream().anyMatch(x -> palestraAttractionNames.contains(x.getName())))
            return;

        var attractionTypeNames = Arrays.asList(
                "Palestra",
                "Minicurso",
                "Workshop",
                "Visita Técnica",
                "Torneio");

        for (var item : attractionTypeNames) {
            var attractionTypeModel = new AttractionType();
            attractionTypeModel.setName(item);
            attractionTypeModel.setDescription(item);
            typeRepository.save(attractionTypeModel);
        }

        for (var item : palestraAttractionNames) {
            var attractionModel = new Attraction();
            attractionModel.setName(item);
            attractionModel.setDescription(item);
            attractionModel.setVagas(100);
            var type = typeRepository.findByName("Palestra");
            attractionModel.setAttractionType(type);
            attractionModel.setCreatedAt(LocalTime.now());
            repository.save(attractionModel);
        }

        var minicursoAttractionNames = Arrays.asList(
                "Introdução ao Desenvolvimento Web",
                "Curso de Programação Funcional",
                "Introdução ao Big Data",
                "Curso de Programação em Python");

        for (var item : minicursoAttractionNames) {
            var attractionModel = new Attraction();
            attractionModel.setName(item);
            attractionModel.setDescription(item);
            attractionModel.setVagas(10);
            var type = typeRepository.findByName("Minicurso");
            attractionModel.setAttractionType(type);
            attractionModel.setCreatedAt(LocalTime.now());
            repository.save(attractionModel);
        }

        var workshopAttractionNames = Arrays.asList(
                "Workshop de Machine Learning",
                "Workshop de Desenvolvimento Mobile",
                "Oficina de Realidade Aumentada",
                "Workshop de Design UX/UI",
                "Oficina de Testes Automatizados");

        for (var item : workshopAttractionNames) {
            var attractionModel = new Attraction();
            attractionModel.setName(item);
            attractionModel.setDescription(item);
            attractionModel.setVagas(10);
            var type = typeRepository.findByName("Workshop");
            attractionModel.setAttractionType(type);
            attractionModel.setCreatedAt(LocalTime.now());
            repository.save(attractionModel);
        }

        var visitaAttractionNames = Arrays.asList(
                "Visita Técnica ao Data Center ISH",
                "Visita Técnica a Biancogres");

        for (var item : visitaAttractionNames) {
            var attractionModel = new Attraction();
            attractionModel.setName(item);
            attractionModel.setDescription(item);
            attractionModel.setVagas(12);
            var type = typeRepository.findByName("Visita Técnica");
            attractionModel.setAttractionType(type);
            attractionModel.setCreatedAt(LocalTime.now());
            repository.save(attractionModel);
        }

        var torneioAttractionNames = Arrays.asList(
                "Maratona de Programação",
                "Hackathon de Jogos Digitais",
                "Maratona de Robótica");

        for (var item : torneioAttractionNames) {
            var attractionModel = new Attraction();
            attractionModel.setName(item);
            attractionModel.setDescription(item);
            attractionModel.setVagas(6);
            var type = typeRepository.findByName("Torneio");
            attractionModel.setAttractionType(type);
            attractionModel.setCreatedAt(LocalTime.now());
            repository.save(attractionModel);
        }        
    }

    public static void createSpeaker(SpeakerDAO speakerRepository) throws Exception {
        
        var speakers = Arrays.asList(
            "Patricia Dockhorn",
            "Victor E. Silva Souza",
            "Alberto Ferreira De Souza");
        
        if (speakerRepository.findAll().stream().anyMatch(x -> speakers.contains(x.getName())))
            return;

        for (var item : speakers) {
            var speakerModel = new Speaker();
            speakerModel.setName(item);
            speakerModel.setDescription(item);
            speakerRepository.save(speakerModel);
        }
    }

    public static void createLocation(LocationDAO locationRepository) throws Exception {
        
        var locations = Arrays.asList(
            "Teatro da Ufes",
            "CT XIII");
        
        if (locationRepository.findAll().stream().anyMatch(x -> locations.contains(x.getName())))
            return;
            

        for (var item : locations) {
            var locationModel = new Location();
            locationModel.setName(item);
            locationModel.setDescription(item);
            locationRepository.save(locationModel);
        }
    }

}
