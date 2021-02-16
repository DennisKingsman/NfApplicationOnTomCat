package com.trainig.spring.main.project.rest.controller;

import com.trainig.spring.main.project.entity.Addressee;
import com.trainig.spring.main.project.service.AddresseeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class AddresseeRestController {

    private static final String ADDRESSEE = "addressee";
    private static final String ERROR = "errorMessage";

    @Autowired
    private AddresseeService addresseeService;

    @GetMapping(value = "/addressee")
    public ModelAndView getAllAddressee(ModelAndView model) {
        model.addObject("addressees", addresseeService.getAllAddressee());
        model.setViewName("addressees");
        return model;
    }

    @GetMapping(value = "/addressee/add")
    public ModelAndView getAddAddresseePage(ModelAndView model) {
        model.addObject(ADDRESSEE, new Addressee());
        model.setViewName("newAddressee");
        return model;
    }

    @PostMapping(value = "/addressee/add")
    public ModelAndView postAddAddresseePage(@ModelAttribute(ADDRESSEE)
                                                   Addressee addressee) {
        ModelAndView modelAndView;
        if (addresseeService.addAddressee(addressee)) {
//            model.addAttribute("all", addresseeService.getAllAddressee());
            modelAndView = new ModelAndView("redirect:/addressees");
        } else {
            modelAndView = new ModelAndView("redirect:/errorPage");
            modelAndView.addObject(ERROR, "addressee exists");
        }
        return modelAndView;
    }

    @GetMapping(value = "/delete/{addresseeId}")
    public ModelAndView deleteAddressee(@PathVariable(name = "addresseeId")
                                              Long addresseeId) {
        ModelAndView model;
        if (addresseeService.deleteAddressee(addresseeId)) {
            model = new ModelAndView("redirect:/addressee");
        } else {
            model = new ModelAndView("redirect:/errorPage");
            model.addObject(ERROR, "deleting error");
        }
        return model;
    }

}
