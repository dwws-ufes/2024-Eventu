package br.ufes.inf.eventu.app.controller;

import br.ufes.inf.eventu.app.persistence.AttractionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/attractions")
public class AttractionController {

    @Autowired
    private AttractionDAO attractionDAO;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("title", "Eventos");
        model.addAttribute("attractions", attractionDAO.findAll());
        return "attractions/index";
    }
}
