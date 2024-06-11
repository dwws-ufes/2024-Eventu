package br.ufes.inf.eventu.app.controller;

import br.ufes.inf.eventu.app.core.EventuException;
import br.ufes.inf.eventu.app.domain.Speaker;
import br.ufes.inf.eventu.app.model.SpeakerModel;
import br.ufes.inf.eventu.app.persistence.SpeakerDAO;
import br.ufes.inf.eventu.app.services.interfaces.SpeakerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/speakers")
public class SpeakerController {

    @Autowired
    private SpeakerDAO speakerDAO;

    @Autowired
    private SpeakerService speakerService;

    @GetMapping("")
    public String index(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        model.addAttribute("title", "Eventos");

        int pageSize = 6;
        int startIndex = (page - 1) * pageSize;
        int endIndex = (int) Math.min(startIndex + pageSize, speakerDAO.count());
        int totalPages = (int) (speakerDAO.count() / pageSize);
        if((speakerDAO.count() % pageSize) > 0) totalPages ++;

        var speakersPaginated =  speakerDAO
                .findAll()
                .stream()
                .skip(startIndex)
                .limit(endIndex - startIndex)
                .toList();

        model.addAttribute("pageCurrent", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("speakers", speakersPaginated);
        return "speakers/index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("title", "Cadastrar");
        model.addAttribute("speakerModel", new SpeakerModel());

        return "speakers/register";
    }

    @PostMapping("/register")
    public String submitRegister(
            @Valid @ModelAttribute("speakerModel") SpeakerModel speakerModel,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes attributes) {

        model.addAttribute("title", "Cadastrar");

        if (bindingResult.hasErrors())
            return "speakers/register";

        try {
            var speaker = new Speaker();
            speaker.setName(speakerModel.getName());
            speaker.setDescription(speakerModel.getDescription());
            speaker.setDescription(speakerModel.getDescription());
            speakerService.save(speaker);
        } catch (Exception e) {
            var msg = "Erro ao atração";
            if (e instanceof EventuException) msg = e.getMessage();
            bindingResult.addError(new FieldError(bindingResult.getObjectName(), "name", msg));
            return "speakers/register";
        }

        attributes.addAttribute("registered", "true");
        return "redirect:/speakers/register";
    }
}
