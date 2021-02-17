package com.trainig.spring.main.project.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainRestController {

    @GetMapping(value = {"/", "/home"})
    public ModelAndView welcomePage(ModelAndView modelAndView) {
        modelAndView.addObject("message", "This is welcome page!");
        modelAndView.addObject("title", "Welcome");
        modelAndView.setViewName("home");
        return modelAndView;
    }

}
