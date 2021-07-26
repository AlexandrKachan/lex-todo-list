package by.lex.lextodolist.controllers;

import by.lex.lextodolist.service.UserRepresentation;
import by.lex.lextodolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class LoginController {
    //внедряем UserService
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String loginPage() {
        return "login";

    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserRepresentation());
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid @ModelAttribute("user") UserRepresentation userRepresentation, BindingResult bindingResult) { // параметры: предтавление пользователя; валидация пользователя
        if (bindingResult.hasErrors()) {
            return "register";//если ошибка -остаемся на этойже странице
            }
        if (!userRepresentation.getPassword().equals(userRepresentation.getRepeatPassword())){
            bindingResult.rejectValue("password","", "пароли не совпадают" );
            return "register"; // если пароли не совпадают
        }
        userService.create((userRepresentation)); // создаем пользователя
        return "redirect:/login"; // переходим на страницу логирования

    }
}
