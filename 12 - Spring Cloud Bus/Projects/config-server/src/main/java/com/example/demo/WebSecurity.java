package com.example.demo;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers("/**").permitAll();
		http.csrf().disable();
	}

}
