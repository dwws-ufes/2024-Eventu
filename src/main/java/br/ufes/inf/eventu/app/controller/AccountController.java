package br.ufes.inf.eventu.app.controller;

import br.ufes.inf.eventu.app.core.EventuException;
import br.ufes.inf.eventu.app.domain.User;
import br.ufes.inf.eventu.app.model.UserModel;
import br.ufes.inf.eventu.app.services.interfaces.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService<User> userService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login");
        return "account/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        var session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("title", "Cadastrar");
        model.addAttribute("userModel", new UserModel());
        return "account/register";
    }

    @PostMapping("/register")
    public String submitRegister(
            @Valid @ModelAttribute("userModel") UserModel userModel,
            BindingResult bindingResult,
            Model model) {

        model.addAttribute("title", "Cadastrar");

        if (bindingResult.hasErrors())
            return "account/register";

        try {
            var user = new User();
            user.setFullName(userModel.getFullName());
            user.setDateBirth(userModel.getDateBirth());
            user.setInstitution(userModel.getInstitution());
            user.setEmail(userModel.getEmail());
            user.setPassword(userModel.getPassword());
            userService.save(user);
        } catch (Exception e) {
            var msg = "Erro ao cadastrar usuário";
            if (e instanceof EventuException) msg = e.getMessage();
            bindingResult.addError(new FieldError(bindingResult.getObjectName(), "email", msg));
            return "account/register";
        }

        return "redirect:/login";
    }
}
