package com.training.spring.main.project.controller.rest;

import com.trainig.spring.main.project.controller.rest.MainRestController;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;

public class MainRestControllerTest {

    @Test
    public void welcomePageTest() {
        MainRestController mainRestController = new MainRestController();
        ModelAndView modelAndView = mainRestController.welcomePage(new ModelAndView());
        assertEquals("home", modelAndView.getViewName());
    }

}
