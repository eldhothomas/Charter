package com.eldho.charter.controller.mvc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//Controller annotation will return the view name to MVC framework.
//If you change it to RestController, the return statement returns a plain text.

@Controller
public class MvcHelloController {

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
    private static final Logger logger = LogManager.getLogger(MvcHelloController.class);

    
    @GetMapping("/mvc/hello/hello1")
    public String hello(Model model) {

    	logger.info("Received request - HELLO1");
        model.addAttribute("message", message);
        model.addAttribute("tasks", tasks);
        return "hello"; //view
    }

    // /hello?name=kotlin
    @GetMapping("mvc/hello/hello2")
    public String helloWithParam(
            @RequestParam(name = "message", required = false, defaultValue = "") 
			String message, Model model) {

        model.addAttribute("message", message);

        return "hello"; //view
    }

    @GetMapping("/mvc/hello/hello3")
    public String helloWithRequest(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        logger.info("Received request - HELLO3. Base URL: " + request.getServerName());
        message = "Request Server Name: " + request.getServerName() + ". Port: " + request.getServerPort();
        model.addAttribute("message", message);
        model.addAttribute("tasks", tasks);
        return "hello"; //view
    }


}