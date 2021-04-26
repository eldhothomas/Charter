package com.eldho.charter.controller.mvc;

import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MvcErrorController implements ErrorController {

    private static final Logger logger = LogManager.getLogger(MvcErrorController.class);

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {

		logger.debug("An error occurred. Remote Addr: " + request.getRemoteAddr());
		
		Iterator<String> iter = request.getAttributeNames().asIterator();
		while (iter.hasNext()) {
			String attribName = (String) iter.next();
			logger.trace("Attribute " + attribName + ": " + request.getAttribute(attribName));
		}

		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			
			logger.debug("HTTP Status Code: " + statusCode);
			
	        model.addAttribute("message", "Status code: " + statusCode);

		} else {
			model.addAttribute("message", "Status code unknown");
		}
		return "error";
	}

	/**
	 * Do not use this method. This is deprecated. Remove this when it is removed from the interface class.
	 */
	@Override
	public String getErrorPath() {
		return null;
	}
}
