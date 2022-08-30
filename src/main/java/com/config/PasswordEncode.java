package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncode {

	@Bean
	public BCryptPasswordEncoder encodePass() {
		System.out.println("........BCRYPY Encoder is ready........");
		return new BCryptPasswordEncoder();
	}
}
