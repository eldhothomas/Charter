package com.eldho.charter.controller.mvc;

import org.apache.commons.lang3.StringUtils;

/*
 * Shows how attributes are added to session to be retrieved in later requests.
 * This can be used to maintain additional information about the user (name, title, roles etc.) to be used throughout the session
 * This session information is cached in Redis (See configuration in application.yml). 
 * Spring session manages all that automatically.
 * 
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//Controller annotation will return the view name to MVC framework.
//If you change it to RestController, the return statement returns a plain text.

@Controller
public class MvcRootController {

    private static final Logger logger = LogManager.getLogger(MvcRootController.class);

	@GetMapping("/")
	public String rootBranchOut(Model model, HttpSession session, HttpServletRequest request) {

		logger.info("Session Id " + session.getId() + ". Received request - '/'. Remote Addr: " + request.getRemoteAddr() + ". Remote Host: " + request.getRemoteHost());

		String baseUrl =  request.getServerName();
	    if (StringUtils.isBlank(baseUrl)) {
	        logger.warn("Session Id " + session.getId() + ". No server name received on request to redirect request");
	        return "error";
	    }
	    //localhost added for eldho.com on development environment
	    if (baseUrl.toLowerCase().contains("eldho.com") || baseUrl.toLowerCase().contains("localhost") || baseUrl.toLowerCase().contains("192.168.1")) {
	        logger.info("Session Id " + session.getId() + ". Base URL: " + baseUrl + "; Loading eldho.com");
	        return "eldho";
	    } else if (baseUrl.toLowerCase().contains("perumbavoor.com")) {
	        logger.info("Session Id " + session.getId() + ". Base URL: " + baseUrl + "; Loading perumbavoor.com");
	        return "perumbavoor";
	    } else if (baseUrl.toLowerCase().contains("deauville.us")) {
	        logger.info("Session Id " + session.getId() + ". Base URL: " + baseUrl + "; Loading deauville.us");
	        return "deauville";
	    } else {
	        logger.warn("Session Id " + session.getId() + ". Base URL: " + baseUrl + "; Unexpected server name received on request. Has to be eldho.com, perumbavoor.com or deauville.us");
	        return "others";
	    }
	}

}