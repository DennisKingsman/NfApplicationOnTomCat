package com.trainig.spring.main.project.controller.mvc.admin;

import com.trainig.spring.main.project.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.trainig.spring.main.project.utils.Constants.ERROR;

@Controller
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.getAll());
        return "admin";
    }

    @PostMapping(value = "/admin")
    public String deleteUser(@RequestParam Long userId,
                             Model model) {
        log.info("User id is : {}", userId);
        if (userService.delete(userId)) {
            return "redirect:/admin";
        } else {
            model.addAttribute(ERROR, "deleting fails");
            return "redirect:/errorPage";
        }
    }

}
