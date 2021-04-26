package com.eldho.charter;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
 * Shows how to configure Spring Security. 
 * 
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

	@Override
	public void configure(WebSecurity web) throws Exception{
		//Ignore all the rest URLs
        web.ignoring().antMatchers("/").antMatchers("/rest/**").antMatchers("/h2-console/**");
    }

}