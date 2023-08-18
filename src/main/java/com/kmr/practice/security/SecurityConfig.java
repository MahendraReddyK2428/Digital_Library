package com.kmr.practice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	protected void config(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		    .requestMatchers("/digitalLibrary/student/createStudent", "/digitalLibrary/student/updateStudent",
                "/digitalLibrary/student/getStudent", "/digitalLibrary/student/updatePassword").hasAnyRole("STUDENT","ADMIN")
		    .requestMatchers("/digitalLibrary/student/deleteStudent", "/digitalLibrary/book/createBook",
                    "/digitalLibrary/book/getBook", "/digitalLibrary/book/updateBook",
                    "/digitalLibrary/book/deleteBook", "/digitalLibrary/transaction/issueBook",
                    "/digitalLibrary/transaction/returnBook").hasRole("ADMIN")
		    .anyRequest().authenticated()
		    .and()
		    .formLogin().permitAll()
		    .and()
		    .logout().permitAll();
	}
	
	//@Override
	protected void config(AuthenticationManagerBuilder auth) {
		auth.userDetailsService(userDetailsService);
	}
}
