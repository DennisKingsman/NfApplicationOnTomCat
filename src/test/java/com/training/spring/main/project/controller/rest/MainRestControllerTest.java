package com.training.spring.main.project.controller.rest;

import com.trainig.spring.main.project.controller.rest.MainRestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class MainRestControllerTest {

    @Test
    public void welcomePageTest() {
        MainRestController mainRestController = new MainRestController();
        ModelAndView modelAndView = mainRestController.welcomePage(new ModelAndView());
        assertEquals("home", modelAndView.getViewName());
    }

}
