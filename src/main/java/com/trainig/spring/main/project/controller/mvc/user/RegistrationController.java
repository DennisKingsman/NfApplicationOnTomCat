package com.trainig.spring.main.project.controller.mvc.user;

import com.trainig.spring.main.project.entity.User;
import com.trainig.spring.main.project.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import static com.trainig.spring.main.project.utils.Constants.ERROR;

@Controller
public class RegistrationController {

    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);
    private static final String REGISTER = "register";

    @Autowired
    private UserService userService;

    @GetMapping(value = "/registration")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("userForm", user);
        return REGISTER;
    }

    @PostMapping(value = "/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User user,
                          BindingResult bindingResult,
                          Model model) {
        log.info("user from registration from: {}", user);
        if (bindingResult.hasErrors()) {
            model.addAttribute(ERROR, "incorrect login or password");
            return REGISTER;
        } else if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute(ERROR, "password error");
            return REGISTER;
        } else if (!userService.save(user)) {
            model.addAttribute(ERROR, "user already exists");
            return REGISTER;
        }
        return "redirect:/";
    }

}
