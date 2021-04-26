package com.eldho.charter.controller.mvc;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


//Controller annotation will return the view name to MVC framework.
//If you change it to RestController, the return statement returns a plain text.

@Controller
public class MvcSessionController {

    private static final Logger logger = LogManager.getLogger(MvcSessionController.class);

	@GetMapping("/mvc/session")
	public String session(Model model, HttpSession session) {

		logger.info("Session Id " + session.getId() + ". Request: session. Received request");
		@SuppressWarnings("unchecked")
		List<String> roles = (List<String>) session.getAttribute("USER_ROLES");
		if (roles == null) {
			logger.info("Session Id " + session.getId() + ". Request: session. Role list is empty. Creating");
			roles = new ArrayList<>();
			roles.add("Role 0");
			roles.add("Role 1");
			logger.info("Session Id " + session.getId() + ". Request: session. Adding to cache");
			session.setAttribute("USER_ROLES", roles);
		} else {
			logger.info("Session Id " + session.getId() + ". Request: session. Retrieved role list from cache");
		}
		model.addAttribute("sessionRoles", roles);
		return "session";
	}

	@PostMapping("/mvc/session/addRole")
	public String addRole(@RequestParam("role") String role, HttpServletRequest request) {

		logger.info("Session Id " + request.getSession().getId() + ". Request: addRole. Received request. Role to add: " + role);

		//This fetches the USER_ROLES attribute from Redis cache (I think)
		@SuppressWarnings("unchecked")
		List<String> roles = (List<String>) request.getSession().getAttribute("USER_ROLES");
		if (roles == null) {
			//This should apply only if roles could not be fetched from Redis
			logger.info("Session Id " + request.getSession().getId() + ". Request: addRole. Role list is empty. Creating");
			roles = new ArrayList<>();
			roles.add("Role 0");
			roles.add("Role 1");
			logger.info("Session Id " + request.getSession().getId() + ". Request: addRole. Adding to cache");
			request.getSession().setAttribute("USER_ROLES", roles);
		} else {
			logger.info("Session Id " + request.getSession().getId() + ". Request: addRole. Retrieved role list from cache");
		}

		logger.info("Session Id " + request.getSession().getId() + ". Request: addRole. Adding role and saving list to cache");
		//Add the newly added role to the list 
		roles.add(role);
		//Save in cache
		request.getSession().setAttribute("USER_ROLES", roles);
		return "redirect:/mvc/session";
	}

	@PostMapping("/mvc/session/destroy")
	public String destroySession(HttpServletRequest request) {

		logger.info("Session Id " + request.getSession().getId() + ". Request: destroy. Received");
		request.getSession().invalidate();
		return "redirect:/mvc/session";
	}

}