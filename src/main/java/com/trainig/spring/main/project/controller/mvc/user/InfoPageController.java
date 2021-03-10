package com.trainig.spring.main.project.controller.mvc.user;

import com.trainig.spring.main.project.entity.User;
import com.trainig.spring.main.project.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class InfoPageController {

    private static final Logger log = LoggerFactory.getLogger(InfoPageController.class);

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/profile")
    public String getInfoPage(Model model, Principal principal) {
        String principalName = principal.getName();
        User user = userService.getByName(principal.getName());
        log.info("Principal name {}", principalName);
        model.addAttribute("user", user);
        return "infoPage";
    }

    @GetMapping(value = "/profile/edit/{field}")
    public String getEditPage(Principal principal,
                              @PathVariable(name = "field") String field,
                              Model model) {
        User user = userService.getByName(principal.getName());
        log.info("User into getEdit: {}", user);
        model.addAttribute("field", field);
        String message = "Enter new %s : ";
        String alert = "after editing log in again";
        model.addAttribute("alert", alert);
        model.addAttribute("message", String.format(message, field));
        return "editUser";
    }

    @PostMapping(value = "/profile/edit/{field}")
    public String putEditPage(Principal principal,
                              @ModelAttribute(name = "field") String field,
                              @RequestParam(name = "newNameOrPassword") String param,
                              Model model) {
        User user = userService.getByName(principal.getName());
        if (field.equals("name")) {
            user.setUserName(param);
        } else {
            user.setUserPassword(param);
        }
        log.info("New user : {}", user);
        if (userService.update(user)) {
            return "redirect:/logout";
        } else {
            String message = "Update error. Please contact administrator.";
            model.addAttribute("errorMessage", message);
            return "redirect:/errorPage";
        }
    }

}
