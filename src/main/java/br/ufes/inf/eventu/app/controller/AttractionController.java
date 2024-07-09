package br.ufes.inf.eventu.app.controller;

import br.ufes.inf.eventu.app.core.EventuException;
import br.ufes.inf.eventu.app.domain.Attraction;
import br.ufes.inf.eventu.app.domain.AttractionTime;
import br.ufes.inf.eventu.app.domain.AttractionType;
import br.ufes.inf.eventu.app.domain.Location;
import br.ufes.inf.eventu.app.model.AttractionModel;
import br.ufes.inf.eventu.app.model.AttractionTimeModel;
import br.ufes.inf.eventu.app.model.AttractionTypeModel;
import br.ufes.inf.eventu.app.model.LocationModel;
import br.ufes.inf.eventu.app.persistence.AttractionDAO;
import br.ufes.inf.eventu.app.persistence.AttractionTypeDAO;
import br.ufes.inf.eventu.app.persistence.SpeakerDAO;
import br.ufes.inf.eventu.app.persistence.LocationDAO;
import br.ufes.inf.eventu.app.services.interfaces.AttractionService;
import br.ufes.inf.eventu.app.services.interfaces.AttractionTimeService;
import br.ufes.inf.eventu.app.services.interfaces.AttractionTypeService;
import br.ufes.inf.eventu.app.services.interfaces.LocationService;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/attractions")
public class AttractionController {

    @Autowired
    private AttractionDAO attractionDAO;

    @Autowired
    private AttractionTypeDAO attractionTypeDAO;

    @Autowired
    private SpeakerDAO speakerDAO;

    @Autowired
    private LocationDAO locationDAO;

    @Autowired
    private AttractionService attractionService;

    @Autowired
    private AttractionTypeService attractionTypeService;

    @Autowired
    private AttractionTimeService attractionTimeService;

    @Autowired
    private LocationService locationService;

