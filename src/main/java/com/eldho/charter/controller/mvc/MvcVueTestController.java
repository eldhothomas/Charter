package com.eldho.charter.controller.mvc;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eldho.charter.controller.rest.RestBasicController;

@Controller
@RequestMapping("/mvc/vue")
public class MvcVueTestController {

    private static final Logger logger = LogManager.getLogger(RestBasicController.class);

    @RequestMapping(method=RequestMethod.GET, value="/test")
    public String index(Model model) {
    	logger.log(Level.INFO, "Received request for Vue Test");
        model.addAttribute("eventName", "FIFA 2018");
        return "vuetest";
    }    

}
