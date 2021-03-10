package com.trainig.spring.main.project.controller.rest;

import com.trainig.spring.main.project.entity.Addressee;
import com.trainig.spring.main.project.service.addressee.AddresseeService;
import org.jboss.logging.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class AddresseeRestController {

    private static final Logger log = LoggerFactory.getLogger(AddresseeRestController.class);
    private static final String ADDRESSEES = "addressees";
    private static final String USER_ID = "userId";

    private AddresseeService addresseeService;

    @Autowired
    public void setAddresseeService(AddresseeService addresseeService) {
        this.addresseeService = addresseeService;
    }

    @GetMapping(value = "/profile/addressees/{userId}")
    public ModelAndView getAllAddressee(
            @PathVariable Long userId,
            ModelAndView model) {
        return prepareAddresseesPage(userId, model);
    }

    @PostMapping(value = "/profile/addressees/{userId}")
    public ModelAndView deleteAddressee(
            @RequestParam Long addresseeId,
            @PathVariable Long userId,
            ModelAndView model) {
        if (!addresseeService.delete(addresseeId)) {
            model.addObject("message", "failToDelete");
        }
        return prepareAddresseesPage(userId, model);
    }

    @GetMapping(value = "/profile/addressee/add")
    public ModelAndView getAddAddresseePage(
            @RequestParam(name = USER_ID) Long userId,
            ModelAndView model) {
        log.info("userId : {}", userId);
        model.addObject(USER_ID, userId);
        model.setViewName("newAddressee");
        return model;
    }

    @PostMapping(value = "/profile/addressee/add")
    public ModelAndView postAddAddresseePage(
            @RequestParam(name = USER_ID) Long userId,
            @RequestParam(name = "addresseeName") String addresseeName,
            @RequestParam(name = "addresseeEmail") String addresseeEmail,
            ModelAndView modelAndView) {
        log.info("userID : {}", userId);
        Addressee addressee = new Addressee();
        addressee.setAddresseeName(addresseeName);
        addressee.setAddresseeEmail(addresseeEmail);
        log.info("addressee : {}", addressee);
        if (!addresseeService.saveUserAddressee(userId, addressee)) {
            modelAndView.addObject("message", "Addressee exists");
        }
        return prepareAddresseesPage(userId, modelAndView);
    }

    private ModelAndView prepareAddresseesPage(Long userId, ModelAndView modelAndView) {
        log.info("userId : {}", userId);
        modelAndView.addObject(USER_ID, userId);
        modelAndView.addObject(ADDRESSEES, addresseeService.getAllByUserId(userId));
        modelAndView.setViewName(ADDRESSEES);
        return modelAndView;
    }

}
