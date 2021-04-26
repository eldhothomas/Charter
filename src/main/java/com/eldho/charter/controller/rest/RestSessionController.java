package com.eldho.charter.controller.rest;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/session")
public class RestSessionController {

    private static final Logger logger = LogManager.getLogger(RestSessionController.class);

    @RequestMapping("/getId")
    public String getSessionId(HttpSession session) {
    	logger.log(Level.INFO, "Received request to get session id");
    	return session.getId();
    }

}