    @GetMapping("")
    public String index(
            @RequestParam(value = "p", defaultValue = "") String pesquisa ,
            @RequestParam(value = "page", defaultValue = "1") int page,
            Model model) {
        model.addAttribute("title", "Eventos");

        int pageSize = 6;
        int startIndex = (page - 1) * pageSize;
        int endIndex = (int) Math.min(startIndex + pageSize, attractionDAO.count());
        int totalPages = (int) (attractionDAO.count() / pageSize);
        if((attractionDAO.count() % pageSize) > 0) totalPages ++;

        var attractionsPaginated =  attractionDAO
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Attraction::getId))
                .filter(x -> (pesquisa.isEmpty()
                        || (x.getName().toLowerCase().contains(pesquisa.toLowerCase())
                            || x.getDescription().toLowerCase().contains(pesquisa.toLowerCase()))))
                .skip(startIndex)
                .limit(endIndex - startIndex)
                .toList();

        model.addAttribute("pageCurrent", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("attractions", attractionsPaginated);
        return "attractions/index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("title", "Cadastrar");
        model.addAttribute("attractionModel", new AttractionModel());

        var attractionTypes = attractionTypeDAO
        .findAll()
        .stream()
        .toList();

        var speakers = speakerDAO
        .findAll()
        .stream()
        .toList();

        model.addAttribute("attractionTypes", attractionTypes);
        model.addAttribute("speakers", speakers);

        return "attractions/register";
    }


    @PostMapping("/register")
    public String submitRegister(
            @Valid @ModelAttribute("attractionModel") AttractionModel attractionModel,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes attributes) {

        model.addAttribute("title", "Cadastrar");

        if (bindingResult.hasErrors())
            return "attractions/register";

        try {
            var attraction = new Attraction();
            attraction.setName(attractionModel.getName());
            attraction.setDescription(attractionModel.getDescription());
            attraction.setDescription(attractionModel.getDescription());

            var speakers = speakerDAO.findAllById(attractionModel.getSpeakersIds());

            var selectedAttractionType = attractionTypeDAO.findById(attractionModel.getAttractionTypeId()).get();

            attraction.setAttractionType(selectedAttractionType);
            attraction.setSpeakers(speakers);
            attraction = attractionService.save(attraction);

            attributes.addAttribute("registered", "true");
            return "redirect:/attractions/edit/" + attraction.getId();
        } catch (Exception e) {
            var msg = "Erro ao atração";
            if (e instanceof EventuException) msg = e.getMessage();
            bindingResult.addError(new FieldError(bindingResult.getObjectName(), "name", msg));
            return "attractions/register";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Attraction attraction = attractionDAO.findById(id).get();
        AttractionModel attractionModel = new AttractionModel();

        attractionModel.setId(attraction.getId());
        attractionModel.setName(attraction.getName());
        attractionModel.setDescription(attraction.getDescription());
        attractionModel.setAttractionTypeId(attraction.getAttractionType().getId());
        attractionModel.setSpeakersIds(attraction.getSpeakers().stream().map(s -> s.getId()).toList());       
        attractionModel.setAttachments(attraction.getAttachments());           


        model.addAttribute("title", "Editar atração");
        model.addAttribute("attractionModel", attractionModel);

        var attractionTypes = attractionTypeDAO
        .findAll()
        .stream()
        .toList();

        var speakers = speakerDAO
        .findAll()
        .stream()
        .toList();

        var locations = locationDAO
        .findAll()
        .stream()
        .toList();

        var attractionTimes = attraction.getAttractionTimes();

        model.addAttribute("registeredLocations", locations);
        model.addAttribute("attractionTypes", attractionTypes);
        model.addAttribute("attractionTimes", attractionTimes);
        model.addAttribute("attractionTimeModel", new AttractionTimeModel());
        model.addAttribute("speakers", speakers);

        return "attractions/edit";
    }

    @PostMapping("/edit/{id}")
    public String submitEdit(
            @PathVariable("id") Long id,
            @Valid @ModelAttribute("attractionModel") AttractionModel attractionModel,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes attributes) {

        model.addAttribute("title", "Editar Atração");	

        if (bindingResult.hasErrors())
            return "attractions/register";

        try {
            var attraction = attractionDAO.findById(id).get();
            attraction.setName(attractionModel.getName());
            attraction.setDescription(attractionModel.getDescription());
            attraction.setDescription(attractionModel.getDescription());

            var speakers = speakerDAO.findAllById(attractionModel.getSpeakersIds());

            var selectedAttractionType = attractionTypeDAO.findById(attractionModel.getAttractionTypeId()).get();

            attraction.setAttractionType(selectedAttractionType);
            attraction.setSpeakers(speakers);
            attraction = attractionService.save(attraction);

            attributes.addAttribute("registered", "true");
            return "redirect:/attractions/edit/" + attraction.getId();
        } catch (Exception e) {
            var msg = "Erro ao atração";
            if (e instanceof EventuException) msg = e.getMessage();
            bindingResult.addError(new FieldError(bindingResult.getObjectName(), "name", msg));
            return "attractions/register";
        }
    }


    @GetMapping("/register_type")
    public String registerType(Model model) {
        model.addAttribute("title", "Cadastrar");
        model.addAttribute("attractionTypeModel", new AttractionTypeModel());

        var registeredAttractionTypes =  attractionTypeDAO
        .findAll()
        .stream()
        .toList();

        
        model.addAttribute("registeredAttractionTypes", registeredAttractionTypes);
        return "attractions/register_type";
    }

    @PostMapping("/register_type")
    public String submitRegisterType(
            @Valid @ModelAttribute("attractionTypeModel") AttractionTypeModel attractionTypeModel,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes attributes) {

        model.addAttribute("title", "Cadastrar");

        if (bindingResult.hasErrors())
            return "attractions/register_type";

        try {
            var attractionType = new AttractionType();
            attractionType.setName(attractionTypeModel.getName());
            attractionType.setDescription(attractionTypeModel.getDescription());
            attractionTypeService.save(attractionType);
        } catch (Exception e) {
            var msg = "Erro ao registrar tipo de atração";
            if (e instanceof EventuException) msg = e.getMessage();
            bindingResult.addError(new FieldError(bindingResult.getObjectName(), "name", msg));
            return "attractions/register_type";
        }

        attributes.addAttribute("registered", "true");
        return "redirect:/attractions/register_type";
    }

    @GetMapping("/register_location")
    public String registerLocation(Model model) {
        model.addAttribute("title", "Cadastrar");
        model.addAttribute("locationModel", new LocationModel());

        var registeredLocations =  locationDAO
        .findAll()
        .stream()
        .toList();

        
        model.addAttribute("registeredLocations", registeredLocations);
        return "attractions/register_location";
    }

    @PostMapping("/register_location")
    public String submitRegisterLocation(
            @Valid @ModelAttribute("locationModel") LocationModel locationModel,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes attributes) {

        model.addAttribute("title", "Cadastrar");

        if (bindingResult.hasErrors())
            return "attractions/register_location";

        try {
            var location = new Location();
            location.setName(locationModel.getName());
            location.setDescription(locationModel.getDescription());
            locationService.save(location);
        } catch (Exception e) {
            var msg = "Erro ao registrar localizacao";
            if (e instanceof EventuException) msg = e.getMessage();
            bindingResult.addError(new FieldError(bindingResult.getObjectName(), "name", msg));
            return "attractions/register_location";
        }

        attributes.addAttribute("registered", "true");
        return "redirect:/attractions/register_location";
    }

    @PostMapping("/add_time/{id}")
    public String addAttractionTime(
            @PathVariable("id") Long attractionId,
            @Valid @ModelAttribute("attractionTimeModel") AttractionTimeModel attractionTimeModel,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes attributes)  throws Exception {
        System.out.println("RECEBEU POST");
        model.addAttribute("title", "Cadastrar");

        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.hasErrors());
            return "redirect:/attractions/edit/" + attractionId;
        }
            

        try {
            var time = new AttractionTime();
            time.setDate(LocalDate.parse(attractionTimeModel.getDate()));
            time.setStart(LocalTime.parse(attractionTimeModel.getStart()));
            time.setFinish(LocalTime.parse(attractionTimeModel.getFinish()));
            time.setLocation(locationDAO.findById(attractionTimeModel.getLocationId()).get());
            time.setAttraction(attractionDAO.findById(attractionId).get());
            attractionTimeService.save(time);
        } catch (Exception e) {
            
            System.out.println(e);
            var msg = "Erro ao registrar tipo de atração";
            System.out.println(msg);
            if (e instanceof EventuException){
                msg = e.getMessage();       
            } 
            bindingResult.addError(new FieldError(bindingResult.getObjectName(), "name", msg));
            throw e;
            //return "redirect:/attractions/edit/" + attractionId;
        }

        attributes.addAttribute("registered", "true");
        return "redirect:/attractions/edit/" + attractionId;
    }

}
