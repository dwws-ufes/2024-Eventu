package br.ufes.inf.eventu.app.controller;

import br.ufes.inf.eventu.app.core.EventuException;
import br.ufes.inf.eventu.app.domain.Attraction;
import br.ufes.inf.eventu.app.domain.User;
import br.ufes.inf.eventu.app.model.AttractionModel;
import br.ufes.inf.eventu.app.model.UserModel;
import br.ufes.inf.eventu.app.persistence.AttractionDAO;
import br.ufes.inf.eventu.app.services.interfaces.AttractionService;
import jakarta.validation.Valid;
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
    private AttractionService attractionService;

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
}
