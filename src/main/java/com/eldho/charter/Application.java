package com.eldho.charter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

//This is required for the booTrun task for Gradle
//@SpringBootApplication annotation adds all the following - 
//@Configuration tags the class as a source of bean definitions for the application context.
//@EnableAutoConfiguration tells Spring Boot to start adding beans basd on classpath settings, other beans and various property settings
//Normally you would add @EnableWebMvc for a Spring MVC app, but Spring Boot adds it automatically when it sees spring-webmvc on the classpath. This flags the application as a web application and activates key behaviors such as setting up a DispatcherServlet.
//@ComponentScan tells Spring to look for other components, configurations, and services under this package, allowing it to find the controllers.

@SpringBootApplication
@EnableCaching
public class Application extends SpringBootServletInitializer implements CommandLineRunner {
	private static final Logger logger = LogManager.getLogger(Application.class);

	@Autowired
	private YAMLConfig myConfig;

	// The main() method uses SpringApplication.run() method from Spring Boot to
	// launch an application
	public static void main(String[] args) {
		logger.info("Running application");
		SpringApplication.run(Application.class, args);
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }


	@Override
	public void run(String... args) throws Exception {
		// This is added to show how these properties are extracted from application.yml

		logger.info("YAML Env: " + myConfig.getEnvironment());
		logger.info("YAML name: " + myConfig.getName());
		logger.info("Spring Profile: " + myConfig.getSpring().getProfiles());
		logger.info("DB01 URL: " + myConfig.getSpring().getDatasource().getDb01().getJdbcUrl());
		logger.info("JPA DP Platform: " + myConfig.getSpring().getJpa().getDatabasePlatform());
		logger.info("H2 Path: " + myConfig.getSpring().getH2().getConsole().getPath());
		logger.info("servers: " + myConfig.getServers());
		logger.info("LOG4J_PATH : {}", System.getProperty("LOG4J_PATH"));
		logger.info("LOG4J_LEVEL : {}", System.getProperty("LOG4J_LEVEL"));

	}
}
