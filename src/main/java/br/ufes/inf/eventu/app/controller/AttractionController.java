package br.ufes.inf.eventu.app.controller;

import br.ufes.inf.eventu.app.core.EventuException;
import br.ufes.inf.eventu.app.domain.Attraction;
import br.ufes.inf.eventu.app.domain.AttractionType;
import br.ufes.inf.eventu.app.model.AttractionModel;
import br.ufes.inf.eventu.app.model.AttractionTypeModel;
import br.ufes.inf.eventu.app.persistence.AttractionDAO;
import br.ufes.inf.eventu.app.persistence.AttractionTypeDAO;
import br.ufes.inf.eventu.app.persistence.SpeakerDAO;
import br.ufes.inf.eventu.app.services.interfaces.AttractionService;
import br.ufes.inf.eventu.app.services.interfaces.AttractionTypeService;
import jakarta.validation.Valid;

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
    private AttractionService attractionService;

    @Autowired
    private AttractionTypeService attractionTypeService;

    @GetMapping("")
    public String index(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        model.addAttribute("title", "Eventos");

        int pageSize = 6;
        int startIndex = (page - 1) * pageSize;
        int endIndex = (int) Math.min(startIndex + pageSize, attractionDAO.count());
        int totalPages = (int) (attractionDAO.count() / pageSize);
        if((attractionDAO.count() % pageSize) > 0) totalPages ++;

        var attractionsPaginated =  attractionDAO
                .findAll()
                .stream()
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
            attraction.setSpeakers(new HashSet<>(speakers));
            attractionService.save(attraction);
        } catch (Exception e) {
            var msg = "Erro ao atração";
            if (e instanceof EventuException) msg = e.getMessage();
            bindingResult.addError(new FieldError(bindingResult.getObjectName(), "name", msg));
            return "attractions/register";
        }

        attributes.addAttribute("registered", "true");
        return "redirect:/attractions/register";
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

}
