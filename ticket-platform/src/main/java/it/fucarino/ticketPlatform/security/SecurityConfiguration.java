package it.fucarino.ticketPlatform.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests()
		.requestMatchers("/ticket/**").hasAnyAuthority("OPERATOR", "ADMIN")
		.requestMatchers("/operator/status/**").hasAnyAuthority("OPERATOR", "ADMIN")
		.requestMatchers("/ticket/stato/**").hasAnyAuthority("OPERATOR", "ADMIN")
		.requestMatchers("/create/**").hasAuthority("ADMIN")
		.requestMatchers("/operator/show").hasAuthority("OPERATOR")
		.requestMatchers("/ticket/create/**").hasAuthority("ADMIN")
		.requestMatchers("/operator/info/**").hasAuthority("ADMIN")
		.requestMatchers(HttpMethod.POST, "/note/create").hasAnyAuthority("OPERATOR", "ADMIN")
		.requestMatchers(HttpMethod.POST, "/ticket/**").hasAuthority("ADMIN")
		.requestMatchers(HttpMethod.POST, "/delete/**").hasAuthority("ADMIN")
		.requestMatchers("/ticket/**", "/operator/**").hasAuthority("ADMIN")
		.requestMatchers("/").permitAll()
		.and().formLogin()
		.and().logout()
		.and().exceptionHandling();
		return http.build();
	}
	
	
	@Bean
	DatabaseUserDetailService userDetailService() {
		return new DatabaseUserDetailService();
	}
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
}
