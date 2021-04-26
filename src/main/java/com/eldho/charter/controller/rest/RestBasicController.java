package com.eldho.charter.controller.rest;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/basic")
public class RestBasicController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private static final Logger logger = LogManager.getLogger(RestBasicController.class);

    // The @RequestMapping annotation ensures that HTTP requests to /stubBasic are mapped to the stubBasic() method.
    // @RequestMapping maps all HTTP operations by default. Use @RequestMapping(method=GET) to narrow this mapping
    // @RequestParam binds the value of the query string parameter name into the name parameter of the greeting() method. This query string parameter is explicitly marked as optional (required=true by default): if it is absent in the request, the defaultValue of "World" is used

    @RequestMapping("/hello")
    public ResponseEntity<RestBasicVo> hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    	logger.log(Level.INFO, "Received request. Name: {}", name);
    	return new ResponseEntity<RestBasicVo>(new RestBasicVo(counter.incrementAndGet(), String.format(template, name)), HttpStatus.OK);
        // Spring's HTTP message converter (MappingJackson2HttpMessageConverter) which is on the classpath, is automatically chosen to convert the Greeting instance to JSON
    }

}
